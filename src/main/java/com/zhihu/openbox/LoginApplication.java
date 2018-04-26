package com.zhihu.openbox;

/**
 * Created by shiluming on 2018/4/22.
 */
public class LoginApplication {

    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();

        loginService.login("13463863323", "ZtXqwjPk1P");
    }
}
