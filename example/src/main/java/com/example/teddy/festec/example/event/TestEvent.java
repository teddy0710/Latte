package com.example.teddy.festec.example.event;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.latte.delegates.web.event.Event;
import com.example.latte.util.log.LatteLogger;


/**
 * Created by 张枫霖 on 2017-09-11
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            getWebView().post(new Runnable() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    //原生调用web
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
