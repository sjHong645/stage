package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void mainPage_loaded() {

        // when
        // TestRestTemplate을 통해 "/"로 호출했을 때
        // index.mustache에 포함된 코드들이 있는지 확인
        String body = this.restTemplate.getForObject("/", String.class);

        // then
        // assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
        assertThat(body).contains("채용 공고 동시 검색");
    }

    // 검색을 위한 테스트를 꼭 만들자
    /*@Test
    public void search_completed() {

        // when
        // TestRestTemplate을 통해 "/"로 호출했을 때
        // index.mustache에 포함된 코드들이 있는지 확인
        String body = this.restTemplate.getForObject("/", String.class);


        assertThat(body).contains("검색");


    }*/

}
