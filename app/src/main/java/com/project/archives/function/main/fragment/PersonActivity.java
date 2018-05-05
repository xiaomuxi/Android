package com.project.archives.function.main.fragment;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.main.personlistFragments.PersonCaseInvesFragment;
import com.project.archives.function.main.personlistFragments.PersonEndingsFragment;
import com.project.archives.function.main.personlistFragments.PersonLettersFragment;
import com.project.archives.function.main.personlistFragments.PersonVerificationsFragment;
import com.project.archives.function.main.personlistFragments.PersonZancunsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by inrokei on 2018/5/4.
 */

public class PersonActivity extends BaseActivity {
    final String TAG = getClass().getName();
    private ListView listView;
    private int type_index;
    private int company_index;
    final String[] arr1=new String[]{"全部单位类型","党群部门","行政部门","区管企业","区管事业单位", "人大政协办法"};
    final String[] arr2=new String[]{"全部单位","闵行区1单位","松江区1单位","徐汇区2单位"};
    final String[] strings=new String[]{"选择单位类型","选择单位"};
    private DropDownMenu mMenu;


    private ViewPager viewPager;
    private FragmentManager mFragmentManager;
    private int mPrePosition;
    private LinearLayout ll_list;
    private EditText et_search;
    private Button btn_search;
    private TextView tv_caseinves, tv_verifications,
            tv_letters, tv_endings, tv_zancuns;

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
        setContentView(R.layout.fragment_list_parent);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("个人查询");
    }

    @Override
    protected void initView() {
        super.initView();
        mFragmentManager = getSupportFragmentManager();
        initTopMenu();
        initViewPgaer();
    }

    private void initViewPgaer() {
        btn_search = (Button) findViewById(R.id.btn_search);
        et_search = (EditText) findViewById(R.id.et_search);
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
        btn_search.setOnClickListener(mClickListener);


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

    private void initTopMenu() {
        mMenu=(DropDownMenu) findViewById(R.id.menu);
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

        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                if (ColumnIndex == 0) {
                    type_index = RowIndex;
                } else if (ColumnIndex == 1) {
                    company_index = RowIndex;
                }
                //过滤筛选
//                setFilter();
            }
        });
        List<String[]> items = new ArrayList<>();
        items.add(arr1);
        items.add(arr2);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
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
                        fragment = new PersonCaseInvesFragment();
                        break;
                    //初步核实类
                    case TAB_VERIFICATIONS:
                        fragment = new PersonVerificationsFragment();
                        break;
                    //谈话函询类
                    case TAB_LETTERS:
                        fragment = new PersonLettersFragment();
                        break;
                    //了结类
                    case TAB_ENDINGS:
                        fragment = new PersonEndingsFragment();
                        break;
                    //暂存类
                    case TAB_ZANCUNS:
                        fragment = new PersonZancunsFragment();
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

                case R.id.btn_search:
                    nameSearchClickEvent();
                    break;
                default:
                    break;
            }
        }
    };

    private void nameSearchClickEvent() {
        String username = et_search.getText().toString().trim();
//        if (StringUtils.isEmpty(username)) {
//            UIUtils.showToastSafe("请输入姓名");
//            return;
//        }

//        if (FragmentFactory.mFragmentMap.get(0) != null) {
//            PersonCaseInvesFragment caseInvesFragment = (PersonCaseInvesFragment) FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
//            caseInvesFragment.getDatabyUserName(username);
//        }
//        if (FragmentFactory.mFragmentMap.get(1) != null) {
//            PersonVerificationsFragment verificationsFragment = (PersonVerificationsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
//            verificationsFragment.getDatabyUserName(username);
//        }
//        if (FragmentFactory.mFragmentMap.get(2) != null) {
//            PersonLettersFragment lettersFragment = (PersonLettersFragment) FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
//            lettersFragment.getDatabyUserName(username);
//        }
//        if (FragmentFactory.mFragmentMap.get(3) != null) {
//            PersonEndingsFragment endingsFragment = (PersonEndingsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
//            endingsFragment.getDatabyUserName(username);
//        }
//        if (FragmentFactory.mFragmentMap.get(4) != null) {
//            PersonZancunsFragment zancunsFragment = (PersonZancunsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
//            zancunsFragment.getDatabyUserName(username);
//        }

        switch (viewPager.getCurrentItem()) {
            case 0:
                PersonCaseInvesFragment caseInvesFragment = (PersonCaseInvesFragment) FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
                caseInvesFragment.getDatabyUserName(username);
                break;
            case 1:
                PersonVerificationsFragment verificationsFragment = (PersonVerificationsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
                verificationsFragment.getDatabyUserName(username);
                break;
            case 2:
                PersonLettersFragment lettersFragment = (PersonLettersFragment) FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
                lettersFragment.getDatabyUserName(username);
                break;
            case 3:
                PersonEndingsFragment endingsFragment = (PersonEndingsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
                endingsFragment.getDatabyUserName(username);

                break;
            case 4:
                PersonZancunsFragment zancunsFragment = (PersonZancunsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
                zancunsFragment.getDatabyUserName(username);
                break;
        }
    }

    public String getUserName() {
        return et_search.getText().toString().trim();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        FragmentFactory.mFragmentMap.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (StringUtils.notNull(event)) {
            switch (event.getTitle()) {
                case "PERSON_CASEINVES":
                    int count1 = (int) event.getContent();
                    LogUtils.i(TAG, count1+"----count1");
                    tv_caseinves.setText(getResources()
                            .getString(R.string.list_caseinves_title, count1+""));
                    break;
                case "PERSON_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    LogUtils.i(TAG, count2+"----count2");
                    tv_verifications.setText(getResources()
                            .getString(R.string.list_verifications_title, count2+""));
                    break;
                case "PERSON_LETTERS":
                    int count3 = (int) event.getContent();
                    LogUtils.i(TAG, count3+"----count3");
                    tv_letters.setText(getResources()
                            .getString(R.string.list_letters_title, count3+""));
                    break;
                case "PERSON_ENDINGS":
                    int count4 = (int) event.getContent();
                    LogUtils.i(TAG, count4+"----count4");
                    tv_endings.setText(getResources()
                            .getString(R.string.list_endings_title, count4+""));
                    break;
                case "PERSON_ZANCUNS":

                    int count5 = (int) event.getContent();
                    LogUtils.i(TAG, count5+"----count5");
                    tv_zancuns.setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
            }
        }
    }
}
