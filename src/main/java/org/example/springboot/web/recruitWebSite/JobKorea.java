package org.example.springboot.web.recruitWebSite;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class JobKorea {

    // 파라미터 str = 검색 url
    // 일단, 채용정보 1페이지에 있는 내용만 출력하도록 하자.
    // ex) 2페이지 - Page_No = 2
    public static String jobKoreaSearch(String str) throws IOException {

        String result = "";

        WebDriver driver;

        final String WEB_DRIVER_ID = "webdriver.chrome.driver";
        final String WEB_DRIVER_PATH = "C:/devtool/chromedriver_win32/chromedriver.exe"; // 크롬 드라이버 경로

        String base_url; // 크롤링 할 url

        // System Property 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 로딩 차단

        driver = new ChromeDriver(options);
        base_url = str + "&Page_No="; // 페이지 번호 매개변수를 추가한 url

        List<WebElement> titleList = new LinkedList<WebElement>();
        List<WebElement> companyList = new LinkedList<WebElement>();

        try {
            int pageNumber = 1;

            while(true) {
                // get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
                // 반복문이 돌아올 때 마자 페이지 번호를 1씩 증가시킨다.
                driver.get(base_url + (pageNumber++));


                // #content > div > div > div.cnt-list-wrap >
                // div > div.recruit-info > div.lists > div > div.list-default
                // ul > li:nth-child(1) > div > div.post-list-corp > a
                titleList = driver.findElements(
                        By.cssSelector("#content > div > div > div.cnt-list-wrap > div > div.recruit-info > div.lists > div > div.list-default > ul > li:nth-child(1) > div > div.post-list-corp > a"));

                companyList = driver.findElements(
                        By.cssSelector("#content > div > div > div.cnt-list-wrap > div > div.recruit-info > div.lists > div > div.list-default > ul > li:nth-child(1) > div > div.post-list-info > a"));

                // 더 이상 검색결과가 없을 때까지 반복한다.
                if(titleList.size() == 0) break;

                try {
                    for(int i = 0; i < titleList.size(); i++) {

                        // {}를 3개 쓰면서 <br>을 추가해줌으로써 줄 바꿈이 가능해짐
                        result += "채용 공고 = " + titleList.get(i).getText()
                                + " "
                                + " 회사 = " + companyList.get(i).getText() + "<br>";

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        driver.close();

        return result;
    }
}
