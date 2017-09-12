package com.example.latte.net.rx;

import android.support.annotation.NonNull;

import com.example.latte.util.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 张枫霖 on 2017-09-12
 */

public final class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                        //给源生api请求附带上webview拦截下载的cookie
                        builder.addHeader("Cookie", s);
                    }
                });

        return chain.proceed(builder.build());
    }
}
