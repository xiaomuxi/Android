package com.project.archives.function.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.PersonAdapter;
import com.project.archives.function.main.bean.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/4/25.
 */

public class FifthFragment extends BaseActivityFragment {

    //    private DropDownMenu mMenu;
    private ListView mList;

    private List<PersonModel> data;

    private ListView listView;
    private PersonAdapter mAdapter;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fifth_fragment);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {

        data = new ArrayList<>();
        initData();
        listView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new PersonAdapter(mContext);
        listView.setAdapter(mAdapter);
        mAdapter.setData(data);

    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }

    public void initData() {
        for (int i = 0; i < 100; i ++) {
            PersonModel model = new PersonModel();
            model.setName("赵六A" + i + "号");
            model.setAge(22 + i + "");
            model.setCode(130002+i+"");
            model.setCompany(i%3 == 0 ? "行政单位2" : "事业单位1");
            model.setJob("松江区党支部书记");
            model.setLevel(i%2 == 0 ?"了结类": i%3 == 0 ? "暂存类": "处分类");
            model.setSex("男");
            data.add(model);
        }
    }

}
