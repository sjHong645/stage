package org.example.springboot.web;

import java.io.IOException;
import java.util.List;

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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

        model.addAttribute("saramInList", saramInSearch(saramIn, company));
        // model.addAttribute("jobKoreaList", saramInSearch(saramIn));
        // model.addAttribute("wantedList", saramInSearch(saramIn));

        return "multiSearch";
    }

    // 파라미터 str = 검색 url
    // 일단, 채용정보 1페이지에 있는 내용만 출력하도록 하자.
    // 채용정보 파라미터는 recruitPage. company 뒤에 &로 이어서 전달하면 된다.
    // ex) 2페이지 - recruitPage = 2
    public String saramInSearch(String str, String company) throws IOException {

        String result = "";

        WebDriver driver;

        final String WEB_DRIVER_ID = "webdriver.chrome.driver";
        final String WEB_DRIVER_PATH = "C:/devtool/chromedriver_win32/chromedriver.exe"; // 크롬 드라이버 경로

        String base_url; // 크롤링 할 url

        // System Property 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        driver = new ChromeDriver();
        base_url = str;

        try {

            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(base_url);

            // item_recruit라는 이름의 클래스 리스트를 불러옴
            // List<WebElement> el1 = driver.findElements(By.className("job_tit"));

            // <div id = "recruit_info_list"> -> <div class = "content">
            // -> <div class = "item_recruit"> -> <div class = "area_job"> -> <h2 class = "job_tit">
            List<WebElement> titleList = driver.findElements(
                    By.cssSelector("#recruit_info_list > div.content > div.item_recruit > div.area_job > h2.job_tit"));

            List<WebElement> companyList = driver.findElements(
                    By.cssSelector("#recruit_info_list > div.content > div.item_recruit > div.area_corp > strong.corp_name"));

            System.out.println("titleList.size() = " + titleList.size());

            for(int i = 0; i < titleList.size(); i++) {

                // System.out.println(el1.get(i).getText());
                // {}를 3개 쓰면서 <br>을 추가해줌으로써 줄 바꿈이 가능해짐
                result += titleList.get(i).getText()
                        + " "
                        + companyList.get(i).getText() + "<br>";

            }


        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            driver.close();
        }


        return result;
    }



}



