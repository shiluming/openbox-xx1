package com.zhihu.openbox;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultHandle extends AbstractHandleService{

    public static Pattern pattern = Pattern.compile("\\d+");

    public static final String REGISTER_URL = "https://www.zhihu.com/signup?next=%2F";

    public static final String ZHIHU_INDEX = "https://www.zhihu.com/";

    public static final int DEFAULT_WAIT_SECOND = 10;

    public static final String DRIVER_NAME = "webdriver.chrome.driver";

    public static final String DRIVER_APPLICATION_NAME = "chromedriver.exe";

    public static final int DEFAULT_RETRY_COUNT = 20;

    public static final String ERROR_PHONE = "10086";

    public static String[] DEFAULT_FILE_NAMES = null;

    public static final String DEFAULT_IMAGES = "images";

    public static final String BASE_DIR = "c:\\" + DEFAULT_IMAGES + "\\";

    public static Random DEFAULT_RANDOM = new Random();
    static {
        System.getProperties().setProperty(DRIVER_NAME, DRIVER_APPLICATION_NAME);
        DEFAULT_FILE_NAMES = Utils.getFileNames(BASE_DIR);
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
        if (ERROR_PHONE.equals(phoneNo)) {
            return ;
        }
        fillPhoneNO(phoneNo);
        boolean b2 = hashCaptcharContainer();
        if (b2) {
            //是否手机号码
            iSms.releasePhoneNo(phoneNo);
            boolean b = hasEnglishCaptchar();
            if (b) {
                logger.info("数字验证码显示中， 跳过注册");
                return;
            }
            boolean b1 = hasChineseCaptchar();
            if (b1) {
                logger.info("中文验证码显示中，跳过注册");
                return;
            }
        }
        Thread.sleep(1000);
        //点击获取验证码
        webDriver.findElement(By.className("SignFlow-smsInputButton")).click();
        //接收短信验证码
        String sms = retry(phoneNo, DEFAULT_RETRY_COUNT);
        if (StringUtils.isEmpty(sms)) {
            iSms.releasePhoneNo(phoneNo);
            logger.info("手机号码 {} 获取验证码次数超过规定次数，跳过", phoneNo);
            return;
        }
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
                .sendKeys(Utils.generateUserName(5));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("password")))
                .sendKeys(password);
        return password;
    }

    /**
     * 点击进入知乎
     */
    public void fillIdandFinish() throws InterruptedException {
        //点击注册
        webDriver.findElement(By.className("Register-getIn")).click();
        //填写身份
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/div[1]/button")).click();
        //进入知乎
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();
        Thread.sleep(2000);
        uploadAvatar();
        Thread.sleep(2000);
        //退出登录
        webDriver.get(ZHIHU_INDEX);
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();
        try {
            Thread.sleep(4000);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(
                    By.className("AppHeader-profileEntry"))).click();
        } catch (Exception e) {
            logger.info("发生预期错误，错误码：1008, e = {}", e);
            WebElement element = webDriver.findElement(By.className("AppHeader-profileEntry"));
            Thread.sleep(1000);
            if (element.isEnabled()) {
                logger.info("可用的状态");
                element.click();
            }
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("退出"))).click();
    }

    public void uploadAvatar() throws InterruptedException {
        logger.info("开始上传头像");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.className("AppHeader-profileEntry"))).click();
        //进入我的主页
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("我的主页"))).click();

//        webDriverWait.until(ExpectedConditions.elementToBeClickable(
//                By.className("UserAvatar-inner"))).click();
        int i = DEFAULT_RANDOM.nextInt(DEFAULT_FILE_NAMES.length);
        String defaultFileName = DEFAULT_FILE_NAMES[i];
        //调用 input file 上传文件
        webDriver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[1]/input"))
                .sendKeys(BASE_DIR + defaultFileName);////*[@id="ProfileHeader"]/div/div[2]/div/div[1]/input
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("/html/body/div[3]/div/span/div/div[2]/div/div[2]/div/div[3]/button")).click();
        //
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
            Thread.sleep(3000);
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

    /**
     * 判断是否有数字验证码
     * @return
     */
    private boolean hasEnglishCaptchar() {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.className("Captcha-englishImg"));
        }catch (Exception e) {
            element = null;
        }
        if (null == element) {
            return false;
        }
        return true;
    }

    private boolean hashCaptcharContainer() {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.className("SignFlow-captchaContainer"));
        }catch (Exception e) {
            element = null;
        }
        //是否可见
        boolean displayed = element.isDisplayed();
        if (displayed) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有中文验证码
     * @return
     */
    private boolean hasChineseCaptchar() {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.className("Captcha-chineseImg"));
        }catch (Exception e) {
            element = null;
        }
        if (null == element) {
            return false;
        }
        return true;
    }

    private void uploadImage() {
        // UserAvatarEditor-maskInner
        webDriverWait.until(ExpectedConditions
                .elementToBeClickable(By.className("UserAvatarEditor-maskInner"))).sendKeys();

    }

}
