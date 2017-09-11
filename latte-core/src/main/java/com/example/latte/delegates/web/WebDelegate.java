package com.example.latte.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.latte.app.ConfigKeys;
import com.example.latte.app.Configurator;
import com.example.latte.app.Latte;
import com.example.latte.delegates.LatteDelegate;
import com.example.latte.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * webView 的基础类
 * Created by 张枫霖 on 2017-09-11
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitalizer {

    private WebView mWebView = null;
    //弱引用，防止内存泄漏
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {
    }

    //子类必须实现该抽象方法
    public abstract IWebViewInitalizer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        //获取传入的url
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    /**
     * 初始化webview
     */
    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        //避免重复初始化和内存泄漏判断
        if (mWebView != null) {
            mWebView.removeAllViews();//清除所有view
            mWebView.destroy();//销毁
        } else {
            final IWebViewInitalizer initalizer = setInitializer();
            if (initalizer != null) {
                //用代码new WebView不容易引起内存泄漏
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUENE);
                mWebView = webViewWeakReference.get();
                mWebView = initalizer.initWebView(mWebView);
                mWebView.setWebViewClient(initalizer.initWebViewClient());
                mWebView.setWebChromeClient(initalizer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                //完成初始化，可以用了的标记
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!!!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    /**
     * 对外提供获取webview 的方法
     *
     * @return WebView
     */
    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webview is null!!!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("mUrl is null!!!");
        }
        return mUrl;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
