package com.zhihu.openbox;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    private static ISms iSms = new DefaultSmsImpl();

    private static Pattern pattern = Pattern.compile("\\d+");

    public static void main(String[] args) throws InterruptedException {
        final String url = "https://www.zhihu.com/signup?next=%2F";
        System.getProperties().setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        //全局隐式等待
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //设定网址
        webDriver.get(url);
        //显示等待控制对象
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,10);
        //接收易站手机号码
        String phoneNO = iSms.getPhoneNO();
        if (StringUtils.isEmpty(phoneNO)) {
            webDriver.close();
            System.out.println("获取手机号码发生错误，请检查");
            return ;
        }
        System.out.println("获取的手机号码是：" + phoneNO);
        //等待输入框可用后输入账号密码
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.name("phoneNo")))
                .sendKeys(phoneNO);
//        webDriverWait.until(
//                ExpectedConditions.elementToBeClickable(By.name("loginPassword")))
//                .sendKeys("00243050172qq");
        //点击获取验证码
        webDriver.findElement(By.className("SignFlow-smsInputButton")).click();
        //等待2秒用于页面加载，保证Cookie响应全部获取。
        Thread.sleep(2000);
        //获取Cookie并打印
        Set<Cookie> cookies = webDriver.manage().getCookies();
        Iterator iterator=cookies.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        //接收短信验证码
        String phoneSms = retry(phoneNO);
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.name("digits")))
                .sendKeys(phoneSms);
        //点击注册
        webDriver.findElement(By.className("Register-submitButton")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("fullname")))
                .sendKeys("registeraaaaaa");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("password")))
                .sendKeys("123456789qq");

        //点击注册
        webDriver.findElement(By.className("Register-getIn")).click();

        //填写身份
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/div[1]/button")).click();

        //进入知乎
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();

        //关闭WebDriver,否则并不自动关闭

        webDriver.close();
    }

    public static String retry(String phoneNo) throws InterruptedException {
        int i = 1;
        for (;;) {
            String phoneSms = iSms.getPhoneSms(phoneNo);
            System.out.println("第 " + i + " 次 retry 获取验证码，返回值是:" + phoneSms);
            if (phoneSms.contains("success")) {
                return parseSMSCode(phoneSms);
            }
            i++;
            Thread.sleep(2000);
        }
    }

    public static String parseSMSCode(String phoneSms) {
        Matcher matcher = pattern.matcher(phoneSms);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
}
