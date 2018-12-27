package com.project.archives.function.person.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.Users;
import com.project.archives.common.dao.manager.UsersManager;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.person.adapter.UserListAdapter;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/4.
 */

public class PersonActivity extends BaseActivity {
    private ListView lv_person;
    private EditText et_search;
    private Button btn_search;
    private TextView tv_empty;

    private int company_type_index = 0 ;
    private int company_index = 0;
    private String[] companyTypeArr=new String[]{"全部单位类型", "党群部门", "行政部门", "街镇", "区管企业", "区管事业单位", "人大政协半、法院检察院"};
    private String[] companyArr=new String[]{"全部单位"};
    private NiceSpinner ns_company_type, ns_company;

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
        ns_company_type = (NiceSpinner) findViewById(R.id.ns_company_type);
        ns_company = (NiceSpinner) findViewById(R.id.ns_company);

        btn_search.setOnClickListener(mClickListener);

        adapter = new UserListAdapter(this);
        lv_person.setAdapter(adapter);
        ns_company_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                company_type_index = position;
                company_index = 0;
                initCompany();
                List<String> companys = new LinkedList<>(Arrays.asList(companyArr));
                ns_company.attachDataSource(companys);
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtils.i(TAG, "onNothingSelected--type-");
            }
        });
        ns_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                company_index = position;
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LogUtils.i(TAG, "onNothingSelected---");
            }
        });

        initCompany();
        List<String> comapnytypes = new LinkedList<>(Arrays.asList(companyTypeArr));
        List<String> companys = new LinkedList<>(Arrays.asList(companyArr));
        ns_company_type.attachDataSource(comapnytypes);
        ns_company.attachDataSource(companys);

        initData();
    }

    private void initData() {
        list = UsersManager.getInstance().getUserList(null, null, null, getCompany());
        checkData(list);
        adapter.setData(list);
    }

    private void initCompany() {
        List<String> companyList = UsersManager.getInstance().getAllCompany(company_type_index);
        ArrayList<String> newCompanyList = new ArrayList<>();
        newCompanyList.add("全部单位");
        for (int i = 0; i < companyList.size(); i++) {
            String companyName = companyList.get(i);
            companyName = StringUtils.isEmpty(companyName) ? "未知单位" : companyName;
            newCompanyList.add(companyName);
        }

        companyArr = newCompanyList.toArray(new String[0]);
    }

    public List<String> getCompany() {
        if (company_type_index == 0 && company_index == 0) {
            return null;
        }
        List<String> companys = new ArrayList<>();
        if (company_index != 0) {
            companys.add(companyArr[company_index]);
        }
        else {
            companys = new ArrayList<>(Arrays.asList(companyArr));
            companys.remove(0);
        }

        return companys;
    }

    private void getDataByName(String name) {
        list = UsersManager.getInstance().getUserList(name, null, null, getCompany());
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
