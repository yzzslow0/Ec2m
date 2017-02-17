package com.example.ec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListTestActivity extends AppCompatActivity {

    private ListView list;

    private TestAdaptera adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);

        list = (ListView) findViewById(R.id.list);

        list.setAdapter(adapter);

    }
}
