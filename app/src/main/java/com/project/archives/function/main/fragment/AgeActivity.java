package com.project.archives.function.main.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.main.ageListFragments.AgeCaseInvesFragment;
import com.project.archives.function.main.ageListFragments.AgeEndingsFragment;
import com.project.archives.function.main.ageListFragments.AgeLettersFragment;
import com.project.archives.function.main.ageListFragments.AgeVerificationsFragment;
import com.project.archives.function.main.ageListFragments.AgeZancunsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by inrokei on 2018/5/4.
 */

public class AgeActivity extends BaseActivity {

    private ViewPager viewPager;
    private FragmentManager mFragmentManager;
    private int mPrePosition;
    private LinearLayout ll_list;
    private TextView tv_caseinves, tv_verifications,
            tv_letters, tv_endings, tv_zancuns;
    private Button btn_search;
    private EditText et_search_start;
    private EditText et_search_end;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.fragment_list_age);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("年龄查询");
    }

    @Override
    protected void initView() {
        super.initView();
        mFragmentManager = getSupportFragmentManager();

        initViewPgaer();
    }


    private void initViewPgaer() {
        btn_search = (Button) findViewById(R.id.btn_search);
        et_search_start = (EditText)findViewById(R.id.et_search_start);
        et_search_end = (EditText) findViewById(R.id.et_search_end);
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
                        fragment = new AgeCaseInvesFragment();
                        break;
                    //初步核实类
                    case TAB_VERIFICATIONS:
                        fragment = new AgeVerificationsFragment();
                        break;
                    //谈话函询类
                    case TAB_LETTERS:
                        fragment = new AgeLettersFragment();
                        break;
                    //了结类
                    case TAB_ENDINGS:
                        fragment = new AgeEndingsFragment();
                        break;
                    //暂存类
                    case TAB_ZANCUNS:
                        fragment = new AgeZancunsFragment();
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
                    searchBtnClickEvent();
                default:
                    break;
            }
        }
    };

    private void searchBtnClickEvent() {

        String startAge = et_search_start.getText().toString().trim();
        String endAge = et_search_end.getText().toString().trim();

        switch (viewPager.getCurrentItem()) {
            case 0:
                AgeCaseInvesFragment caseInvesFragment = (AgeCaseInvesFragment) FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
                caseInvesFragment.getDataByAge(startAge, endAge);
                break;
            case 1:
                AgeVerificationsFragment verificationsFragment = (AgeVerificationsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
                verificationsFragment.getDataByAge(startAge,endAge);
                break;
            case 2:
                AgeLettersFragment lettersFragment = (AgeLettersFragment) FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
                lettersFragment.getDataByAge(startAge, endAge);
                break;
            case 3:
                AgeEndingsFragment endingsFragment = (AgeEndingsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
                endingsFragment.getDataByAge(startAge, endAge);

                break;
            case 4:
                AgeZancunsFragment zancunsFragment = (AgeZancunsFragment) FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
                zancunsFragment.getDataByAge(startAge,endAge);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentFactory.mFragmentMap.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (StringUtils.notNull(event)) {
            switch (event.getTitle()) {
                case "AGE_CASEINVES":
                    int count1 = (int) event.getContent();
                    tv_caseinves.setText(getResources()
                            .getString(R.string.list_caseinves_title, count1+""));
                    break;
                case "AGE_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tv_verifications.setText(getResources()
                            .getString(R.string.list_verifications_title, count2+""));
                    break;
                case "AGE_LETTERS":
                    int count3 = (int) event.getContent();
                    tv_letters.setText(getResources()
                            .getString(R.string.list_letters_title, count3+""));
                    break;
                case "AGE_ENDINGS":
                    int count4 = (int) event.getContent();
                    tv_endings.setText(getResources()
                            .getString(R.string.list_endings_title, count4+""));
                    break;
                case "AGE_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tv_zancuns.setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
            }
        }
    }
}
