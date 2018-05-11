package com.zhihu.openbox;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLDecoder;
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

    public static CloseableHttpClient build = null;
    static {
        System.getProperties().setProperty(DRIVER_NAME, DRIVER_APPLICATION_NAME);
        DEFAULT_FILE_NAMES = Utils.getFileNames(BASE_DIR);
        build = HttpClientBuilder.create().build();
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

        boolean chinessImage = false;
        String phoneNo = iSms.getPhoneNO();
        if (ERROR_PHONE.equals(phoneNo)) {
            return ;
        }
        fillPhoneNO(phoneNo);
        if (true) {
            //是否手机号码
            boolean b = hasEnglishCaptchar();
            logger.info("判断是否是数字验证码：{}", b);
            if (b) {
//                iSms.releasePhoneNo(phoneNo); //数字验证码不释放手机
                logger.info("数字验证码显示中， 下载");
            } else {
                iSms.releasePhoneNo(phoneNo);
            }
            boolean b1 = hasChineseCaptchar();
            logger.info("判断是否是中文验证码：{}", b1);
            if (b1) {
                iSms.releasePhoneNo(phoneNo);
                logger.info("中文验证码显示中");
                chinessImage = true;
            }
        }
        //退出
        if (chinessImage) {
            return;
        }
        //下载图片
        //获取图片验证码
        String byPost = RuoKuai.createByPost("18011726319", "yy123456",
                "3040", "60", "103585", "2733fdbdeba74bc1bcfdbeefcc8040dc",
                "c://yzm.jpg");
        if (StringUtils.isNoneEmpty(byPost)) {
            int i = byPost.indexOf("<Result>");
            int i1 = byPost.indexOf("</Result>");
            logger.info("第三方打码平台返回的结果：{}", byPost);
            String yzm = byPost.substring(i + 8, i1);
            //填写验证码
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("captcha")))
                    .sendKeys(yzm);
        } else {
            iSms.releasePhoneNo(phoneNo);
            logger.info("获取第三方打码平台验证码错误， 跳过注册");
            return ;
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
        Utils.appendFileContents(phoneNo, password, Application.DEFAULT_DATA_FILE);
        fillIdandFinish();

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
        webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/div[1]/button")
        )).click();
//        webDriver.findElement(
//                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/div[1]/button")).click();
        ////*[@id="root"]/div/main/div/div[2]/div/div[2]/div[1]/button
        //进入知乎
        webDriver.findElement(
                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();
        Thread.sleep(2000);
        uploadAvatar();
        Thread.sleep(2000);
        //退出登录
        webDriver.get(ZHIHU_INDEX);
//        webDriver.findElement(
//                By.xpath("//*[@id=\"root\"]/div/main/div/div[2]/div/div[2]/button")).click();
        ////*[@id="Popover-52840-82433-toggle"]
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
        webDriver.findElement(By.xpath("//*[@id=\"ProfileHeader\"]/div/div[2]/div/div[1]/div/input"))////*[@id="ProfileHeader"]/div/div[2]/div/div[1]/div/input
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
            element = webDriver.findElement(By.className("Captcha-englishImg"));//
        }catch (Exception e) {
            logger.error("数字验证码不存在");
            element = null;
        }
        logger.info("数字验证码元素 img = {}", element);
        if (null == element) {
            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.info("获取图片属性之前睡眠 1 S");
            e.printStackTrace();
        }
        String text = element.getAttribute("alt");
        String src = element.getAttribute("src");
        logger.info("数字验证码元素存在，text = {}, src = {}", text, src);
        if (StringUtils.isEmpty(src)) {
            logger.info("数字验证码不能解析出 src， 跳过。。。");
            return false;
        }
        downImage(src);
        return true;
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
            logger.error("中文验证码不存在");
            element = null;
        }
        logger.info("中文验证码元素 img = {},", element);
        if (null == element) {
            return false;
        }
        return true;
    }

    public static boolean downImage(String baseStr) {

        //对字节数组字符串进行Base64解码并生成图片
        if (baseStr == null){ //图像数据为空
            return false;
        }
        String src = baseStr.substring(baseStr.indexOf(",")+1);
        baseStr = src.replaceAll("%0A", "");
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(baseStr);
            for(int i=0;i<b.length;++i)
            {
                if (b[i] < 0) {
                    b[i] +=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "c://yzm.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static void main(String[] args) {
        String ss = "data:image/jpg;base64,R0lGODdhlgA8AIcAAP7+/gAAAOjo6BcXFycnJ0hISNjY2MfHx/Pz83h4eKWlpWdnZ1ZWVoaGhjQ0%0ANJeXl7e3t7i4uKqqqjg4OMPDwwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACwAAAAAlgA8AEAI/wABCBxI%0AsKDBgwgTKlzIsKHDhxAjSpxIsaJCCQwiCADAsaPHjx8DiAzQwACAkyhTqlzJsqXLlzBjypy50kCA%0AmwEAAJAQoGcCAECDCgVgwEGAowEmIBgQoKkCAFCjSp1KtarVq1izSkVwYIEAAGDDigU7IECAAgDS%0A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%0A6dWvZ9/e/fvoAQEAOw==";
        String aa = "data:image/jpg;base64,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";
        try {
//            String src = URLDecoder.decode(ss,"utf-8");
            System.out.println(ss);
            downImage(ss);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
