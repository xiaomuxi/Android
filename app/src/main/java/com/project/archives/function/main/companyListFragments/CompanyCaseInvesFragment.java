package com.project.archives.function.main.companyListFragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.CaseInvesListAdapter;
import com.project.archives.common.dao.manager.CaseInvesManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class CompanyCaseInvesFragment extends BaseLoadingFragment{

    private ListView listView;
    private List<CaseInves> list = new ArrayList<>();
    private CaseInvesListAdapter adapter;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_caseinves);
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
        adapter = new CaseInvesListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();

    }

    private void initData() {
        list = CaseInvesManager.getInstance().getCaseInvesList(null, null, null, null);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("COMPANY_CASEINVES", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    @Override
    protected View createLoadedView() {
        return setContentView();
    }

    @Override
    protected void load() {
    }
}
