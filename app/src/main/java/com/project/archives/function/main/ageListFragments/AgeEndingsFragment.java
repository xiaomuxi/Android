package com.project.archives.function.main.ageListFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Endings;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.EndingsListAdapter;
import com.project.archives.function.main.manager.EndingsManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class AgeEndingsFragment extends BaseActivityFragment {

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

        list = EndingsManager.getInstance().getEndingList(null, null, null, null);
        adapter.setData(list);
        MessageEvent messageEvent = new MessageEvent<Integer>("AGE_ENDINGS", list.size());
        EventBus.getDefault().post(messageEvent);
    }

    public void getDataByAge(String startAge, String endAge) {
        list = EndingsManager.getInstance().getEndingsListByAge(startAge, endAge);
        adapter.setData(list);

        MessageEvent messageEvent = new MessageEvent<Integer>("AGE_ENDINGS", list.size());
        EventBus.getDefault().post(messageEvent);
    }
}