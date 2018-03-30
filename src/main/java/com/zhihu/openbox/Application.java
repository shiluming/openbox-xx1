package com.zhihu.openbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static final String DEFAULT_DATA_FILE = "data.log";

    public static void main(String[] args) {
        DefaultHandle handle = new DefaultHandle();
        try {
            Utils.appendFileLine(DEFAULT_DATA_FILE);
            int i = 1;
            for (;;) {
                handle.handler();
                logger.info("第 {} 次注册完成", i);
                i ++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
