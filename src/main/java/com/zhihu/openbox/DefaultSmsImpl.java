package com.zhihu.openbox;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Pattern;

public class DefaultSmsImpl implements ISms{

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
            StatusLine statusLine = execute.getStatusLine();
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
            result = EntityUtils.toString(execute.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
