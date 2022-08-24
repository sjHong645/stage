package org.example.springboot.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.example.springboot.web.recruitWebSite.JobKoreaSearchForTest.jobKoreaSearch_for_test;
import static org.example.springboot.web.recruitWebSite.SaramInSearchForTest.saramInSearch_for_test;
import static org.hamcrest.Matchers.is;

// @WebMvcTest(controllers = SearchController.class)
// 위 어노테이션을 걸었다면 mvc 관련 테스트가 있어야 함

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {

    // 출처 : https://nesoy.github.io/articles/2017-03/Selenium
    private WebDriver driver;

    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    final String WEB_DRIVER_PATH = "C:/devtool/chromedriver_win32/chromedriver.exe"; // 크롬 드라이버 경로

    // 테스트 전에 실행할 코드
    @Before
    public void setUp() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 단일한 메소드로 만들자
        // 속도 개선 출처 https://lotuus.tistory.com/108
        ChromeOptions options = new ChromeOptions();

        // options.addArguments("headless"); // 브라우저 안 띄움
        // 이걸 하니까 특정 값을 못읽어오는 상황 발생

        options.addArguments("--blink-settings=imagesEnabled=false"); // 이미지 로딩 차단

        driver = new ChromeDriver(options); // 드라이버 생성 및 설정
    }

    // 테스트 후에 실행할 코드
    @After
    public void tearDown() {
        driver.quit(); // 드라이버 종료
    }

    // 여기서 뭘 확인하도록 하면 좋을까.
    // 뭘 확인해야 제대로 채용 공고 리스트를 불러왔다고 할 수 있을까
    @Test
    public void saramInSearch_Completed() throws Exception{


        String company = "우아한형제들";
        String url = "https://www.saramin.co.kr/zf_user/search?searchword=" + company;

        driver.get(url);

        Thread.sleep(1000); // 1초만 대기하자. 동작이 너무 빨라서 숫자가 아니라 ... 이 먼저 크롤링된다.

        // 전체 검색 결과 개수가 저장되어 있는 위치
        WebElement total = driver.findElement(By.cssSelector("#sp_preview_total_cnt"));

        // total.getText()를 실행하면 전체 검색 결과 개수를 알아낼 수 있다.

        // 전체 검색 결과와
        // 실제 채용공고 개수가 같은지 비교
        Assert.assertThat(Integer.parseInt(total.getText().replace(",", "")),
                is(saramInSearch_for_test(url)));

    }

    @Test
    public void jobKoreaSearch_Completed() throws Exception {

        String company = "요기요";
        String url = "https://www.jobkorea.co.kr/Search/?stext=" + company;

        driver.get(url);

        Thread.sleep(1000);

        // 전체 검색 결과 개수가 저장되어 있는 위치
        WebElement total = driver.findElement(By.cssSelector("strong.dev_tot"));

        // 전체 검색 결과와
        // 실제 채용공고 개수가 같은지 비교
        Assert.assertThat(Integer.parseInt(total.getText().replace(",", "")),
                is(jobKoreaSearch_for_test(url)));


    }



}

