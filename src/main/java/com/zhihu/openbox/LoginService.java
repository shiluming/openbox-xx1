package com.zhihu.openbox;

/**
 * Created by shiluming on 2018/4/22.
 */
public interface LoginService {

    /**
     * 登陆
     * @param userName
     * @param password
     */
    void login(String userName, String password);

    /**
     * 退出
     */
    void logout();

}
