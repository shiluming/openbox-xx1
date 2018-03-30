package com.zhihu.openbox;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultHandle extends AbstractHandleService{

    public static Pattern pattern = Pattern.compile("\\d+");

    public static final String REGISTER_URL = "https://www.zhihu.com/signup?next=%2F";

    public static final int DEFAULT_WAIT_SECOND = 10;

    public static final String DRIVER_NAME = "webdriver.chrome.driver";

    public static final String DRIVER_APPLICATION_NAME = "chromedriver.exe";

    public static final int DEFAULT_RETRY_COUNT = 10;

    static {
        System.getProperties().setProperty(DRIVER_NAME, DRIVER_APPLICATION_NAME);
    }

    public DefaultHandle() {
        webDriver = new ChromeDriver();
        //全局隐式等待
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_SECOND, TimeUnit.SECONDS);

        //显示等待控制对象
        webDriverWait = new WebDriverWait(webDriver,DEFAULT_WAIT_SECOND);
        //短信接口
        iSms = new DefaultSmsImpl();
    }

    public void handler() throws InterruptedException, IOException {
        String phoneNo = iSms.getPhoneNO();
        fillPhoneNO(phoneNo);
        //接收短信验证码
        String sms = retry(phoneNo, DEFAULT_RETRY_COUNT);
        fillPhoneSms(sms);
        String password = fillUserDetails();
        fillIdandFinish();
        Utils.appendFileContents(phoneNo, password, Application.DEFAULT_DATA_FILE);
    }

    /**
     * 填充手机号码并且发送验证码
     * @param phoneNo
     */
    public void fillPhoneNO(String phoneNo) {
        //设定网址
        webDriver.get(REGISTER_URL);
        //等待输入框可用后输入账号
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.name("phoneNo")))
                .sendKeys(phoneNo);
        //点击获取验证码
        webDriver.findElement(By.className("SignFlow-smsInputButton")).click();
    }

    /**
     * 填充验证码并且点击注册
     * @param sms
     */
    public void fillPhoneSms(String sms) {
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.name("digits")))
                .sendKeys(sms);
        //点击注册
        webDriver.findElement(By.className("Register-submitButton")).click();
    }



    /**
     * 填写用户姓名和密码
     */
    public String fillUserDetails() {
        String password = Utils.generatePsw(10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("fullname")))
                .sendKeys("aguiagui");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("password")))
                .sendKeys(password);
        return password;
    }

    /**
     * 点击进入知乎
     */
    public void fillIdandFinish() {
        //点击注册
        webDriver.findElement(By.className("Register-getIn")).click();
        //填写身份
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/div[1]/button")).click();
        //进入知乎
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();
    }

    /**
     * 重试获取验证码机制
     *
     * @param phoneNo
     * @param number
     * @return
     * @throws InterruptedException
     */
    public String retry(String phoneNo, int number) throws InterruptedException {
        for (int i = 0; i < number; i++) {
            String phoneSms = iSms.getPhoneSms(phoneNo);
            logger.info("手机号码: {} , 第 {} 次获取验证码，返回值是: {}", phoneNo, i, phoneSms);
            if (phoneSms.contains("success")) {
                return parseSmsCode(phoneSms);
            }
            Thread.sleep(2000);
        }
        return "";
    }


    /**
     * 解析短信验证码
     *
     * @param phoneSms
     * @return
     */
    private String parseSmsCode(String phoneSms) {
        Matcher matcher = pattern.matcher(phoneSms);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

}
