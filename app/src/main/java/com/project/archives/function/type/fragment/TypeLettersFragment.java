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
import com.project.archives.common.dao.Letters;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.LettersListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class TypeLettersFragment extends BaseActivityFragment implements OnMenuSelectedListener{

    private ListView listView;
    private List<Letters> list = new ArrayList<>();
    private LettersListAdapter adapter;

    private Button btn_search;
    private EditText et_search;
    private TextView tv_empty;

    private String[] trueDegreeArr = new String[]{"全部类型", "属实", "基本属实", "部分属实", "不实", "存结无调查结论"};
    final String[] strings = new String[]{"请选择处理情况类型"};
    private DropDownMenu mMenu;

    private int trueDegreeIndex = 0;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_letters_type);
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
        mMenu = (DropDownMenu) view.findViewById(R.id.menu);

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
        adapter = new LettersListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    public void initSelectMenu() {
        mMenu.setmMenuCount(1);
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
        items.add(trueDegreeArr);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }


    private void initData() {

        String name = et_search.getText().toString().trim();
        String trueDegreeResult = trueDegreeIndex == 0 ? null : String.valueOf(trueDegreeIndex);

        list = LettersManager.getInstance().getLetterListByNameAndTrueDegree(name, trueDegreeResult);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                checkData();
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("TYPE_LETTERS", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    @Override
    public void onSelected(View view, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            trueDegreeIndex = rowIndex;
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
