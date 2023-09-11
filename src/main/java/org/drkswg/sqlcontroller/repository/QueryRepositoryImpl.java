package org.drkswg.sqlcontroller.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.drkswg.sqlcontroller.mappings.ResultSet;
import org.drkswg.sqlcontroller.mappings.SingleValueResult;
import org.drkswg.sqlcontroller.dao.QueryDAO;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QueryRepositoryImpl implements QueryRepository {
    QueryDAO queryDAO;

    @Override
    public SingleValueResult getSingleValue(String query) {
        return queryDAO.getSingleValue(query);
    }

    @Override
    public ResultSet getResultSet(String query) {
        return queryDAO.getResultSet(query);
    }
}
