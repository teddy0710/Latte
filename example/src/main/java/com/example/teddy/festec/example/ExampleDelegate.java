package com.example.teddy.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.delegates.LatteDelegate;

/**
 * Created by 张枫霖 on 2017/8/27
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
