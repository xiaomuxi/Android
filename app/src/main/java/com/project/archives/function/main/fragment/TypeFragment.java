package com.project.archives.function.main.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.typeListFragments.TypeCaseInvesFragment;
import com.project.archives.function.main.typeListFragments.TypeEndingsFragment;
import com.project.archives.function.main.typeListFragments.TypeLettersFragment;
import com.project.archives.function.main.typeListFragments.TypeVerificationsFragment;
import com.project.archives.function.main.typeListFragments.TypeZancunsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by inrokei on 2018/4/25.
 */

public class TypeFragment extends BaseActivityFragment {

    //    private DropDownMenu mMenu;
    private ListView mList;

    private int city_index;
    private int sex_index;
    private int age_index;
//    private List<PersonModel> data;
    final String[] arr2=new String[]{"全部问题类型","处分类","初步核实类", "谈话函询类","了结类", "暂存类"};
    final String[] strings=new String[]{"选择问题类型"};
    private DropDownMenu mMenu;

    private ViewPager viewPager;
    private FragmentManager mFragmentManager;
    private int mPrePosition;
    private LinearLayout ll_list;
    private TextView tv_caseinves, tv_verifications,
            tv_letters, tv_endings, tv_zancuns;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_type);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        mFragmentManager = getActivity().getSupportFragmentManager();
        mMenu = (DropDownMenu) view.findViewById(R.id.menu);
        mMenu.setVisibility(View.GONE);

        initViewPgaer(view);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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
                        fragment = new TypeCaseInvesFragment();
                        break;
                    //初步核实类
                    case TAB_VERIFICATIONS:
                        fragment = new TypeVerificationsFragment();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        if (StringUtils.notNull(event)) {
            switch (event.getTitle()) {
                case "TYPE_CASEINVES":
                    int count1 = (int) event.getContent();
                    tv_caseinves.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_caseinves_title, count1+"")));
                    break;
                case "TYPE_VERIFICATIONS":

                    int count2 = (int) event.getContent();
                    tv_verifications.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_verifications_title, count2+"")));
                    break;
                case "TYPE_LETTERS":
                    int count3 = (int) event.getContent();
                    tv_letters.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_letters_title, count3+"")));
                    break;
                case "TYPE_ENDINGS":
                    int count4 = (int) event.getContent();
                    tv_endings.setText(Html.fromHtml(getResources()
                            .getString(R.string.list_endings_title, count4+"")));
                    break;
                case "TYPE_ZANCUNS":

                    int count5 = (int) event.getContent();
                    tv_zancuns.setText(getResources()
                            .getString(R.string.list_zancuns_title, count5+""));
                    break;
            }
        }
    }
}
