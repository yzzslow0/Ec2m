package com.example.ec.newapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ec.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initUI();
        setRecycler();



    }

    private void setRecycler() {
        // 设置recyclerView的布局管理
        // LinearLayoutManager -> ListView风格
        // GridLayoutManager -> GridView风格
        // StaggeredGridLayoutManager -> 瀑布流风格
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initUI() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);

    }
}
