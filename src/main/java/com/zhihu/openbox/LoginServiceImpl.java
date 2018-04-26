package com.zhihu.openbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiluming on 2018/4/22.
 */
public class LoginServiceImpl implements LoginService{

    private static String LOGIN_URL = "https://www.zhihu.com/signup";

    public static final String DRIVER_NAME = "webdriver.chrome.driver";

    public static final String DRIVER_APPLICATION_NAME = "chromedriver.exe";

    public static final int DEFAULT_WAIT_SECOND = 10;

    public static final String VOTE_URL = "https://www.zhihu.com/question/265965273/answer/305355998";

    protected WebDriver webDriver;

    protected WebDriverWait webDriverWait;

    public static String DEFAULT_PASWORD = "";

    public static String DEFAULT_USERNAME = "";

    static {
        System.getProperties().setProperty(DRIVER_NAME, DRIVER_APPLICATION_NAME);
    }
    public LoginServiceImpl() {
        webDriver = new ChromeDriver();
        //全局隐式等待
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_SECOND, TimeUnit.SECONDS);

        //显示等待控制对象
        webDriverWait = new WebDriverWait(webDriver,DEFAULT_WAIT_SECOND);

    }

    public void login(String userName, String password) {
        webDriver.get(VOTE_URL);
        voteUp();
        toLoginBtn();
        fillNameAndPsw(userName, password);
        submitLogin();

        voteUp();
    }

    public void logout() {

    }

    /**
     * 跳转到登陆按钮
     */
    private void toLoginBtn() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/span/div/div[2]/div/div/div/div[2]/div[2]/span"))).click();
    }

    /**
     * 填充账号和密码
     * @param name
     * @param password
     */
    private void fillNameAndPsw(String name, String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys(name);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys(password);
    }

    /**
     * 点击登陆
     */
    private void submitLogin() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("SignFlow-submitButton"))).click();
    }

    /**
     * 点赞
     */
    private void voteUp() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("VoteButton--up"))).click();
    }

    /**
     * 反对
     */
    private void voteDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("VoteButton--down"))).click();
    }

    public static void main(String[] args) {
        //https://www.zhihu.com/question/265965273/answer/305355998
        String https = "https://www.zhihu.com/question/265965273/answer/305355998";
        String http = "https://www.zhihu.com/question/265965273/answer/305355998";
        String pattern = "www\\.zhihu\\.com/question/\\d+/answer/\\d+";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(https);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
