package com.project.archives.function.main.listFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.CaseInvesListAdapter;
import com.project.archives.function.main.manager.CaseInvesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class CaseInvesFragment extends BaseActivityFragment{

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

        List<CaseInves> result = CaseInvesManager.getInstance().getCaseInvesList(null, null, null, null);
        for (int i = 0; i < result.size(); i++) {
            list.add(result.get(i));
        }
        adapter.setData(list);
    }
}
