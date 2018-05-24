package com.project.archives.function.person.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.GiftHands;
import com.project.archives.common.dao.manager.GiftsHandsManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.GiftsListAdapter;
import com.project.archives.function.person.activity.PersonProblemListActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class PersonGiftsFragment extends BaseLoadingFragment {

    private ListView listView;
    private List<GiftHands> list = new ArrayList<>();
    private GiftsListAdapter adapter;

    private String name = "";
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_gifts);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        PersonProblemListActivity personProblemListActivity = (PersonProblemListActivity) context;
        name = personProblemListActivity.getName();
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
        adapter = new GiftsListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {

        list = GiftsHandsManager.getInstance().getGiftHandsListWithCompanys(name, null, null, null);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("PERSON_GIFTS", list.size());
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
