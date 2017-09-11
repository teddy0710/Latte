package com.example.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte.delegates.web.chromeclient.WebChromeClientImpl;
import com.example.latte.delegates.web.client.WebViewClientImpl;
import com.example.latte.delegates.web.route.RouteKeys;
import com.example.latte.delegates.web.route.Router;

/**
 * Created by 张枫霖 on 2017-09-11
 */

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        //通过父类的方法获取webview并把整个webview 当做一个主布局
        return getWebView();
    }

    /**
     * view准备好的时候
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟web跳转
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitalizer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
