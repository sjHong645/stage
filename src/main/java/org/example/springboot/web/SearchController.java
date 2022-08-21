package org.example.springboot.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.example.springboot.web.recruiteWebSite.SaramIn;

@Controller
public class SearchController {

    // 간단한 실험
    /*@GetMapping("/search")
    public String search(@RequestParam("company") String company) {

        return "multiSearch " + company;
    }*/

    // 검색해서 search 를 통해 company 매개변수까지 전달했다면
    // 어떤 동작을 해야 할까.
    // 기업 채용 공고가 한 눈에 들어오도록 하고 싶다.
    // 여기서 다양한 수작질을 하면 되겠다.

    // 일단 company 입력은 받음
    @GetMapping("/multiSearch")
    public String multiSearch(@RequestParam("company") String company, Model model) throws IOException {

        // return을 해야 화면에 출력된다.
        // multiSearch.mustache로 넘어간다.

        // 여기서 작업한 내용을 mutliSearch.mustache에 전달만 해주면 되지 않을까?

        // {{name}}에 attributeValue를 출력한다.
        model.addAttribute("name", "홍승준");

        // 여기서 results에 다가 결과값을 저장하면 화면에 출력 가능

        // 파라미터로 company를 전달했음

        String saramIn = "https://www.saramin.co.kr/zf_user/search?searchword=" + company;
        String jobKorea = "https://www.jobkorea.co.kr/Search/?stext=" + company;
        String wanted = "https://www.wanted.co.kr/search?query=" + company;

        model.addAttribute("saramIn", saramIn);
        model.addAttribute("jobKorea", jobKorea);
        model.addAttribute("wanted", wanted);

        model.addAttribute("saramInList", SaramIn.saramInSearch(saramIn));

        return "multiSearch";
    }



}



