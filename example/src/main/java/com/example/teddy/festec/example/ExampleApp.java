package com.example.teddy.festec.example;

import android.app.Application;

import com.example.latte.app.Latte;

/**
 * Created by 张枫霖 on 2017/8/25
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://")
                .configure();
    }
}
