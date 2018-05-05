package com.project.archives.function.type.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.ZancunsListAdapter;
import com.project.archives.common.dao.manager.ZancunsManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class TypeZancunsFragment extends BaseLoadingFragment {

    private ListView listView;
    private List<Zancuns> list = new ArrayList<>();
    private ZancunsListAdapter adapter;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_zancuns);
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
        adapter = new ZancunsListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {

        list = ZancunsManager.getInstance().getZancunList(null, null, null, null);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("TYPE_ZANCUNS", list.size());
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
