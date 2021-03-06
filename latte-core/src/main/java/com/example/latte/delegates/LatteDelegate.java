package com.example.latte.delegates;

/**
 * Created by 张枫霖 on 2017/8/27
 */

public abstract class LatteDelegate extends BaseDelegate {
    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
