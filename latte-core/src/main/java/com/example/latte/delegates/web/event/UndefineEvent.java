package com.example.latte.delegates.web.event;

import com.example.latte.util.log.LatteLogger;

/**
 * Created by 张枫霖 on 2017-09-11
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
