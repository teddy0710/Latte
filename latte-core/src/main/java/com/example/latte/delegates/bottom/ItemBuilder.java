package com.example.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by 张枫霖 on 2017-08-31
 */

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    //简单工厂模式
    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bottomTabBean, BottomItemDelegate bottomItemDelegate) {
        ITEMS.put(bottomTabBean, bottomItemDelegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS) {
        ITEMS.putAll(ITEMS);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return ITEMS;
    }
}
