package org.example.springboot.web;

import java.io.IOException;

import org.example.springboot.web.recruitWebSite.Wanted;
import org.example.springboot.web.recruitWebSite.JobKorea;
import org.example.springboot.web.recruitWebSite.SaramIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    // 일단 company 입력은 받음
    @GetMapping("/multiSearch")
    public String multiSearch(@RequestParam("company") String company, Model model) throws IOException {

        // return을 해야 화면에 출력된다.
        // multiSearch.mustache로 넘어간다.


        // 파라미터로 company를 전달했음
        String saramIn = "https://www.saramin.co.kr/zf_user/search?searchword=" + company;
        String jobKorea = "https://www.jobkorea.co.kr/Search/?stext=" + company;
        String wanted = "https://www.wanted.co.kr/search?query=" + company;

        model.addAttribute("saramIn", saramIn);
        model.addAttribute("jobKorea", jobKorea);
        model.addAttribute("wanted", wanted);

        model.addAttribute("saramInList", SaramIn.saramInSearch(saramIn));
        model.addAttribute("jobKoreaList", JobKorea.jobKoreaSearch(saramIn));



        return "multiSearch";
    }



}



