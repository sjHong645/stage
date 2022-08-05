package org.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 AWS가 실행됨
                                                        // 항상 톰캣을 설치할 필요가 없게 됨
        // 스프링 부트로 만들어진 Jar 파일로 실행하면 됨
    }
}
