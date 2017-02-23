package com.example.ec.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easycode.util.UiSizeUtil;
import com.example.ec.DataServer;
import com.example.ec.R;
import com.example.ec.StateHEHE;


/**
 * Created by yzz on 2017/2/21.
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<StateHEHE,BaseViewHolder> {


    public PullToRefreshAdapter() {
        super(R.layout.layout_list_item, DataServer.getSampleData(100));
    }

    @Override
    protected void convert(BaseViewHolder helper, StateHEHE item) {
        helper.addOnClickListener(R.id.name).addOnClickListener(R.id.password);
        switch (helper.getLayoutPosition()%3){
            case 0:
                UiSizeUtil.LayoutChange(helper.getConvertView(),UiSizeUtil.NO_CHANGE,300);
                break;
            case 1:
                UiSizeUtil.LayoutChange(helper.getConvertView(),UiSizeUtil.NO_CHANGE,300);
                break;
            case 2:
                UiSizeUtil.LayoutChange(helper.getConvertView(),UiSizeUtil.NO_CHANGE,200);
                break;
        }
        helper.setText(R.id.name,"啥玩意1");
        helper.setText(R.id.password,"啥玩意2");

    }

//    ClickableSpan clickableSpan = new ClickableSpan() {
//        @Override
//        public void onClick(View widget) {
//            ToastUtil.showShort("点了");
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            ds.setColor(ContextHolder.getContext().getResources().getColor(R.color.md_material_blue_800));
//            ds.setUnderlineText(true);
//        }
//    };
}
