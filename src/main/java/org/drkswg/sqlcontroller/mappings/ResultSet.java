package org.drkswg.sqlcontroller.mappings;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResultSet {
    List<Map<String, Object>> rows;
}
