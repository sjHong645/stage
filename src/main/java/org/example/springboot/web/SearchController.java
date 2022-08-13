package org.example.springboot.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    // public String multiSearch() {

        // 이건 콘솔에서나 출력되지 화면에서는 출력되지 않았다.

        /*System.out.println("사람인 결과");
        System.out.println("잡코리아 결과");
        System.out.println("원티드 결과");*/

        // return을 해야 화면에 출력된다.
        // multiSearch.mustache로 넘어간다.

        // 여기서 작업한 내용을 mutliSearch.mustache에 전달만 해주면 되지 않을까?

        // {{name}}에 attributeValue를 출력한다.
        // 관려
        model.addAttribute("name", "홍승준");

        String results = "";

        // 여기서 results에 다가 결과값을 저장하면 화면에 출력 가능

        // 파라미터로 company를 전달했음

        String saramIn = "https://www.saramin.co.kr/zf_user/search?searchword=" + company;
        String jobKorea = "https://www.jobkorea.co.kr/Search/?stext=" + company;
        String wanted = "https://www.wanted.co.kr/search?query=" + company;

        model.addAttribute("saramIn", saramIn);
        model.addAttribute("jobKorea", jobKorea);
        model.addAttribute("wanted", wanted);


        model.addAttribute("results", results);

        // 사람인 파싱
        Connection connSaramIn = Jsoup.connect(saramIn);

        Document document = connSaramIn.get(); // IOException 다뤄야 함

        // div 태그에 있는 클래스를 적으면 된다
        Elements jobTits = document.getElementsByClass("area_job");
        Element jobTit = jobTits.get(0); // class가 job_tit 인 것 중 0번째 값

        Elements corpNames = document.getElementsByClass("area_corp");
        Element corpName = corpNames.get(0);

        // area_job 클래스 아래에 있는 값 중에서 h2 태그 값 중 0번째 값
        String title = jobTit.getElementsByTag("h2").get(0).text();

        // area_corp 클래스 아래에 있는 값 중에서 strong 태그 값 중 0번째 값
        String content = corpName.getElementsByTag("strong").get(0).text();

        model.addAttribute("saramInList", title + "\n\n" + content);

        return "multiSearch";
    }
}

