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
                "d://yzm.jpg");

        if (StringUtils.isNoneEmpty(byPost)) {
            int i = byPost.indexOf("<Result>");
            int i1 = byPost.indexOf("</Result>");
            logger.info("第三方打码平台返回的结果：{}", byPost);
            //添加第三方验证码返回错误判断
            if (i1 <= i+8) {
                iSms.releasePhoneNo(phoneNo);
                logger.info("第三方返回验证码错误，不处理，跳过注册!!");
                return;
            }
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
        logger.info("开始生成图片。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
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

    public boolean downImage(String baseStr) {

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
            logger.info("生成图片：路径：D://yzm.jpg");
            //生成jpeg图片
            String imgFilePath = "d://yzm.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            logger.info("生成图片完成。。。。。。。。。。。。。。");
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
