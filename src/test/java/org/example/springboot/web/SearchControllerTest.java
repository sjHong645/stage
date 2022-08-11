package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;
    // private TestRestTemplate restTemplate;


    // 테스트 코드 제대로 짜야함
    @Test
    public void search_completed() throws Exception {

        String value = "company";

        mvc.perform(get("/multiSearch")
                .param("company", value))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company",is(value)));

        // String body = this.restTemplate.getForObject("/multiSearch?" + "company=" + company, String.class);
        // String body = this.restTemplate.getForObject("/multiSearch?" + param + "=" + value, String.class);


        /*mvc.perform(get("/multiSearch")
                .param("company", company))
                .andExpect(status().isOk())
                .andExpect(content().string("multiSearch " + company));*/
        // assertThat(body).contains("hello multiSearch");

    }


}
