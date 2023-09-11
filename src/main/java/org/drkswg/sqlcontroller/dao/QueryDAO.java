package org.drkswg.sqlcontroller.dao;

import org.drkswg.sqlcontroller.mappings.ResultSet;
import org.drkswg.sqlcontroller.mappings.SingleValueResult;

public interface QueryDAO {
    SingleValueResult getSingleValue(String query);

    ResultSet getResultSet(String query);
}
