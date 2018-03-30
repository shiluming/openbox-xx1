package com.zhihu.openbox;

import java.io.IOException;

public class Application {

    public static final String DEFAULT_DATA_FILE = "data.log";

    public static void main(String[] args) {
        DefaultHandle handle = new DefaultHandle();
        try {
            Utils.appendFileLine(DEFAULT_DATA_FILE);
            for (int i = 0; i < 10; i++) {
                handle.handler();
                System.out.println("第 [ " +i+ " ] 次注册完成");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end ..");

    }
}
