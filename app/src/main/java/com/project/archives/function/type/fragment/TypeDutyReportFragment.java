package com.project.archives.function.type.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.DutyReports;
import com.project.archives.common.dao.manager.DutyReportsManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.DutyReportListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class TypeDutyReportFragment extends BaseActivityFragment {

    private ListView listView;
    private List<DutyReports> list = new ArrayList<>();
    private DutyReportListAdapter adapter;

    private Button btn_search;
    private EditText et_search;
    private TextView tv_empty;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_duty_report_type);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        et_search = (EditText) view.findViewById(R.id.et_search);
        tv_empty = (TextView) view.findViewById(R.id.tv_empty);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        adapter = new DutyReportListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        String name = et_search.getText().toString().trim();

        list = DutyReportsManager.getInstance().getDutyReportListByName(name);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                checkData();
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("TYPE_DUTY_REPORT", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    private void checkData(){
        if (list.size() == 0) {
            listView.setVisibility(View.GONE);
            tv_empty.setVisibility(View.VISIBLE);
        }
        else {
            tv_empty.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}
