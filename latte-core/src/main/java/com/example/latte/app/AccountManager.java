package com.example.latte.app;

import com.example.latte.util.storage.LattePreference;

/**
 * Created by 张枫霖 on 2017-08-30
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    //用于保存用户登录状态
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    //获取用户的登录状态
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }


}
