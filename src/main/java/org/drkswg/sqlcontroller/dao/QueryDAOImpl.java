package org.drkswg.sqlcontroller.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.IOUtils;
import org.drkswg.sqlcontroller.exception.ClobSerializationException;
import org.drkswg.sqlcontroller.mapping.ResultSet;
import org.drkswg.sqlcontroller.mapping.SingleValueResult;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QueryDAOImpl implements QueryDAO {
    EntityManager entityManager;

    @Override
    public SingleValueResult getSingleValue(String query) {
        SingleValueResult result = new SingleValueResult();
        Query queryResult = entityManager.createNativeQuery(query);
        List<?> resultSet = queryResult.getResultList();

        if (!resultSet.isEmpty()) {
            Object value = resultSet.get(0);

            if (value instanceof Clob clob) {
                value = getClobAsString(clob);
            }

            result.setValue(value);
        }

        return result;
    }

    @Override
    public ResultSet getResultSet(String query) {
        ResultSet resultSet = new ResultSet();
        NativeQuery<?> nativeQuery = entityManager.createNativeQuery(query)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) nativeQuery.getResultList();

        resultList.forEach(row -> row.forEach((key, value) -> {
            if (value instanceof Clob clob) {
                String clobString = getClobAsString(clob);
                row.put(key, clobString);
            }
        }));

        resultSet.setRows(resultList);

        return resultSet;
    }

    private String getClobAsString(Clob clob) throws ClobSerializationException {
        StringWriter writer = new StringWriter();

        try {
            Reader reader = clob.getCharacterStream();
            IOUtils.copy(reader, writer);
        } catch (SQLException | IOException e) {
            throw new ClobSerializationException(e.getMessage(), e);
        }

        return writer.toString();
    }
}
