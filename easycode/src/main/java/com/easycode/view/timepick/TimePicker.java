package com.easycode.view.timepick;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easycode.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by yzz on 2016/6/21.
 */
public class TimePicker extends LinearLayout {
    private static final int UPDATE_TITLE_MSG = 0x111;
    private static final int UPDATE_WHEEL = 0x112;
    private static final int UPDATE_TODAY_WHELL = 0x113;
    private static final int INIT_UPDATE_TODAY_WHELL = 0x114;
    private static final int INIT_UPDATE_WHEEL = 0x115;
    private static final int UPDATE_NOTTODAY_WHEEL = 0x116;

    private TextView mPickerTitle;
    private WheelView mWheelDay;
    private WheelView mWheelHour;
    private WheelView mWheelMin;
    private TextView mCancelBtn;
    private TextView mConfirmBtn;
    private int mDay;
    private int mHour;
    private int mMin;
    private int mYear;
    private int mMonth;
    private int today, endday;
    private boolean IsToday, IsToolate;

    private Calendar mCalendar;
    private LinearLayout   linearLayout_min, linearLayout_hour;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TITLE_MSG: {
                    updateTitle();
                }
                break;

                case UPDATE_NOTTODAY_WHEEL: {
                    updatenottodayWheel();
                }
                break;
                case INIT_UPDATE_WHEEL: {
                    updateWheel(true);
                }
                break;
                case UPDATE_WHEEL: {
                    updateWheel(false);
                }
                break;
                case INIT_UPDATE_TODAY_WHELL: {
                    updatetodayWheel(true);
                }
                break;

                case UPDATE_TODAY_WHELL: {
                    updatetodayWheel(false);
                }
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

    public TimePicker(Context context) {
        this(context, null);
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.normal_time_picker, this);
        mPickerTitle = (TextView) findViewById(R.id.picker_title);
        mWheelDay = (WheelView) findViewById(R.id.day);
        mWheelHour = (WheelView) findViewById(R.id.hour);
        mWheelMin = (WheelView) findViewById(R.id.min);
        mConfirmBtn = (TextView) findViewById(R.id.confirm);
        mWheelDay.setOnSelectListener(mDayListener);
        mWheelHour.setOnSelectListener(mHourListener);
        mWheelMin.setOnSelectListener(mMinListener);
//        linearLayout_bottom = (LinearLayout) findViewById(R.id.linearLayout_bottom);
//
//        linearLayout_day = (LinearLayout) findViewById(R.id.linearLayout_day);
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
        mCalendar.setTimeInMillis(date);
        today = mCalendar.get(Calendar.DAY_OF_MONTH);
        endday = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mYear = mCalendar.get(Calendar.YEAR);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY) + 1;

//        GregorianCalendar mData = new GregorianCalendar();
//        int minute = mData.get(Calendar.MINUTE);
        mMin = 00;


        mWheelDay.setData(getDayData());//设置 日data
        mWheelHour.setData(getTodayHourData());//设置 小时data
        mWheelMin.setData(getMinData(true));//设置 分钟data

        mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        mHandler.sendEmptyMessage(UPDATE_WHEEL);
    }

    private void updateTitle() {
        if (mDay == -1) {
            mPickerTitle.setText("立即发货");

        } else {
            mPickerTitle.setText(getContext().getString(R.string.picker_title, mDay, mHour, 00));
            mPickerTitle.setText(mDay + "日" + mHour + "时" + mMin + "分");
        }


    }


    private WheelView.OnSelectListener mDayListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            IsToday = false;
            mWheelMin.setData(getMinData(false));//设置 分钟data
            if (position == 0) {

                mHandler.sendEmptyMessage(0);
                mDay = -1;
            } else {
                int selectday = Integer.valueOf(text.replace("日", ""));

                if (selectday == mCalendar.get(Calendar.DAY_OF_MONTH)) {
                    IsToday = true;
                    mWheelHour.setData(getTodayHourData());//设置 小时data
                    mWheelMin.setData(getMinData(false));//设置 分钟data

                    mHandler.sendEmptyMessage(UPDATE_TODAY_WHELL);
                } else {
                    mWheelHour.setData(getHourData());//设置 小时data

                    mHandler.sendEmptyMessage(UPDATE_NOTTODAY_WHEEL);
                }

                if (mCalendar.get(Calendar.DAY_OF_MONTH) + position - 1 >= endday) {
                    mMonth = mCalendar.get(Calendar.MONTH) + 1;
                    mYear = mCalendar.get(Calendar.YEAR);

                    if (mMonth + 1 > 12) {//如果大于12月
                        mYear++;
                        mMonth = 1;
                    } else {
                        mMonth++;
                    }

                    mDay = selectday;

                } else {
                    mMonth = mCalendar.get(Calendar.MONTH) + 1;
                    mYear = mCalendar.get(Calendar.YEAR);
                    mDay = mCalendar.get(Calendar.DAY_OF_MONTH) + position - 1;
                }
                mHandler.sendEmptyMessage(1);
            }
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        }


        @Override
        public void selecting(int day, String text) {

        }
    };
    private WheelView.OnSelectListener mHourListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            int nowHour = mCalendar.get(Calendar.HOUR_OF_DAY);

            if (mDay == mCalendar.get(Calendar.DAY_OF_MONTH)) {
                mHour = position + nowHour + 1;
            } else {
                mHour = position;
            }


            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        }

        @Override
        public void selecting(int day, String text) {
        }
    };
    private WheelView.OnSelectListener mMinListener = new WheelView.OnSelectListener() {
        @Override
        public void endSelect(int position, String text) {
            if (position == 0) {
                mMin = 00;
            } else {
                mMin = 30;
            }
            mHandler.sendEmptyMessage(UPDATE_TITLE_MSG);
        }

        @Override
        public void selecting(int day, String text) {
        }
    };


    private void updateWheel(boolean isInit) {
        if (isInit) {
            mHour = 0;
            mMin = 00;
        }
        mWheelDay.setDefault(1);
        mWheelHour.setDefault(0);
        mWheelMin.setDefault(0);
    }

    private void updatetodayWheel(boolean isInit) {

        if (isInit) {
            mHour = mCalendar.get(Calendar.HOUR_OF_DAY) + 1;
            mMin = 00;
        }
        mWheelHour.setDefault(0);
        mWheelMin.setDefault(0);
    }

    private void updatenottodayWheel() {
        mHour = 0;
        mMin = 00;
        mWheelHour.setDefault(0);
        mWheelMin.setDefault(0);
    }

    private ArrayList<String> getDayData() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("现在发货");

        for (int i = today; i <= today + 6; i++) {
//            if (i == today && mCalendar.get(Calendar.HOUR_OF_DAY) + 1 == 24) {
//
//            } else {
            if (i > endday) {

                return setNextMonth(list);

            }

            list.add(i + "日");
        }
//        }
        return list;
    }

    private ArrayList<String> setNextMonth(ArrayList<String> list) {


        for (int i = 1; i <= 6 - (endday - today); i++) {
            list.add(i + "日");
        }

        return list;
    }

    private ArrayList<String> getTodayHourData() {
        ArrayList<String> list = new ArrayList<String>();
        if (mCalendar.get(Calendar.HOUR_OF_DAY) + 1 >= 24) {
            list.add("请选明日");
            return list;
        } else {
            for (int i = mCalendar.get(Calendar.HOUR_OF_DAY) + 1; i < 24; i++) {
                list.add(i + "时");
            }
            return list;
        }

    }

    private ArrayList<String> getHourData() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            list.add(i + "时");
        }
        return list;
    }

    private ArrayList<String> getMinData(boolean b) {
        ArrayList<String> list = new ArrayList<>();
        if (b) {
            if (mCalendar.get(Calendar.HOUR_OF_DAY) + 1 >= 24) {
                list.add("请选明日");
                IsToolate = true;
                return list;
            }
        }
        if (mCalendar.get(Calendar.HOUR_OF_DAY) + 1 >= 24 && IsToday) {
            list.add("请选明日");
            IsToolate = true;
            return list;
        } else {
            list.add("00分");
            list.add("30分");
            IsToolate = false;
            return list;
        }


    }


    public TextView getmCancelBtnBtn() {
        return mCancelBtn;
    }

    public String getTime() {
        if (IsToolate) {
            return"太晚了";
        } else {
            if (mDay == -1) {
                return "立即发货";
            } else {
//            mMonth = mCalendar.get(Calendar.MONTH);
//            mMonth++;
                if (mMin < 10) {

                    return mYear + "-" + mMonth + "-" + mDay + " " + mHour + ":0" + mMin + ":00";
                } else {
                    return mYear + "-" + mMonth + "-" + mDay + " " + mHour + ":" + mMin + ":00";
                }

            }
        }

    }
}