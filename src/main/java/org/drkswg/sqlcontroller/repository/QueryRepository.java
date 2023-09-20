package org.drkswg.sqlcontroller.repository;

import org.drkswg.sqlcontroller.mapping.ResultSet;
import org.drkswg.sqlcontroller.mapping.SingleValueResult;

public interface QueryRepository {
    SingleValueResult getSingleValue(String query);

    ResultSet getResultSet(String query);
}
