package com.project.archives.function.company.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.dao.manager.GiftsHandsManager;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.dao.manager.UsersManager;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.dao.manager.ZancunsManager;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.company.fragment.CompanyCaseInvesFragment;
import com.project.archives.function.company.fragment.CompanyEndingsFragment;
import com.project.archives.function.company.fragment.CompanyGiftsFragment;
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

    private EditText et_start;
    private EditText et_end;
    private Button btn_reset;
    private ViewPager viewPager;

    private Calendar mCalendar;
    private DatePickerDialog startDatePickerDialog;
    private int startYear, startMonth, startDay;
    private DatePickerDialog endDatePickerDialog;
    private int endYear, endMonth, endDay;
    private String startDate, endDate;
    private DatePicker startDatePicker, endDatePicker;
    private TextView tv_qingkuangshuoming;

    private NiceSpinner ns_company_type, ns_company;
    private TabLayout tl_bar;
    private List<String> tabIndicators;
    private final String[] indicators = new String[]{"处分类", "初核类", "函询类", "了结类", "暂存类", "三礼上交"};
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_company);
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
                List<String> companys = new LinkedList<>(Arrays.asList(companyArr));
                ns_company.attachDataSource(companys);
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
        List<String> companys = new LinkedList<>(Arrays.asList(companyArr));
        ns_company_type.attachDataSource(comapnytypes);
        ns_company.attachDataSource(companys);
        initQKSM();

        initDatePickerAndDialog();
        initViewPager();
        initTab();
    }

    private void initTab(){
        tabIndicators = Arrays.asList(indicators);
        tl_bar = (TabLayout) findViewById(R.id.tl_bar);
        tl_bar.setTabMode(TabLayout.MODE_SCROLLABLE);
        tl_bar.setTabTextColors(Color.parseColor("#999999"), Color.parseColor("#267cfc"));
        tl_bar.setSelectedTabIndicatorColor(Color.parseColor("#267cfc"));
        ViewCompat.setElevation(tl_bar, 10);
        tl_bar.setupWithViewPager(viewPager);

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentAdapter);
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
        long caseinvesCount = CaseInvesManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);
        long verificationsCount = VerificationsManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);
        long lettersCount = LettersManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);
        long endingsCount = EndingsManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);
        long zancunsCount = ZancunsManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);
        long giftHandsCount = GiftsHandsManager.getInstance().getCountByQueryWithCompanys(getCompany(), startDate, endDate);

        tl_bar.getTabAt(0).setText(getResources().getString(R.string.list_caseinves_title, String.valueOf(caseinvesCount)));
        tl_bar.getTabAt(1).setText(getResources().getString(R.string.list_verifications_title, String.valueOf(verificationsCount)));
        tl_bar.getTabAt(2).setText(getResources().getString(R.string.list_letters_title, String.valueOf(lettersCount)));
        tl_bar.getTabAt(3).setText(getResources().getString(R.string.list_endings_title, String.valueOf(endingsCount)));
        tl_bar.getTabAt(4).setText(getResources().getString(R.string.list_zancuns_title, String.valueOf(zancunsCount)));
        tl_bar.getTabAt(5).setText(getResources().getString(R.string.list_gift_title, String.valueOf(giftHandsCount)));


        BaseLoadingFragment loadingFragment = (BaseLoadingFragment) contentAdapter.getItem(viewPager.getCurrentItem());
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

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.vp_list);

        FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
        FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
        FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
        FragmentFactory.createFragment(FragmentFactory.TAB_GIFT);
        viewPager.addOnPageChangeListener(mPageChangeListener);
    }

    public List<String> getCompany() {
        if (company_type_index == 0 && company_index == 0) {
            return null;
        }
        List<String> companys = new ArrayList<>();
        if (company_index != 0) {
            companys.add(companyArr[company_index]);
        }
        else {
            companys = new ArrayList<>(Arrays.asList(companyArr));
            companys.remove(0);
        }

        return companys;
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
        public static final int TAB_ZANCUNS = 4 ;// 暂存
        public static final int TAB_GIFT = 5; // 三礼上交

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
                    //三礼上交
                    case TAB_GIFT:
                        fragment = new CompanyGiftsFragment();
                        break;
                    default:
                        break;
                }
                mFragmentMap.put(index, fragment);
            }
            return fragment;
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
            BaseActivityFragment fragment = FragmentFactory.createFragment(position);
            if (fragment instanceof BaseLoadingFragment) {
                ((BaseLoadingFragment) fragment).show();
            }
        }
    };

    class ContentPagerAdapter extends FragmentPagerAdapter{

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseActivityFragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

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
                    tl_bar.getTabAt(0).setText(getResources()
                            .getString(R.string.list_caseinves_title, count1+""));
                    break;
                case "COMPANY_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tl_bar.getTabAt(1).setText(getResources()
                            .getString(R.string.list_verifications_title, count2+""));
                    break;
                case "COMPANY_LETTERS":
                    int count3 = (int) event.getContent();
                    tl_bar.getTabAt(2).setText(getResources()
                            .getString(R.string.list_letters_title, count3+""));
                    break;
                case "COMPANY_ENDINGS":
                    int count4 = (int) event.getContent();
                    tl_bar.getTabAt(3).setText(getResources()
                            .getString(R.string.list_endings_title, count4+""));
                    break;
                case "COMPANY_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tl_bar.getTabAt(4).setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
                case "COMPANY_GIFTS":

                    int count6 = (int) event.getContent();
                    tl_bar.getTabAt(5).setText(getResources()
                            .getString(R.string.list_gift_title, count6+""));
                    break;
            }
        }
    }
}
