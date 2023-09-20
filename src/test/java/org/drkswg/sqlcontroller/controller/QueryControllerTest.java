package org.drkswg.sqlcontroller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.drkswg.sqlcontroller.mapping.Query;
import org.drkswg.sqlcontroller.mapping.ResultSet;
import org.drkswg.sqlcontroller.mapping.SingleValueResult;
import org.drkswg.sqlcontroller.repository.QueryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(QueryController.class)
class QueryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private QueryRepository queryRepository;

    @Test
    void getResultSetTest() throws Exception {
        Query query = new Query("SELECT * FROM test");
        ResultSet resultSet = new ResultSet();

        when(queryRepository.getResultSet(query.getQuery())).thenReturn(resultSet);

        mvc.perform(MockMvcRequestBuilders.post("/get_result_set")
                        .content(new ObjectMapper().writeValueAsString(query))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(resultSet)));
    }

    @Test
    void getSingleValueTest() throws Exception {
        Query query = new Query("SELECT single_value FROM test");
        SingleValueResult result = new SingleValueResult();

        when(queryRepository.getSingleValue(query.getQuery())).thenReturn(result);

        mvc.perform(post("/get_single_value")
                        .content(new ObjectMapper().writeValueAsString(query))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(result)));
    }
}
