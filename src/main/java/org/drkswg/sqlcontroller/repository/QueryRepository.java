package org.drkswg.sqlcontroller.repository;

import org.drkswg.sqlcontroller.mappings.ResultSet;
import org.drkswg.sqlcontroller.mappings.SingleValueResult;

public interface QueryRepository {
    SingleValueResult getSingleValue(String query);

    ResultSet getResultSet(String query);
}
