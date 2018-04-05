package com.zhihu.openbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * 上传头像
 * Created by Administrator on 2018/4/5.
 */
public class UploadApplication {

    public static final String DRIVER_NAME = "webdriver.chrome.driver";

    public static final String DRIVER_APPLICATION_NAME = "chromedriver.exe";

    private static WebDriver webDriver = null;

    private static WebDriverWait webDriverWait = null;

    private static String LOGIN_IN_URL = "https://www.zhihu.com/signin";

    static {
        System.getProperties().setProperty(DRIVER_NAME, DRIVER_APPLICATION_NAME);
        webDriver = new ChromeDriver();
        //全局隐式等待
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //显示等待控制对象
        webDriverWait = new WebDriverWait(webDriver,10);
    }

    public static void main(String[] args) {
        webDriver.get(LOGIN_IN_URL);
        webDriver.findElement(By.name("username")).sendKeys("17090477836");
        webDriver.findElement(By.name("password")).sendKeys("WJctZ4BIgl");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("SignFlow-submitButton"))).click();

        //设置
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.className("AppHeader-profileEntry"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("我的主页"))).click();

//        webDriverWait.until(ExpectedConditions.elementToBeClickable(
//                By.className("UserAvatar-inner"))).click();


        //调用 input file 上传文件
        webDriver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[1]/input"))
                .sendKeys("c:\\touxiang.jpg");////*[@id="ProfileHeader"]/div/div[2]/div/div[1]/input

        webDriver.findElement(By.xpath("/html/body/div[3]/div/span/div/div[2]/div/div[2]/div/div[3]/button")).click();
    }


}
