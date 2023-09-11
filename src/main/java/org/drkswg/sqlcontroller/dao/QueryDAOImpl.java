package org.drkswg.sqlcontroller.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.drkswg.sqlcontroller.mappings.ResultSet;
import org.drkswg.sqlcontroller.mappings.SingleValueResult;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
            String value = String.valueOf(resultSet.get(0));
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
        resultSet.setRows(resultList);

        return resultSet;
    }
}
