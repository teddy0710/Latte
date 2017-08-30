package com.example.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.app.AccountManager;
import com.example.latte.ec.datebase.DatebaseManager;
import com.example.latte.ec.datebase.UserProfile;

/**
 * Created by 张枫霖 on 2017-08-30
 */

public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatebaseManager.getInstance().getDao().insert(userProfile);

        //已经注册成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatebaseManager.getInstance().getDao().insert(userProfile);

        //已经注册成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
