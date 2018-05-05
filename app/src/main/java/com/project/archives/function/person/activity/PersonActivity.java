package com.project.archives.function.person.activity;

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

public class PersonActivity extends BaseActivity {
    private ListView lv_person;
    private EditText et_search;
    private Button btn_search;
    private TextView tv_empty;

    private List<Users> list;
    private UserListAdapter adapter;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_person);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("个人查询");
    }

    @Override
    protected void initView() {
        super.initView();

        lv_person = (ListView) findViewById(R.id.lv_person);
        et_search = (EditText) findViewById(R.id.et_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        tv_empty = (TextView) findViewById(R.id.tv_empty);

        btn_search.setOnClickListener(mClickListener);

        adapter = new UserListAdapter(this);
        lv_person.setAdapter(adapter);

        initData();
    }

    private void initData() {
        list = UsersManager.getInstance().getUserList(null, null, null);
        checkData(list);
        adapter.setData(list);
    }

    private void getDataByName(String name) {
        list = UsersManager.getInstance().getUserList(name, null, null);
        checkData(list);
        adapter.setData(list);
    }

    private void checkData(List data){
        if (data.size() == 0) {
            lv_person.setVisibility(View.GONE);
            tv_empty.setVisibility(View.VISIBLE);
        }
        else {
            lv_person.setVisibility(View.VISIBLE);
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
        String username = et_search.getText().toString().trim();

        getDataByName(username);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
