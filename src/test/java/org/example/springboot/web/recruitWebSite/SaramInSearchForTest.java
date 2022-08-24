package org.example.springboot.web.recruitWebSite;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// 테스트를 위한 사람인 검색 클래스
public class SaramInSearchForTest {

    // test 메소드는
    // 채용공고의 개수만 세도록 할 것이다
    public static int saramInSearch_for_test(String str) throws IOException {

        int result = 0;

        WebDriver driver;

        final String WEB_DRIVER_ID = "webdriver.chrome.driver";
        final String WEB_DRIVER_PATH = "C:/devtool/chromedriver_win32/chromedriver.exe"; // 크롬 드라이버 경로

        String base_url; // 크롤링 할 url

        // System Property 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 단일한 메소드로 만들자
        // 속도 개선 출처 https://lotuus.tistory.com/108
        ChromeOptions options = new ChromeOptions();

        // *[@id="recruit_info"]/div[2]/div/div[3]/button

        // options.addArguments("headless"); // 브라우저 안 띄움

        options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 로딩 차단

        driver = new ChromeDriver(options);
        base_url = str + "&recruitPage="; // 페이지 번호 매개변수를 추가한 url

        List<WebElement> titleList = new LinkedList<WebElement>();

        try {
            int pageNumber = 1;

            int prevCount = 0;
            while(true) {
                // get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
                // 반복문이 돌아올 때 마자 페이지 번호를 1씩 증가시킨다.
                // 한 페이지에 100개의 채용 공고가 나타나도록 함
                driver.get(base_url + (pageNumber++) + "&recruitPageCount=100");



                // item_recruit라는 이름의 클래스 리스트를 불러옴

                // <div id = "recruit_info_list"> -> <div class = "content">
                // -> <div class = "item_recruit"> -> <div class = "area_job"> -> <h2 class = "job_tit">
                titleList = driver.findElements(
                        By.cssSelector("#recruit_info_list > div.content > div.item_recruit > div.area_job > h2.job_tit"));

                // 더 이상 검색결과가 없을 때까지 반복한다.
                if(titleList.size() == 0) break;

                result += titleList.size();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        driver.close();

        return result;
    }

}
