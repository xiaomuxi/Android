package com.project.archives.function.person.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.VerificationsListAdapter;
import com.project.archives.function.person.activity.PersonProblemListActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class PersonVerificationsFragment extends BaseLoadingFragment{
    private ListView listView;
    private List<Verifications> list = new ArrayList<>();
    private VerificationsListAdapter adapter;

    private String name = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PersonProblemListActivity personProblemListActivity = (PersonProblemListActivity) context;
        name = personProblemListActivity.getName();
    }

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_verifications);
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
        adapter = new VerificationsListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {

        list = VerificationsManager.getInstance().getVerificationList(name, null, null, null);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("PERSON_VERIFICATIONS", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    public void getDatabyUserName(String username) {
        if(StringUtils.isEmpty(username)) {
            list = VerificationsManager.getInstance().getVerificationList(null, null, null, null);
        }
        else {
            list = VerificationsManager.getInstance().getVerificationList(username, null, null, null);
        }

        adapter.setData(list);

        MessageEvent messageEvent = new MessageEvent<Integer>("PERSON_VERIFICATIONS", list.size());
        EventBus.getDefault().post(messageEvent);
    }

    @Override
    protected View createLoadedView() {
        return setContentView();
    }

    @Override
    protected void load() {

    }
}
