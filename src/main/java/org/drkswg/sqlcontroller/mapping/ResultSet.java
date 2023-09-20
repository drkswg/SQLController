package org.drkswg.sqlcontroller.mapping;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResultSet {
    List<Map<String, Object>> rows;
}
