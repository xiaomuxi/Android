package com.project.archives.function.age;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.Users;
import com.project.archives.common.dao.manager.UsersManager;
import com.project.archives.function.person.adapter.UserListAdapter;

import java.util.List;

/**
 * Created by inrokei on 2018/5/4.
 */

public class AgeActivity extends BaseActivity {

    private ListView lv_age;
    private EditText et_search_start, et_search_end;
    private Button btn_search;
    private TextView tv_empty;

    private List<Users> list;
    private UserListAdapter adapter;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_age);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("年龄查询");
    }

    @Override
    protected void initView() {
        super.initView();
        lv_age = (ListView) findViewById(R.id.lv_age);
        et_search_start = (EditText) findViewById(R.id.et_search_start);
        et_search_end = (EditText) findViewById(R.id.et_search_end);
        btn_search = (Button) findViewById(R.id.btn_search);
        tv_empty = (TextView) findViewById(R.id.tv_empty);

        btn_search.setOnClickListener(mClickListener);

        adapter = new UserListAdapter(this);
        lv_age.setAdapter(adapter);

        initData();
    }

    private void initData() {
        list = UsersManager.getInstance().getUserList(null, null, null);
        checkData(list);
        adapter.setData(list);
    }

    private void getDataByAge(String startAge, String endAge) {
        list = UsersManager.getInstance().getUserList(null, startAge, endAge);
        checkData(list);
        adapter.setData(list);
    }

    private void checkData(List data){
        if (data.size() == 0) {
            lv_age.setVisibility(View.GONE);
            tv_empty.setVisibility(View.VISIBLE);
        }
        else {
            lv_age.setVisibility(View.VISIBLE);
            tv_empty.setVisibility(View.GONE);
        }
    }


    private final View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_search:
                    nameSearchClickEvent();
                    break;
                default:
                    break;
            }
        }
    };

    private void nameSearchClickEvent() {
        String startAge = et_search_start.getText().toString().trim();
        String endAge = et_search_end.getText().toString().trim();

        getDataByAge(startAge, endAge);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
