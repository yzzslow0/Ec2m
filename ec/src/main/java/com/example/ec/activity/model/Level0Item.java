package com.example.ec.activity.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ec.adapter.ExpandableItemAdapter;

/**
 * Created by yzz on 2017/5/9.
 */

public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {

    public String title;
    public String content;
    public boolean isSelected;

    public Level0Item(String title, String content, boolean isSelected) {
        this.title = title;
        this.content = content;
        this.isSelected = isSelected;
    }

    @Override
    public int getLevel() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
