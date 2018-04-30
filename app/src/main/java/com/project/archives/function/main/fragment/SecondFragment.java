package com.project.archives.function.main.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by inrokei on 2018/4/25.
 */

public class SecondFragment extends BaseActivityFragment implements OnMenuSelectedListener, View.OnClickListener {

    private int company_type_index;
    private int compay_index;
    final String[] arr1=new String[]{"全部单位类型","党群部门","行政部门","区管企业","区管事业单位", "人大政协办法"};
    final String[] arr3=new String[]{"全部单位","闵行区1单位","松江区1单位","徐汇区2单位"};
    final String[] strings=new String[]{"选择单位类型","选择单位"};
    private DropDownMenu mMenu;

    private EditText et_start;
    private EditText et_end;
    private Button btn_reset;

    private Calendar mCalendar;
    private DatePickerDialog startDatePickerDialog;
    private int startYear, startMonth, startDay;
    private DatePickerDialog endDatePickerDialog;
    private int endYear, endMonth, endDay;
    private String startDate, endDate;
    private DatePicker startDatePicker, endDatePicker;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_second);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {

        mMenu = (DropDownMenu) view.findViewById(R.id.menu);
        et_start = (EditText) view.findViewById(R.id.et_start);
        et_end = (EditText) view.findViewById(R.id.et_end);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);

        et_start.setOnClickListener(this);
        et_end.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

        initSelectMenu();
        initDatePickerAndDialog();
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }


    private void initDatePickerAndDialog() {
        mCalendar = Calendar.getInstance();
        startYear = mCalendar.get(Calendar.YEAR);
        startMonth = mCalendar.get(Calendar.MONTH);
        startDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        endYear = mCalendar.get(Calendar.YEAR);
        endMonth = mCalendar.get(Calendar.MONTH);
        endDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        startDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (!checkStartAndEndTimeRight()) {
                    UIUtils.showToastSafe("起始时间不能小于结束时间！");
                    return;
                }

                et_start.setText(startDate);
            }
        }, startYear, startMonth, startDay);

        endDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (!checkStartAndEndTimeRight()) {
                    UIUtils.showToastSafe("结束时间不能小于起始时间！");
                    return;
                }

                et_end.setText(endDate);
            }
        }, startYear, startMonth, startDay);


        startDatePicker = startDatePickerDialog.getDatePicker();
        endDatePicker = endDatePickerDialog.getDatePicker();

        startDatePicker.setMaxDate(new Date().getTime());
        endDatePicker.setMaxDate(new Date().getTime());
    }

    public void initSelectMenu() {
        mMenu.setmMenuCount(2);
        mMenu.setmShowCount(6);
        mMenu.setShowCheck(true);
        mMenu.setmMenuTitleTextSize(16);
        mMenu.setmMenuTitleTextColor(Color.parseColor("#777777"));
        mMenu.setmMenuListTextSize(16);
        mMenu.setmMenuListTextColor(Color.BLACK);
        mMenu.setmMenuBackColor(Color.parseColor("#eeeeee"));
        mMenu.setmMenuPressedBackColor(Color.WHITE);
        mMenu.setmMenuPressedTitleTextColor(Color.BLACK);

        mMenu.setmCheckIcon(R.drawable.ico_make);

        mMenu.setmUpArrow(R.drawable.arrow_up);
        mMenu.setmDownArrow(R.drawable.arrow_down);

        mMenu.setDefaultMenuTitle(strings);


        mMenu.setShowDivider(false);
        mMenu.setmMenuListBackColor(getResources().getColor(R.color.white));
        mMenu.setmMenuListSelectorRes(R.color.white);
        mMenu.setmArrowMarginTitle(20);

        mMenu.setMenuSelectedListener(this);
        List<String[]> items = new ArrayList<>();
        items.add(arr1);
        items.add(arr3);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }


    @Override
    public void onSelected(View view, int RowIndex, int ColumnIndex) {
        if (ColumnIndex == 0) {
            company_type_index = RowIndex;
        } else {
            compay_index = RowIndex;
        }

        //过滤筛选
//                setFilter();
    }

    public void resetDate() {
        startDate = "";
        endDate = "";
        startDatePickerDialog.getDatePicker();
        et_start.setText(startDate);
        et_end.setText(endDate);
    }

    public boolean checkStartAndEndTimeRight() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date startCurrentDate = format.parse(startDate);
            Date endCurrentDate = format.parse(endDate);

            return startCurrentDate.getTime() <= endCurrentDate.getTime();
        }
        catch (Exception e) {
            LogUtils.i(TAG, e.toString());
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_start:
                startDatePickerDialog.show();
                break;
            case R.id.et_end:
                endDatePickerDialog.show();
                break;
            case R.id.btn_reset:
                resetDate();
                break;
        }
    }
}
