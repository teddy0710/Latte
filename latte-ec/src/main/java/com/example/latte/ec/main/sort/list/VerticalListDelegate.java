package com.example.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegates.LatteDelegate;
import com.example.latte.ec.R;

/**
 * Created by 张枫霖 on 2017-09-08
 */

public class VerticalListDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
