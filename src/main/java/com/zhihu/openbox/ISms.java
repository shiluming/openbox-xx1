package com.zhihu.openbox;

public interface ISms {

    /**
     * 获取手机验证码
     * @return
     */
    String getPhoneNO();

    /**
     * 获取手机验证码
     * @return
     */
    String getPhoneSms(String phoneNo);
}
