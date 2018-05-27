package com.project.archives.function.detail.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.dao.GiftHandDetails;
import com.project.archives.common.dao.GiftHands;
import com.project.archives.common.dao.Gifts;
import com.project.archives.common.dao.manager.GiftCardsManager;
import com.project.archives.common.dao.manager.GiftHandDetailsManager;
import com.project.archives.common.dao.manager.GiftsManager;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.detail.fragment.GiftCardListFragment;
import com.project.archives.function.detail.fragment.GiftHandDetailListFragment;
import com.project.archives.function.detail.fragment.GiftListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XX on 2018/4/30.
 */

public class GiftsDetailActivity extends BaseActivity {

    GiftHands item;
    List<Gifts> giftsList = new ArrayList<>();
    List<GiftHandDetails> giftHandDetailsList = new ArrayList<>();
    List<GiftCards> giftCardsList = new ArrayList<>();

    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu;

    private TabLayout tl_bar;
    private List<String> tabIndicators;
    private final String[] indicators = new String[]{"礼金上交", "礼卡／证劵上交", "礼品上交"};
    private ContentPagerAdapter contentAdapter;
    private ViewPager viewPager;
    private int initIndex = 0;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_gifts_detail);

        item = (GiftHands) getIntent().getSerializableExtra("item");
        if (item != null) {
            giftsList = GiftsManager.getInstance().getGiftByGiftHandID(item.getId());
            giftHandDetailsList = GiftHandDetailsManager.getInstance().getGiftHandDetailByGiftHandID(item.getId());
            giftCardsList = GiftCardsManager.getInstance().getGiftCardByGiftHandID(item.getId());
        }
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("信息详情");
    }

    @Override
    protected void initView() {
        super.initView();
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);

        initViewPager();
        initTab();
        initData();
    }

    private void initData() {
        if (item == null) {
            return;
        }

        tl_bar.getTabAt(0).setText(getResources().getString(R.string.txt_money_hand, giftHandDetailsList.size() + ""));
        tl_bar.getTabAt(1).setText(getResources().getString(R.string.txt_card_hand, giftCardsList.size() + ""));
        tl_bar.getTabAt(2).setText(getResources().getString(R.string.txt_gift_hand, giftsList.size() + ""));

        int rank = item.getRank() == null ? -1 : item.getRank();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.vp_list);

        FragmentFactory.createFragment(FragmentFactory.TAB_GIFT_HAND_DETAIL);
        FragmentFactory.createFragment(FragmentFactory.TAB_GIFT_CARD);
        FragmentFactory.createFragment(FragmentFactory.TAB_GIFT);
        viewPager.addOnPageChangeListener(mPageChangeListener);
    }

    private void initTab(){
        tabIndicators = Arrays.asList(indicators);
        tl_bar = (TabLayout) findViewById(R.id.tl_bar);
        tl_bar.setTabMode(TabLayout.MODE_FIXED);
        tl_bar.setTabTextColors(Color.parseColor("#999999"), Color.parseColor("#267cfc"));
        tl_bar.setSelectedTabIndicatorColor(Color.parseColor("#267cfc"));
        ViewCompat.setElevation(tl_bar, 10);
        tl_bar.setupWithViewPager(viewPager);

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentAdapter);

        viewPager.setCurrentItem(initIndex);
    }

    public List<GiftHandDetails> getGiftHandDetailsList() {
        return giftHandDetailsList;
    }

    public List<GiftCards> getGiftCardsList() {
        return giftCardsList;
    }

    public List<Gifts> getGiftsList() {
        return giftsList;
    }

    /**
     * 采用工厂类进行创建Fragment，便于扩展，已经创建的Fragment不再创建
     */
    public static class FragmentFactory {
        public static final int TAB_GIFT_HAND_DETAIL = 0; // 礼金上交情况
        public static final int TAB_GIFT_CARD = 1; // 礼卡上交情况
        public static final int TAB_GIFT = 2; // 礼品上交情况

        //记录所有的fragment，防止重复创建
        public static final Map<Integer, BaseActivityFragment> mFragmentMap = new HashMap<>();

        public static BaseActivityFragment createFragment(int index) {
            BaseActivityFragment fragment = mFragmentMap.get(index);
            if (null == fragment) {
                switch (index) {
                    //礼金上交
                   case TAB_GIFT_HAND_DETAIL:
                        fragment = new GiftHandDetailListFragment();
                        break;
                    //礼卡上交
                    case TAB_GIFT_CARD:
                        fragment = new GiftCardListFragment();
                        break;
                    //礼品上交
                    case TAB_GIFT:
                        fragment = new GiftListFragment();
                        break;
                }
                mFragmentMap.put(index, fragment);
            }
            return fragment;
        }
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

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

}
