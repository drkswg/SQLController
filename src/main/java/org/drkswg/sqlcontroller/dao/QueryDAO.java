package org.drkswg.sqlcontroller.dao;

import org.drkswg.sqlcontroller.mapping.ResultSet;
import org.drkswg.sqlcontroller.mapping.SingleValueResult;

public interface QueryDAO {
    SingleValueResult getSingleValue(String query);

    ResultSet getResultSet(String query);
}
