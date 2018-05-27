package com.project.archives.function.type.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.dao.manager.DutyReportsManager;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.dao.manager.GiftsHandsManager;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.dao.manager.ZancunsManager;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.type.fragment.TypeCaseInvesFragment;
import com.project.archives.function.type.fragment.TypeDutyReportFragment;
import com.project.archives.function.type.fragment.TypeEndingsFragment;
import com.project.archives.function.type.fragment.TypeGiftsFragment;
import com.project.archives.function.type.fragment.TypeLettersFragment;
import com.project.archives.function.type.fragment.TypeVerificationsFragment;
import com.project.archives.function.type.fragment.TypeZancunsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by inrokei on 2018/5/4.
 */

public class TypeActivity extends BaseActivity{

    private ViewPager viewPager;
    private TabLayout tl_bar;
    private List<String> tabIndicators;
    private final String[] indicators = new String[]{"处分类", "初核类", "述责述廉", "函询类", "了结类", "暂存类", "三礼上交"};
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_type);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("问题类型查询");
    }

    @Override
    protected void initView() {
        super.initView();
        initViewPager();
        initTab();
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.vp_list);

        FragmentFactory.createFragment(FragmentFactory.TAB_CASEINVES);
        FragmentFactory.createFragment(FragmentFactory.TAB_VERIFICATIONS);
        FragmentFactory.createFragment(FragmentFactory.TAB_DUTY_REPORT);
        FragmentFactory.createFragment(FragmentFactory.TAB_LETTERS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ENDINGS);
        FragmentFactory.createFragment(FragmentFactory.TAB_ZANCUNS);
        FragmentFactory.createFragment(FragmentFactory.TAB_GIFT);
        viewPager.addOnPageChangeListener(mPageChangeListener);
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

        long caseinvesCount = CaseInvesManager.getInstance().getCount();
        long verificationsCount = VerificationsManager.getInstance().getCount();
        long dutyReportCount = DutyReportsManager.getInstance().getCount();
        long lettersCount = LettersManager.getInstance().getCount();
        long endingsCount = EndingsManager.getInstance().getCount();
        long zancunsCount = ZancunsManager.getInstance().getCount();
        long giftHandsCount = GiftsHandsManager.getInstance().getCount();

        tl_bar.getTabAt(0).setText(getResources().getString(R.string.list_caseinves_title, String.valueOf(caseinvesCount)));
        tl_bar.getTabAt(1).setText(getResources().getString(R.string.list_verifications_title, String.valueOf(verificationsCount)));
        tl_bar.getTabAt(2).setText(getResources().getString(R.string.list_duty_report_title, String.valueOf(dutyReportCount)));
        tl_bar.getTabAt(3).setText(getResources().getString(R.string.list_letters_title, String.valueOf(lettersCount)));
        tl_bar.getTabAt(4).setText(getResources().getString(R.string.list_endings_title, String.valueOf(endingsCount)));
        tl_bar.getTabAt(5).setText(getResources().getString(R.string.list_zancuns_title, String.valueOf(zancunsCount)));
        tl_bar.getTabAt(6).setText(getResources().getString(R.string.list_gift_title, String.valueOf(giftHandsCount)));
    }

    /**
     * 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建
     */
    public static class FragmentFactory {
        public static final int TAB_CASEINVES = 0; // 处分
        public static final int TAB_VERIFICATIONS = 1; // 初步核实
        public static final int TAB_DUTY_REPORT = 2; // 初步核实
        public static final int TAB_LETTERS = 3; // 函询
        public static final int TAB_ENDINGS = 4;// 了结
        public static final int TAB_ZANCUNS = 5 ;
        public static final int TAB_GIFT = 6;

        //记录所有的fragment，防止重复创建
        public static final Map<Integer, BaseActivityFragment> mFragmentMap = new HashMap<>();

        public static BaseActivityFragment createFragment(int index) {
            BaseActivityFragment fragment = mFragmentMap.get(index);
            if (null == fragment) {
                switch (index) {
                    //处分类
                    case TAB_CASEINVES:
                        fragment = new TypeCaseInvesFragment();
                        break;
                    //初步核实类
                    case TAB_VERIFICATIONS:
                        fragment = new TypeVerificationsFragment();
                        break;
                    //述责述廉
                    case TAB_DUTY_REPORT:
                        fragment = new TypeDutyReportFragment();
                        break;
                    //谈话函询类
                    case TAB_LETTERS:
                        fragment = new TypeLettersFragment();
                        break;
                    //了结类
                    case TAB_ENDINGS:
                        fragment = new TypeEndingsFragment();
                        break;
                    //暂存类
                    case TAB_ZANCUNS:
                        fragment = new TypeZancunsFragment();
                        break;
                    //三礼上交
                    case TAB_GIFT:
                        fragment = new TypeGiftsFragment();
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentFactory.mFragmentMap.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (StringUtils.notNull(event)) {
            switch (event.getTitle()) {
                case "TYPE_CASEINVES":
                    int count1 = (int) event.getContent();
                    tl_bar.getTabAt(0).setText(getResources()
                            .getString(R.string.list_caseinves_title, count1+""));
                    break;
                case "TYPE_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tl_bar.getTabAt(1).setText(getResources()
                            .getString(R.string.list_verifications_title, count2+""));
                    break;
                case "TYPE_DUTY_REPORT":

                    int reportCount = (int) event.getContent();
                    tl_bar.getTabAt(2).setText(getResources()
                            .getString(R.string.list_duty_report_title, reportCount+""));
                    break;
                case "TYPE_LETTERS":
                    int count3 = (int) event.getContent();
                    tl_bar.getTabAt(3).setText(getResources()
                            .getString(R.string.list_letters_title, count3+""));
                    break;
                case "TYPE_ENDINGS":
                    int count4 = (int) event.getContent();
                    tl_bar.getTabAt(4).setText(getResources()
                            .getString(R.string.list_endings_title, count4+""));
                    break;
                case "TYPE_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tl_bar.getTabAt(5).setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
                case "TYPE_GIFTS":

                    int count6 = (int) event.getContent();
                    tl_bar.getTabAt(6).setText(getResources()
                            .getString(R.string.list_gift_title, count6+""));
                    break;
            }
        }
    }
}
