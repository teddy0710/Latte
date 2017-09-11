package com.example.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 张枫霖 on 2017-09-11
 */

public interface IWebViewInitalizer {
    //初始化WebView,传入目标webView
    WebView initWebView(WebView webView);

    //针对浏览器本身控制
    WebViewClient initWebViewClient();

    //针对页面控制
    WebChromeClient initWebChromeClient();

}
