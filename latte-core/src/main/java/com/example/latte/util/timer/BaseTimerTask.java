package com.example.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by 张枫霖 on 2017/8/29
 */

public class BaseTimerTask extends TimerTask {
    private ItimerListener mItimerListener = null;

    public BaseTimerTask(ItimerListener timerListener) {
        this.mItimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mItimerListener != null) {
            mItimerListener.onTimer();
        }

    }
}
