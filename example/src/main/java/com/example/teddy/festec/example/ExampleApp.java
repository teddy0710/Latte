package com.example.teddy.festec.example;

import android.app.Application;

import com.example.latte.app.Latte;
import com.example.latte.net.rx.AddCookieInterceptor;
import com.example.teddy.festec.example.event.TestEvent;
import com.example.latte.ec.datebase.DatebaseManager;
import com.example.latte.ec.icon.FontEcMoudle;
import com.example.latte.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by 张枫霖 on 2017/8/25
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcMoudle())
                .withLoaderDelayed(1000)
//                .withApiHost("http://127.0.0.1/")
                .withApiHost("http://116.196.95.67/RestServer/api/")
                .withInterceptr(new DebugInterceptor("text", R.raw.text))
                .withWechatAppId("")
                .withWechatAppSecret("")
                .withJavacriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                //添加Cookie拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptr(new AddCookieInterceptor())
                .configure();
        initStetho();

        DatebaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
