package com.project.archives.function.main.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.company.fragment.CompanyCaseInvesFragment;
import com.project.archives.function.company.fragment.CompanyEndingsFragment;
import com.project.archives.function.company.fragment.CompanyLettersFragment;
import com.project.archives.function.company.fragment.CompanyVerificationsFragment;
import com.project.archives.function.company.fragment.CompanyZancunsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by inrokei on 2018/4/25.
 */

public class CompanyFragment extends BaseActivityFragment implements OnMenuSelectedListener, View.OnClickListener {

    private int company_type_index;
    private int compay_index;
    final String[] arr1=new String[]{"全部单位类型","党群部门","行政部门","区管企业","区管事业单位", "人大政协办法"};
    final String[] arr3=new String[]{"全部单位","闵行区1单位","松江区1单位","徐汇区2单位"};
    final String[] strings=new String[]{"选择单位类型","选择单位"};
    private DropDownMenu mMenu;

    private EditText et_start;
    private EditText et_end;
    private Button btn_reset;
    private ViewPager viewPager;
    private FragmentManager mFragmentManager;
    private int mPrePosition;
    private LinearLayout ll_list;
    private TextView tv_caseinves, tv_verifications,
            tv_letters, tv_endings, tv_zancuns;

    private Calendar mCalendar;
    private DatePickerDialog startDatePickerDialog;
    private int startYear, startMonth, startDay;
    private DatePickerDialog endDatePickerDialog;
    private int endYear, endMonth, endDay;
    private String startDate, endDate;
    private DatePicker startDatePicker, endDatePicker;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_company);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        mFragmentManager = getActivity().getSupportFragmentManager();

        mMenu = (DropDownMenu) view.findViewById(R.id.menu);
        et_start = (EditText) view.findViewById(R.id.et_start);
        et_end = (EditText) view.findViewById(R.id.et_end);
        btn_reset = (Button) view.findViewById(R.id.btn_reset);

        et_start.setOnClickListener(this);
        et_end.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

        initSelectMenu();
        initDatePickerAndDialog();
        initViewPgaer(view);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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
        mMenu.setmMenuBackColor(Color.WHITE);
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

    private void initViewPgaer(View view) {

        ll_list = (LinearLayout) view.findViewById(R.id.ll_list);
        viewPager = (ViewPager) view.findViewById(R.id.vp_list);
        tv_caseinves = (TextView) view.findViewById(R.id.tv_caseinves);
        tv_verifications = (TextView) view.findViewById(R.id.tv_verifications);
        tv_letters = (TextView) view.findViewById(R.id.tv_letters);
        tv_endings = (TextView) view.findViewById(R.id.tv_endings);
        tv_zancuns = (TextView) view.findViewById(R.id.tv_zancuns);

        tv_caseinves.setText(Html.fromHtml(getResources()
                .getString(R.string.list_caseinves_title, "0")));
        tv_verifications.setText(Html.fromHtml(getResources()
                .getString(R.string.list_verifications_title, "0")));
        tv_letters.setText(Html.fromHtml(getResources()
                .getString(R.string.list_letters_title, "0")));
        tv_endings.setText(Html.fromHtml(getResources()
                .getString(R.string.list_endings_title, "0")));
        tv_zancuns.setText(Html.fromHtml(getResources()
                .getString(R.string.list_zancuns_title, "0")));


        tv_caseinves.setOnClickListener(mClickListener);
        tv_verifications.setOnClickListener(mClickListener);
        tv_letters.setOnClickListener(mClickListener);
        tv_endings.setOnClickListener(mClickListener);
        tv_zancuns.setOnClickListener(mClickListener);


        FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
        FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
        FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
        OrderPagerAdapter pagerAdapter = new OrderPagerAdapter(mFragmentManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(mPageChangeListener);
        tv_caseinves.setSelected(true);
    }

    /**
     * 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建
     */
    public static class FragmentFactory {
        public static final int TAB_CASEINVES = 0; // 处分
        public static final int TAB_VERIFICATIONS = 1; // 初步核实
        public static final int TAB_LETTERS = 2; // 函询
        public static final int TAB_ENDINGS = 3;// 了结
        public static final int TAB_ZANCUNS = 4 ;

        //记录所有的fragment，防止重复创建
        public static final Map<Integer, BaseActivityFragment> mFragmentMap = new HashMap<>();

        public static BaseActivityFragment createFragment(int index) {
            BaseActivityFragment fragment = mFragmentMap.get(index);
            if (null == fragment) {
                switch (index) {
                    //处分类
                    case TAB_CASEINVES:
                        fragment = new CompanyCaseInvesFragment();
                        break;
                    //初步核实类
                    case TAB_VERIFICATIONS:
                        fragment = new CompanyVerificationsFragment();
                        break;
                    //谈话函询类
                    case TAB_LETTERS:
                        fragment = new CompanyLettersFragment();
                        break;
                    //了结类
                    case TAB_ENDINGS:
                        fragment = new CompanyEndingsFragment();
                        break;
                    //暂存类
                    case TAB_ZANCUNS:
                        fragment = new CompanyZancunsFragment();
                        break;
                    default:
                        break;
                }
                mFragmentMap.put(index, fragment);
            }
            return fragment;
        }
    }

    /**
     * ViewPager的适配器
     */
    public class OrderPagerAdapter extends FragmentPagerAdapter {

        public OrderPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public BaseActivityFragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    private final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageSelected(int position) {
            ViewGroup preView = (ViewGroup) ll_list.getChildAt(mPrePosition);
            ViewGroup curView = (ViewGroup) ll_list.getChildAt(position);
            curView.getChildAt(0).setSelected(true);
            preView.getChildAt(0).setSelected(false);
            BaseActivityFragment fragment = FragmentFactory.createFragment(position);
            if (fragment instanceof BaseLoadingFragment) {
                ((BaseLoadingFragment) fragment).show();
            }
            mPrePosition = position;
        }
    };

    private final View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_caseinves:
                    viewPager.setCurrentItem(FragmentFactory.TAB_CASEINVES);
                    break;
                case R.id.tv_verifications:
                    viewPager.setCurrentItem(FragmentFactory.TAB_VERIFICATIONS);
                    break;
                case R.id.tv_letters:
                    viewPager.setCurrentItem(FragmentFactory.TAB_LETTERS);
                    break;
                case R.id.tv_endings:
                    viewPager.setCurrentItem(FragmentFactory.TAB_ENDINGS);
                    break;
                case R.id.tv_zancuns:
                    viewPager.setCurrentItem(FragmentFactory.TAB_ZANCUNS);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentFactory.mFragmentMap.clear();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (StringUtils.notNull(event)) {
            switch (event.getTitle()) {
                case "COMPANY_CASEINVES":
                    int count1 = (int) event.getContent();
                    tv_caseinves.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_caseinves_title, count1+"")));
                    break;
                case "COMPANY_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tv_verifications.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_verifications_title, count2+"")));
                    break;
                case "COMPANY_LETTERS":
                    int count3 = (int) event.getContent();
                    tv_letters.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_letters_title, count3+"")));
                    break;
                case "COMPANY_ENDINGS":
                    int count4 = (int) event.getContent();
                    tv_endings.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_endings_title, count4+"")));
                    break;
                case "COMPANY_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tv_zancuns.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_zancuns_title, count5+"")));
                    break;
            }
        }
    }
}
