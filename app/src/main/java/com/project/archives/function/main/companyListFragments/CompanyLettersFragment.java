package com.project.archives.function.main.companyListFragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseLoadingFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.LettersListAdapter;
import com.project.archives.common.dao.manager.LettersManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class CompanyLettersFragment extends BaseLoadingFragment {

    private ListView listView;
    private List<Letters> list = new ArrayList<>();
    private LettersListAdapter adapter;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_letters);
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
        adapter = new LettersListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        list = LettersManager.getInstance().getLetterList(null, null, null, null);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                show(check(list));
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("COMPANY_LETTERS", list.size());
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
