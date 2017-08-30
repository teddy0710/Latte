package com.example.latte.ec.sign;

/**
 * Created by 张枫霖 on 2017-08-30
 */

public interface ISignListener {
    //登录成功的回调
    void onSignInSuccess();

    //注册成功的回调
    void onSignUpSuccess();
}
