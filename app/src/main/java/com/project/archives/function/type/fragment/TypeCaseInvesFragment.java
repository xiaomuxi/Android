package com.project.archives.function.type.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.DropDownMenu;
import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.CaseInvesListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class TypeCaseInvesFragment extends BaseActivityFragment implements OnMenuSelectedListener {

    private ListView listView;
    private Button btn_search;
    private EditText et_search;
    private TextView tv_empty;
    private List<CaseInves> list = new ArrayList<>();
    private CaseInvesListAdapter adapter;

    private String[] dangTypeArr=new String[]{"全部党纪类型", "党内警告", "党内严重警告", "撤销党内职务", "留党察看", "开除党籍"};
    private String[] zhengTypeArr=new String[]{"全部政纪类型", "行政警告", "记过", "记大过", "降级", "撤职","开除", "免予处分"};
    final String[] strings=new String[]{"请选择党纪类型","请选择政纪类型"};
    private DropDownMenu mMenu;

    private int dangIndex = 0;
    private int zhengIndex = 0;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_caseinves_type);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        et_search = (EditText) view.findViewById(R.id.et_search);
        mMenu = (DropDownMenu) view.findViewById(R.id.menu);
        tv_empty = (TextView) view.findViewById(R.id.tv_empty);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        initSelectMenu();
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        adapter = new CaseInvesListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();

    }

    public void initSelectMenu() {
        mMenu.setmMenuCount(2);
        mMenu.setShowCheck(true);
        mMenu.setmMenuTitleTextSize(16);
        mMenu.setmMenuTitleTextColor(Color.parseColor("#777777"));
        mMenu.setmMenuListTextSize(16);
        mMenu.setmMenuListTextColor(Color.BLACK);
        mMenu.setmMenuBackColor(Color.WHITE);
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

        mMenu.setMenuSelectedListener(this);
        List<String[]> items = new ArrayList<>();
        items.add(dangTypeArr);
        items.add(zhengTypeArr);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }


    private void initData() {
        String name = et_search.getText().toString().trim();
        String dang = dangIndex == 0 ? null : String.valueOf(dangIndex);
        String zheng = zhengIndex == 0 ? null : String.valueOf(zhengIndex);


        list = CaseInvesManager.getInstance().getCaseInvesListByNameAndDangZheng(name, dang, zheng);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                checkData();
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("TYPE_CASEINVES", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    @Override
    public void onSelected(View view, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            dangIndex = rowIndex;

        } else {
            zhengIndex = rowIndex;
        }

        initData();
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
