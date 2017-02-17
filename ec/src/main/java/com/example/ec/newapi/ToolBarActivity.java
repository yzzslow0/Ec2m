package com.example.ec.newapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.ec.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);

        toolbar.setNavigationIcon(R.mipmap.back_normal_bule);//设置导航栏图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("Title");//设置主标题
        toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
//                    Toast.makeText(ToolBarActivity.this , "" , Toast.LENGTH_SHORT).show();
                    Log.e("Test","1");
                } else if (menuItemId == R.id.action_notification) {
//                    Toast.makeText(ToolBarActivity.this , R.string.menu_notifications , Toast.LENGTH_SHORT).show();
                    Log.e("Test","2");
                } else if (menuItemId == R.id.action_item1) {
//                    Toast.makeText(ToolBarActivity.this , R.string.item_01 , Toast.LENGTH_SHORT).show();
                    Log.e("Test","3");
                } else if (menuItemId == R.id.action_item2) {
//                    Toast.makeText(ToolBarActivity.this , R.string.item_02 , Toast.LENGTH_SHORT).show();
                    Log.e("Test","4");
                }
                return true;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
