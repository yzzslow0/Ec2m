package com.easycode.view.timepick;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.easycode.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by yzz on 2016/6/22.
 */
public class TimePicker_YMD extends LinearLayout {
    private static final int CHANGE_MOUTH_DATA = 0x113;
    private static final int CHANGE_DAY_DATA = 0x114;

    private TextView mPickerTitle;
    private WheelPicker mWheelYear;
    private WheelPicker mWheelMonth;
    private WheelPicker mWheelDay;
    private WheelPicker mWheelHour;
    private WheelPicker mWheelMin;

    private int mDay, Day;
    private int mHour, Hour;
    private int mMin, Min;
    private int mYear, Year;
    private int mMonth, Month;
    private int daysCountOfMonth;

    private Calendar mCalendar, mCalendar2;
    private LinearLayout linearLayout_min, linearLayout_hour;


    public TimePicker_YMD(Context context) {
        this(context, null);
    }

    public TimePicker_YMD(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.ymd_time_picker, this);
        mPickerTitle = (TextView) findViewById(R.id.picker_title);
        mWheelYear = (WheelPicker) findViewById(R.id.year);
        mWheelMonth = (WheelPicker) findViewById(R.id.month);
        mWheelDay = (WheelPicker) findViewById(R.id.day);
        mWheelHour = (WheelPicker) findViewById(R.id.hour);
        mWheelMin = (WheelPicker) findViewById(R.id.min);
//        mWheelDay.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener) mDayListener);
//        mWheelDay.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener) mDayListener);
//        mWheelHour.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener)mHourListener);
//        mWheelMin.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener) mMinListener);
//        mWheelYear.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener) mYearListener);
//        mWheelMonth.setOnItemSelectedListener((WheelPicker.OnItemSelectedListener) mMonthListener);
        mWheelYear.setCurtain(true);
        mWheelMonth.setCurtain(true);
        mWheelDay.setCurtain(true);
        mWheelHour.setCurtain(true);
        mWheelMin.setCurtain(true);


        linearLayout_min = (LinearLayout) findViewById(R.id.linearLayout_min);
        linearLayout_hour = (LinearLayout) findViewById(R.id.linearLayout_hour);


    }


    /**
     * set TimePicker date
     *
     * @param date
     */
    public void setDate(long date) {
        mCalendar = Calendar.getInstance(Locale.CHINA);
        mCalendar2 = Calendar.getInstance();
        mCalendar.setTimeInMillis(date);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mYear = mCalendar.get(Calendar.YEAR);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMin = mCalendar.get(Calendar.MINUTE);

        mCalendar2.set(Calendar.MONTH, mMonth-1);//再指定月份
        daysCountOfMonth = mCalendar2.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());//设置 日data
        mWheelHour.setData(getHourData());//设置 小时data
        mWheelMin.setData(getMinData(true));//设置 分钟data

        Year=mYear;
        Hour=mHour;
        Day=mDay;
        Month=mMonth;
        Min=mMin;

        mWheelYear.setSelectedItemPosition(10);
        mWheelMonth.setSelectedItemPosition(mMonth-1);
        mWheelDay.setSelectedItemPosition(mDay-1);
        mWheelHour.setSelectedItemPosition(mHour);
        mWheelMin.setSelectedItemPosition(mMin);


    }


    /**
     * Year 数据
     */
    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = mYear+10; i > mYear - 40; i--) {
            list.add(i + "年");
        }

        return list;
    }

    /**
     * mYear滑动监听
     */
    private WheelView.OnSelectListener mYearListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            Year = mYear - position+10;
            mCalendar2.set(Calendar.YEAR, Year);//先指定年份
//            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
            mHandler.sendEmptyMessage(CHANGE_MOUTH_DATA);
        }


        @Override
        public void selecting(int day, String text) {

        }
    };

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
     * mMonth滑动监听
     */
    private WheelView.OnSelectListener mMonthListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            Month = position + 1;
            mCalendar2.set(Calendar.MONTH, position);//再指定月份
            daysCountOfMonth = mCalendar2.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天

            mWheelDay.setData(getDayData());//设置 日data

            mHandler.sendEmptyMessage(CHANGE_DAY_DATA);
        }


        @Override
        public void selecting(int day, String text) {

        }
    };

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
     * mDay滑动监听
     */
    private WheelView.OnSelectListener mDayListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            Day = position + 1;
        }


        @Override
        public void selecting(int day, String text) {

        }
    };

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
     * mHour滑动监听
     */
    private WheelView.OnSelectListener mHourListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            Hour = position ;
        }

        @Override
        public void selecting(int day, String text) {
        }
    };


    /**
     * //     *  Min 数据
     * //
     */
    private ArrayList<String> getMinData(boolean b) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add(i + "分");
        }
        return list;
    }

    /**
     * mMin滑动监听
     */
    private WheelView.OnSelectListener mMinListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            Min = position ;
        }

        @Override
        public void selecting(int day, String text) {
        }
    };


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHANGE_MOUTH_DATA:
//                    mWheelMonth.setDefault(0);
                    break;

                case CHANGE_DAY_DATA:
//                    if(Day!=0&&mWheelDay.getListSize()>0)
//                        mWheelDay.setDefault(0);
                    break;

                case 0:
                    linearLayout_hour.setVisibility(View.GONE);
                    linearLayout_min.setVisibility(View.GONE);
                    break;
                case 1:
                    linearLayout_min.setVisibility(View.VISIBLE);
                    linearLayout_hour.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };

    public String getTime() {
        String result;
        String monthStr;
        String dayStr;
        String hourStr;
        String minStr;
        if(Month<10){
            monthStr = "0"+Month;
        }else {
            monthStr = ""+Month;
        }
        if(Day<10){
            dayStr = "0"+Day;
        }else {
            dayStr = ""+Day;
        }
        if(Hour<10){
            hourStr = "0"+Hour;
        }else {
            hourStr = ""+Hour;
        }
        if(Min<10){
            minStr = "0"+Min;
        }else {
            minStr = ""+Min;
        }
        result =  Year + "-" + monthStr + "-" + dayStr + " " + hourStr + ":" + minStr + ":00";
        return result;
    }

}