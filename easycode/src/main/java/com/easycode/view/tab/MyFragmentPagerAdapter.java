package com.easycode.view.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yzz on 2017/2/27.
 */

public class MyFragmentPagerAdapter  extends FragmentPagerAdapter {

    private String[] mTitles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTitles, List<Fragment> fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}