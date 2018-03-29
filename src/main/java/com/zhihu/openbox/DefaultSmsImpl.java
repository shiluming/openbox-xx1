package com.zhihu.openbox;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultSmsImpl implements ISms{

    private final String token = "00437994798866bf1957c6a80dde9273d49f84df";

    private String GET_PHONE_NO = "http://api.fxhyd.cn/UserInterface.aspx?" +
            "action=getmobile&token="+token+"&itemid=891&excludeno=";

    private String GET_PHONE_SMS = "http://api.fxhyd.cn/UserInterface.aspx?" +
            "action=getsms&token="+token+"&itemid=891&mobile=%s&release=1";

    private Pattern pattern = Pattern.compile("\\d+");

    CloseableHttpClient build = HttpClientBuilder.create().build();

    public String getPhoneNO() {

        HttpGet get = new HttpGet(GET_PHONE_NO);
        String phone = "";
        try {
            CloseableHttpResponse execute = build.execute(get);
            HttpEntity entity = execute.getEntity();
            StatusLine statusLine = execute.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (200 != statusCode) {
                return "";
            }
            String result = EntityUtils.toString(entity);
            String[] split = result.split("\\|");
            phone = split[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phone;
    }

    public String getPhoneSms(String phoneNo) {
        String format = String.format(GET_PHONE_SMS, phoneNo);
        String result = "";
        HttpGet get = new HttpGet(format);
        try {
            CloseableHttpResponse execute = build.execute(get);
            StatusLine statusLine = execute.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            result = EntityUtils.toString(execute.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//        ISms iSms = new DefaultSmsImpl();
//        String phoneNO = iSms.getPhoneNO();
//        iSms.getPhoneSms(phoneNO);
//        String source = "【知乎】创建帐号的验证码是 942684，10 分钟内有效。";
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(source);
//        if (matcher.find()) {
//            String group = matcher.group();
//            System.out.println(group);
//        }
    }
}
