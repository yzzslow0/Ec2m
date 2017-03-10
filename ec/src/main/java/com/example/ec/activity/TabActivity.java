package com.example.ec.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;

import com.easycode.view.tab.MyFragmentPagerAdapter;
import com.example.ec.R;
import com.example.ec.fragment.Fragment1;
import com.example.ec.fragment.Fragment2;
import com.example.ec.fragment.Fragment3;
import com.example.ec.fragment.Fragment4;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity implements
        Fragment1.OnFragmentInteractionListener,
        Fragment2.OnFragmentInteractionListener,
        Fragment3.OnFragmentInteractionListener,
        Fragment4.OnFragmentInteractionListener {


    private  MyFragmentPagerAdapter myFragmentPagerAdapter;

    private String[] mTitles = new String[]{"首页", "发现", "进货单","我的"};
    private List<Fragment> fragments = new ArrayList<>();

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        //初始化视图
        initViews();
    }

    private void initViews() {

        fragments.add(0,new Fragment1());
        fragments.add(1,new Fragment2());
        fragments.add(2,new Fragment3());
        fragments.add(3,new Fragment4());
        //使用适配器将ViewPager与Fragment绑定在一起
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),mTitles,fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
        four.setIcon(R.mipmap.ic_launcher);


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}