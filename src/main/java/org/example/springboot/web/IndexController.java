package org.example.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 머스테치 스타터 덕분에
    // 컨트롤러에서 문자열을 반환할 때 "앞의 경로"와 "뒤의 파일 확장자"가 자동 지정됨

    // 앞의 경로 : src/main/resources/template
    // 뒤의 파일 확장자 : .mustache

    // 여기서는 "/"만 던져줘도 src/main/resources/template/index.mustache를 반환해줌
    @GetMapping("/")
    public String index() {
        return "index";
    }


}
