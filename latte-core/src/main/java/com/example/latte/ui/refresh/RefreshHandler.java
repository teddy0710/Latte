package com.example.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.example.latte.app.Latte;
import com.example.latte.net.RestClient;
import com.example.latte.net.callback.ISuccess;

/**
 * Created by 张枫霖 on 2017-09-05
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 2017-09-05 在这里做一些网络请求
                //网络请求结束时放入的方法，隐藏刷新图标
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder().url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }).build().get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
