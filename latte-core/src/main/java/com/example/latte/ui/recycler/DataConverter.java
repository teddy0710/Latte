package com.example.latte.ui.recycler;

import android.text.Html;

import java.util.ArrayList;

/**
 * Created by 张枫霖 on 2017-09-05
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter(String json) {
        this.mJsonData = json;
    }

    protected String getmJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
