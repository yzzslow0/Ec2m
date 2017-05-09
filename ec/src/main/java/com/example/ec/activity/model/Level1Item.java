package com.example.ec.activity.model;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ec.adapter.ExpandableItemAdapter;

/**
 * Created by yzz on 2017/5/9.
 */

public class Level1Item implements MultiItemEntity {
    public String title;
    public String subTitle;
    public boolean isSelected;

    public Level1Item(String title, String subTitle, boolean isSelected) {
        this.title = title;
        this.subTitle = subTitle;
        this.isSelected = isSelected;
    }


    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}