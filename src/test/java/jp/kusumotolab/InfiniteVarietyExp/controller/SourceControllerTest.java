package jp.kusumotolab.InfiniteVarietyExp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SourceControllerTest {
  @Autowired private MockMvc mockMvc;

  @Test
  void testEmpty() throws Exception {
    mockMvc.perform(get("/source")).andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  void test01() throws Exception {
    mockMvc
        .perform(get("/source").param("id", String.valueOf(1)))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
