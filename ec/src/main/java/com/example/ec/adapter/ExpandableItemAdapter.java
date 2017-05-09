package com.example.ec.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ec.R;
import com.example.ec.activity.model.Level0Item;
import com.example.ec.activity.model.Level1Item;
import com.example.ec.activity.model.RecycleEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by yzz on 2017/5/9.
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MultiItemEntity item) {

        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:

//                holder.setImageResource(R.id.iv_head, R.mipmap.head_img0);

                final Level0Item lv0 = (Level0Item)item;
                holder.setText(R.id.tv_item0_title, lv0.title)
                        .setText(R.id.tv_item0_content, lv0.content)
                        .setChecked(R.id.cb_item0,lv0.isSelected)
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });

                holder.itemView.findViewById(R.id.cb_item0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv0.isSelected){
                            lv0.isSelected=false;
                            for(int i=0;i<lv0.getSubItems().size();i++){
                                lv0.getSubItem(i).isSelected=false;
                            }
                        }else {
                            lv0.isSelected=true;
                            for(int i=0;i<lv0.getSubItems().size();i++){
                                lv0.getSubItem(i).isSelected=true;
                            }

                        }
                        EventBus.getDefault().post(new RecycleEvent());

                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item)item;
                holder.setText(R.id.tv_item1_title, lv1.title)
                        .setText(R.id.tv_item1_content, lv1.subTitle)
                        .setChecked(R.id.cb_item1,lv1.isSelected);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 1 item pos: " + pos);
                    }
                });

                holder.itemView.findViewById(R.id.cb_item1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv1.isSelected){
                            lv1.isSelected=false;
                        }else {
                            lv1.isSelected=true;
                        }
                    }
                });
                break;
        }

    }
}
