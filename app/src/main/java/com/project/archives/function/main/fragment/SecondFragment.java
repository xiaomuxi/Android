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
import com.project.archives.function.main.adapter.PersonAdapter;
import com.project.archives.function.main.bean.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/4/25.
 */

public class SecondFragment extends BaseActivityFragment {

//    private DropDownMenu mMenu;
    private ListView mList;

    private int city_index;
    private int sex_index;
    private int age_index;
    private List<PersonModel> data;
    final String[] arr1=new String[]{"全部单位类型","党群部门","行政部门","区管企业","区管事业单位", "人大政协办法"};
    final String[] arr3=new String[]{"全部单位","闵行区1单位","松江区1单位","徐汇区2单位"};
    final String[] strings=new String[]{"选择单位类型","选择单位"};
    private DropDownMenu mMenu;

    private ListView listView;
    private PersonAdapter mAdapter;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_second);
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

        mMenu=(DropDownMenu) view.findViewById(R.id.menu);

        mMenu.setmMenuCount(2);
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
        items.add(arr1);
        items.add(arr3);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }

    public void initData() {
        for (int i = 0; i < 30; i ++) {
            PersonModel model = new PersonModel();
            model.setName("李四" + i + "号");
            model.setAge(20 + i + "");
            model.setCode(30002+i+"");
            model.setCompany(i%2 == 0 ? "闵行区单位2" : "闵行区单位1");
            model.setJob("闵行区区党支部书记");
            model.setLevel(i%2 == 0 ?"正处级": "副部级");
            model.setSex("男");
            data.add(model);
        }
    }

}
