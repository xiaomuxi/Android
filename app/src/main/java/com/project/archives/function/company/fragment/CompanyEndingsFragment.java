package com.project.archives.function.company.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Endings;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.company.activity.CompanyActivity;
import com.project.archives.function.main.adapter.EndingsListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class CompanyEndingsFragment extends BaseLoadingFragment {

    private ListView listView;
    private List<Endings> list = new ArrayList<>();
    private EndingsListAdapter adapter;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_endings);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        adapter = new EndingsListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        CompanyActivity companyActivity = (CompanyActivity) mContext;
        String company = companyActivity.getCompany();
        String startTime = companyActivity.getStartDate();
        String endTime = companyActivity.getEndDate();

        list = EndingsManager.getInstance().getEndingList(null, company, startTime, endTime);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("COMPANY_ENDINGS", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    @Override
    protected View createLoadedView() {
        return setContentView();
    }

    @Override
    protected boolean isNeedLoadEveryTime() {
        return true;
    }

    @Override
    protected void load() {
        initData();
        System.out.println("load--------");
    }
}
