package com.example.ec.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.aigestudio.wheelpicker.WheelPicker;
import com.easycode.util.L;
import com.example.ec.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yzz on 2017/4/14.
 */

public class TimePickerNew extends LinearLayout {

    private WheelPicker mWheelYear;
    private WheelPicker mWheelMonth;
    private WheelPicker mWheelDay;
    private WheelPicker mWheelHour;
    private WheelPicker mWheelMin;
    private Calendar mCalendar, mCalendar2;
    private int thisDay, thisMonth, thisYear, thisHour, thisMin, daysCountOfMonth;

    public TimePickerNew(Context context) {
        super(context);
    }

    public TimePickerNew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.timepicker_new, this);

        mWheelYear = (WheelPicker) findViewById(R.id.wheel_year);
        mWheelMonth = (WheelPicker) findViewById(R.id.wheel_month);
        mWheelDay = (WheelPicker) findViewById(R.id.wheel_day);
        mWheelHour = (WheelPicker) findViewById(R.id.wheel_hour);
        mWheelMin = (WheelPicker) findViewById(R.id.wheel_min);

        //设置监听
        mWheelYear.setOnItemSelectedListener(mYearListener);
        mWheelMonth.setOnItemSelectedListener(mMonthListener);
        mWheelDay.setOnItemSelectedListener(mDayListener);

        mWheelYear.setMaximumWidthText("2014年----");
        mWheelMonth.setMaximumWidthText("12月----");
        mWheelDay.setMaximumWidthText("30日------");
        mWheelHour.setMaximumWidthText("60时-----");
        mWheelMin.setMaximumWidthText("60分-------");
        mWheelYear.hasSameWidth();
        mWheelMonth.hasSameWidth();
        mWheelDay.hasSameWidth();
        mWheelHour.hasSameWidth();
        mWheelYear.hasSameWidth();



        getData();
    }

    /**
     * 设置当前时间
     */
    private void getData() {
        mCalendar = Calendar.getInstance(Locale.CHINA);
        mCalendar2 = Calendar.getInstance();
        mCalendar.setTimeInMillis(new Date().getTime());
        thisDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        thisMonth = mCalendar.get(Calendar.MONTH) + 1;
        thisYear = mCalendar.get(Calendar.YEAR);
        thisHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        thisMin = mCalendar.get(Calendar.MINUTE);

        mCalendar2.set(Calendar.MONTH, thisMonth - 1);//再指定月份
        daysCountOfMonth = mCalendar2.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());//设置 日data
        mWheelHour.setData(getHourData());//设置 小时data
        mWheelMin.setData(getMinData(true));//设置 分钟data

//        Year=mYear;
//        Hour=mHour;
//        Day=mDay;
//        Month=mMonth;
//        Min=mMin;

        mWheelYear.setSelectedItemPosition(10);
        mWheelMonth.setSelectedItemPosition(thisMonth - 1);
        mWheelDay.setSelectedItemPosition(thisDay - 1);
        mWheelHour.setSelectedItemPosition(thisHour);
        mWheelMin.setSelectedItemPosition(Integer.valueOf(thisMin/5));

    }


    /**
     * Year 滑动监听
     */
    private WheelPicker.OnItemSelectedListener mYearListener = new WheelPicker.OnItemSelectedListener() {
        @Override
        public void onItemSelected(WheelPicker picker, Object data, int position) {
            Log.v("onYearListener", String.valueOf(position));
        }
    };

    /**
     * Month 滑动监听
     */
    private WheelPicker.OnItemSelectedListener mMonthListener = new WheelPicker.OnItemSelectedListener() {
        @Override
        public void onItemSelected(WheelPicker picker, Object data, int position) {
            Log.v("onMonthListener", String.valueOf(position));
        }
    };

    /**
     * Day 滑动监听
     */
    private WheelPicker.OnItemSelectedListener mDayListener = new WheelPicker.OnItemSelectedListener() {
        @Override
        public void onItemSelected(WheelPicker picker, Object data, int position) {
            Log.v("onDayListener", String.valueOf(position));
        }
    };

    /**
     * Year 数据
     */
    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = thisYear + 10; i > thisYear - 40; i--) {
            list.add(i + "年");
        }

        return list;
    }

    /**
     * Month 数据
     */
    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i + "月");
        }
        return list;
    }

    /**
     * Day 数据
     */
    private ArrayList<String> getDayData() {
        ArrayList<String> list = new ArrayList<>();
        if (daysCountOfMonth == 0) {
            for (int i = 1; i <= 31; i++) {
                list.add(i + "日");
            }
        } else {
            for (int i = 1; i <= daysCountOfMonth; i++) {
                list.add(i + "日");
            }
        }
        return list;
    }

    /**
     * Hour 数据
     */
    private ArrayList<String> getHourData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            list.add(i + "时");
        }
        return list;
    }


    /**
     * //     *  Min 数据
     * //
     */
    private ArrayList<String> getMinData(boolean b) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(i*5 + "分");
        }
        return list;
    }


}
