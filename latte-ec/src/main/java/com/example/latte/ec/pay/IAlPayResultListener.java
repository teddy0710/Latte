package com.example.latte.ec.pay;

/**
 * Created by 张枫霖 on 2017-09-05
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
