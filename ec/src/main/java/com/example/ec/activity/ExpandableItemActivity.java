package com.example.ec.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ec.R;
import com.example.ec.activity.model.Level0Item;
import com.example.ec.activity.model.Level1Item;
import com.example.ec.activity.model.RecycleEvent;
import com.example.ec.adapter.ExpandableItemAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class ExpandableItemActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ExpandableItemAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private ArrayList<MultiItemEntity> res = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expandable_item);

        EventBus.getDefault().register(this);//订阅

        initUI();

    }

    private void initUI() {
        mRecyclerView =(RecyclerView)findViewById(R.id.rv);
        list = generateData();
        adapter = new ExpandableItemAdapter(list);

        final LinearLayoutManager manager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        adapter.expandAll();

    }



    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 5;
        int lv1Count = 3;
//        int personCount = 5;

//        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
//        Random random = new Random();


        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item( i + " Level 0", "subtitle of " + i,false);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 " + j, "(no animation)",false,i);

                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }

    @Subscribe
    public void onEventBus(RecycleEvent event){

        boolean isAllselected =  false;
        int super_position = 0;
        if (event.getState()==RecycleEvent.LEVEL_1){
            Level0Item mlv0 = null;
            for (int i=0;i<res.size();i++){
                if (res.get(i).getItemType()==ExpandableItemAdapter.TYPE_LEVEL_0){

                    if (super_position==event.getPosition()){
                        mlv0  = (Level0Item) res.get(i);
                        break;
                    }
                    super_position++;
                }
            }

            for (int i=0;i<mlv0.getSubItems().size();i++){

                if (!mlv0.getSubItems().get(i).isSelected){
                    isAllselected = false;
                    break;
                }else {
                    isAllselected = true;
                }
            }
            if (isAllselected){
                mlv0.isSelected=true;
            }else {
                mlv0.isSelected=false;
            }

        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//订阅
    }
}
