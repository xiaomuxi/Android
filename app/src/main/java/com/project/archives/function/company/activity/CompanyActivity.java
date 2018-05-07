package com.project.archives.function.company.activity;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.dao.manager.UsersManager;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.dao.manager.ZancunsManager;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.company.fragment.CompanyCaseInvesFragment;
import com.project.archives.function.company.fragment.CompanyEndingsFragment;
import com.project.archives.function.company.fragment.CompanyLettersFragment;
import com.project.archives.function.company.fragment.CompanyVerificationsFragment;
import com.project.archives.function.company.fragment.CompanyZancunsFragment;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by inrokei on 2018/5/4.
 */

public class CompanyActivity extends BaseActivity implements View.OnClickListener{

    private int company_type_index = 0 ;
    private int company_index = 0;
    private String[] companyTypeArr=new String[]{"全部单位类型", "党群部门", "行政部门", "街镇", "区管企业", "区管事业单位", "人大政协半、法院检察院"};
    private String[] companyArr=new String[]{"全部单位"};
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
    private TextView tv_qingkuangshuoming;

    private OrderPagerAdapter pagerAdapter;

    private NiceSpinner ns_company_type, ns_company;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.fragment_company);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("单位查询");
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        mFragmentManager = getSupportFragmentManager();

        ns_company_type = (NiceSpinner) findViewById(R.id.ns_company_type);
        ns_company = (NiceSpinner) findViewById(R.id.ns_company);

        et_start = (EditText) findViewById(R.id.et_start);
        et_end = (EditText) findViewById(R.id.et_end);
        btn_reset = (Button) findViewById(R.id.btn_reset);

        tv_qingkuangshuoming = (TextView) findViewById(R.id.tv_qingkuangshuoming);

        et_start.setOnClickListener(this);
        et_end.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        ns_company_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                company_type_index = position;
                company_index = 0;
                initCompany();
                initCount();
                initQKSM();
                List<String> comapnys = new LinkedList<>(Arrays.asList(companyArr));
                ns_company.attachDataSource(comapnys);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtils.i(TAG, "onNothingSelected--type-");
            }
        });
        ns_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                company_index = position;
                initCompany();

                initCount();
                initQKSM();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtils.i(TAG, "onNothingSelected---");
            }
        });

        initCompany();
        List<String> comapnytypes = new LinkedList<>(Arrays.asList(companyTypeArr));
        List<String> comapnys = new LinkedList<>(Arrays.asList(companyArr));
        ns_company_type.attachDataSource(comapnytypes);
        ns_company.attachDataSource(comapnys);
        initQKSM();

        initDatePickerAndDialog();
        initViewPgaer();
    }


    private void initDatePickerAndDialog() {
        mCalendar = Calendar.getInstance();
        startYear = mCalendar.get(Calendar.YEAR);
        startMonth = mCalendar.get(Calendar.MONTH);
        startDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        endYear = mCalendar.get(Calendar.YEAR);
        endMonth = mCalendar.get(Calendar.MONTH);
        endDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                startDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (!checkStartAndEndTimeRight()) {
                    UIUtils.showToastSafe("起始时间不能小于结束时间！");
                    return;
                }

                et_start.setText(startDate);
                initCount();
            }
        }, startYear, startMonth, startDay);

        endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (!checkStartAndEndTimeRight()) {
                    UIUtils.showToastSafe("结束时间不能小于起始时间！");
                    return;
                }

                et_end.setText(endDate);
                initCount();
            }
        }, endYear, endMonth, endDay);


        startDatePicker = startDatePickerDialog.getDatePicker();
        endDatePicker = endDatePickerDialog.getDatePicker();

        startDatePicker.setMaxDate(new Date().getTime());
        endDatePicker.setMaxDate(new Date().getTime());
    }


    private void initCompany() {
        List<String> companyList = UsersManager.getInstance().getAllCompany(company_type_index);
        ArrayList<String> newCompanyList = new ArrayList<>();
        newCompanyList.add("全部单位");
        for (int i = 0; i < companyList.size(); i++) {
            String companyName = companyList.get(i);
            companyName = StringUtils.isEmpty(companyName) ? "未知单位" : companyName;
            newCompanyList.add(companyName);
        }

        companyArr = newCompanyList.toArray(new String[0]);
    }

    private void initCount() {
        long caseinvesCount = CaseInvesManager.getInstance().getCountByQuery(getCompany(), startDate, endDate);
        long verificationsCount = VerificationsManager.getInstance().getCountByQuery(getCompany(), startDate, endDate);
        long lettersCount = LettersManager.getInstance().getCountByQuery(getCompany(), startDate, endDate);
        long endingsCount = EndingsManager.getInstance().getCountByQuery(getCompany(), startDate, endDate);
        long zancunsCount = ZancunsManager.getInstance().getCountByQuery(getCompany(), startDate, endDate);

        tv_caseinves.setText(getResources().getString(R.string.list_caseinves_title, String.valueOf(caseinvesCount)));
        tv_verifications.setText(getResources().getString(R.string.list_verifications_title, String.valueOf(verificationsCount)));
        tv_letters.setText(getResources().getString(R.string.list_letters_title, String.valueOf(lettersCount)));
        tv_endings.setText(getResources().getString(R.string.list_endings_title, String.valueOf(endingsCount)));
        tv_zancuns.setText(getResources().getString(R.string.list_zancuns_title, String.valueOf(zancunsCount)));


        BaseLoadingFragment loadingFragment = (BaseLoadingFragment) pagerAdapter.getItem(viewPager.getCurrentItem());
        loadingFragment.show();
    }

    private void initQKSM() {
        String company = company_index == 0 ? "" : companyArr[company_index];
        long ganbuCount = UsersManager.getInstance().getQuGuanGanBuCount(company, company_type_index);
        long dangDaiBiaoCount = UsersManager.getInstance().getDangDaiBiaoCount(company, company_type_index);
        long quweiCount = UsersManager.getInstance().getQuWeiWeiYuanCount(company, company_type_index);
        long jiWeiCount = UsersManager.getInstance().getJiWeiCount(company, company_type_index);
        long renDaCount = UsersManager.getInstance().getRenDaCount(company, company_type_index);
        long zhengXieCount = UsersManager.getInstance().getZhengXieCount(company, company_type_index);
        long nvRenCount = UsersManager.getInstance().getNvRenCount(company, company_type_index);
        long notHanZuCount = UsersManager.getInstance().getNotHanZuCount(company, company_type_index);

        String head = companyTypeArr[company_type_index] + "  " + company;

        tv_qingkuangshuoming.setText(getResources().getString(R.string.txt_qingkuangshuoming1, head, String.valueOf(ganbuCount),
                String.valueOf(dangDaiBiaoCount), String.valueOf(quweiCount), String.valueOf(jiWeiCount), String.valueOf(renDaCount),
                String.valueOf(zhengXieCount), String.valueOf(nvRenCount), String.valueOf(notHanZuCount)));
    }

    public void resetDate() {
        startDate = "";
        endDate = "";
        startDatePickerDialog.getDatePicker().updateDate(startYear, startMonth, startDay);
        endDatePickerDialog.getDatePicker().updateDate(endYear, endMonth, endDay);

        et_start.setText(startDate);
        et_end.setText(endDate);

        initCount();
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

    private void initViewPgaer() {

        ll_list = (LinearLayout) findViewById(R.id.ll_list);
        viewPager = (ViewPager) findViewById(R.id.vp_list);
        tv_caseinves = (TextView) findViewById(R.id.tv_caseinves);
        tv_verifications = (TextView) findViewById(R.id.tv_verifications);
        tv_letters = (TextView) findViewById(R.id.tv_letters);
        tv_endings = (TextView) findViewById(R.id.tv_endings);
        tv_zancuns = (TextView) findViewById(R.id.tv_zancuns);

        tv_caseinves.setText(getResources()
                .getString(R.string.list_caseinves_title, "0"));
        tv_verifications.setText(getResources()
                .getString(R.string.list_verifications_title, "0"));
        tv_letters.setText(getResources()
                .getString(R.string.list_letters_title, "0"));
        tv_endings.setText(getResources()
                .getString(R.string.list_endings_title, "0"));
        tv_zancuns.setText(getResources()
                .getString(R.string.list_zancuns_title, "0"));


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
        pagerAdapter = new OrderPagerAdapter(mFragmentManager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(mPageChangeListener);
        tv_caseinves.setSelected(true);
    }

    public String getCompany() {
        if (company_index == 0) {
            return null;
        }
        return companyArr[company_index];
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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
                    tv_caseinves.setText(getResources()
                            .getString(R.string.list_caseinves_title, count1+""));
                    break;
                case "COMPANY_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tv_verifications.setText(getResources()
                            .getString(R.string.list_verifications_title, count2+""));
                    break;
                case "COMPANY_LETTERS":
                    int count3 = (int) event.getContent();
                    tv_letters.setText(getResources()
                            .getString(R.string.list_letters_title, count3+""));
                    break;
                case "COMPANY_ENDINGS":
                    int count4 = (int) event.getContent();
                    tv_endings.setText(getResources()
                            .getString(R.string.list_endings_title, count4+""));
                    break;
                case "COMPANY_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tv_zancuns.setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
            }
        }
    }
}
