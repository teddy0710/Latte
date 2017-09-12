package com.example.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegates.bottom.BottomItemDelegate;
import com.example.latte.ec.R;
import com.example.latte.ec.main.sort.content.ContentDelegate;
import com.example.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by 张枫霖 on 2017-09-03
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //初始化左侧分类栏
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        //加载该分类栏Fragment到父布局中的制定位置
        getSupportDelegate().loadRootFragment(R.id.vertail_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一（replace适用于重复加载）
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
