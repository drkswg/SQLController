package org.drkswg.sqlcontroller.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.drkswg.sqlcontroller.mappings.Query;
import org.drkswg.sqlcontroller.mappings.ResultSet;
import org.drkswg.sqlcontroller.mappings.SingleValueResult;
import org.drkswg.sqlcontroller.repository.QueryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QueryController {
    QueryRepository queryRepository;

    @PostMapping ("/get_result_set")
    public ResultSet getResultSet(@RequestBody Query query) {
        return queryRepository.getResultSet(query.getQuery());
    }

    @PostMapping ("/get_single_value")
    public SingleValueResult getSingleValue(@RequestBody Query query) {
        return queryRepository.getSingleValue(query.getQuery());
    }
}
