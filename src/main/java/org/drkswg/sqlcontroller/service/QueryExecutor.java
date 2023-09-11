package org.drkswg.sqlcontroller.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QueryExecutor {
    EntityManager entityManager;

    public <T> List<T> executeNativeQueryGeneric(String statement, String mapping, Map<String, T> params) {
        for (var entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) value = "'" + value + "'";
            statement = statement.replace(":" + entry.getKey(), String.valueOf(value));
        }

        Query query = entityManager.createNativeQuery(statement, mapping);

        return query.getResultList();
    }
}
