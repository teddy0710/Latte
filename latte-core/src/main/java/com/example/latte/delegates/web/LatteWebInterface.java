package com.example.latte.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.latte.delegates.web.event.Event;
import com.example.latte.delegates.web.event.EventManager;
import com.example.latte.util.log.LatteLogger;

/**
 * 和源生进行交互
 * Created by 张枫霖 on 2017-09-11
 */

final class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    //用简单工厂包装
    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    //必须添加该注解
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");

        final Event event = EventManager.getInstance().createEvent(action);
        LatteLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
