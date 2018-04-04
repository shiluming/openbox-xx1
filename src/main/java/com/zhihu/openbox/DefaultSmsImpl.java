package com.zhihu.openbox;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DefaultSmsImpl implements ISms{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String token = "00437994798866bf1957c6a80dde9273d49f84df";

    private String GET_PHONE_NO = "http://api.fxhyd.cn/UserInterface.aspx?" +
            "action=getmobile&token="+token+"&itemid=891&excludeno=";

    private String GET_PHONE_SMS = "http://api.fxhyd.cn/UserInterface.aspx?" +
            "action=getsms&token="+token+"&itemid=891&mobile=%s";

    CloseableHttpClient build = HttpClientBuilder.create().build();

    public String getPhoneNO() {
        HttpGet get = new HttpGet(GET_PHONE_NO);
        String phone = "";
        try {
            CloseableHttpResponse execute = build.execute(get);
            HttpEntity entity = execute.getEntity();
            String result = EntityUtils.toString(entity);
            String[] split = result.split("\\|");
            if (null == split || split.length == 1) {
                logger.info("第三方短信通道返回数据错误 value = {}", result);
                return "10086";
            }
            phone = split[1];
        } catch (IOException e) {
            logger.info("获取第三方短信通道发生错误，请检查账号余额，网络是否正常， 错误原因 e = {}", e);
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
            result = EntityUtils.toString(execute.getEntity(), "utf-8");
        } catch (IOException e) {
            logger.info("解析第三方短信通道验证码发生错误，请检查网络是否正常，错误原因 e = {}", e);
            e.printStackTrace();
        }
        return result;
    }
}
