package com.zhihu.openbox;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandleService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected ISms iSms;

    protected WebDriver webDriver;

    protected WebDriverWait webDriverWait;

    protected void closeWebDriver() {
        webDriver.close();
    }

    public ISms getiSms() {
        return iSms;
    }

    public void setiSms(ISms iSms) {
        this.iSms = iSms;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public void setWebDriverWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }
}