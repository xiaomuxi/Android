package com.project.archives.function.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.ProblemAdapter;
import com.project.archives.function.main.bean.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/4/25.
 */

public class ForthFragment extends BaseActivityFragment {

    //    private DropDownMenu mMenu;
    private ListView mList;

    private int city_index;
    private int sex_index;
    private int age_index;
    private List<PersonModel> data;
    final String[] arr2=new String[]{"全部问题类型","处分类","初步核实类", "谈话函询类","了结类", "暂存类"};
    final String[] strings=new String[]{"选择问题类型"};
    private DropDownMenu mMenu;

    private ListView listView;
    private ProblemAdapter mAdapter;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_forth);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {

        data = new ArrayList<>();
        initData();
        listView = (ListView) view.findViewById(R.id.list_view);
        mAdapter = new ProblemAdapter(mContext);
        listView.setAdapter(mAdapter);
        mAdapter.setData(data);

        mMenu=(DropDownMenu) view.findViewById(R.id.menu);

        mMenu.setmMenuCount(1);
        mMenu.setmShowCount(6);
        mMenu.setShowCheck(true);
        mMenu.setmMenuTitleTextSize(16);
        mMenu.setmMenuTitleTextColor(Color.parseColor("#777777"));
        mMenu.setmMenuListTextSize(16);
        mMenu.setmMenuListTextColor(Color.BLACK);
        mMenu.setmMenuBackColor(Color.parseColor("#eeeeee"));
        mMenu.setmMenuPressedBackColor(Color.WHITE);
        mMenu.setmMenuPressedTitleTextColor(Color.BLACK);

        mMenu.setmCheckIcon(R.drawable.ico_make);

        mMenu.setmUpArrow(R.drawable.arrow_up);
        mMenu.setmDownArrow(R.drawable.arrow_down);

        mMenu.setDefaultMenuTitle(strings);


        mMenu.setShowDivider(false);
        mMenu.setmMenuListBackColor(getResources().getColor(R.color.white));
        mMenu.setmMenuListSelectorRes(R.color.white);
        mMenu.setmArrowMarginTitle(20);

        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                Log.i("MainActivity", "select " + ColumnIndex + " column and " + RowIndex + " row");
                if (ColumnIndex == 0) {
                    city_index = RowIndex;
                } else if (ColumnIndex == 1) {
                    sex_index = RowIndex;
                } else {
                    age_index = RowIndex;
                }
                //过滤筛选
//                setFilter();
            }
        });
        List<String[]> items = new ArrayList<>();
        items.add(arr2);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }

    public void initData() {
        for (int i = 0; i < 30; i ++) {
            PersonModel model = new PersonModel();
            model.setName("王五" + i + "号");
            model.setAge(18 + i + "");
            model.setCode(210002+i+"");
            model.setCompany(i%3 == 0 ? "松江镇单位2" : "松江镇单位1");
//            model.setJob("松江区党支部书记");
            model.setLevel(i%2 == 0 ?"记过处分": "留党察看");
            model.setSex("男");
            data.add(model);
        }
    }

}
