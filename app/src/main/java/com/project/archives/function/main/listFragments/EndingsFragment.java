package com.project.archives.function.main.listFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.dao.Endings;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.EndingsListAdapter;
import com.project.archives.function.main.manager.EndingsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class EndingsFragment extends BaseActivityFragment {

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
    }
}
