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
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.VerificationsListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class TypeVerificationsFragment extends BaseActivityFragment implements OnMenuSelectedListener{
    private ListView listView;
    private List<Verifications> list = new ArrayList<>();
    private VerificationsListAdapter adapter;

    private Button btn_search;
    private EditText et_search;
    private TextView tv_empty;

    private String[] takingResultArr = new String[]{"全部类型", "失实", "适当处理", "转立案", "其他"};
    final String[] strings = new String[]{"请选择了结结论类型"};
    private DropDownMenu mMenu;

    private int takingResultIndex = 0;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_verifications_type);
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
        adapter = new VerificationsListAdapter(mContext);
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
        items.add(takingResultArr);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
    }

    private void initData() {

        String name = et_search.getText().toString().trim();
        String takingResult = takingResultIndex == 0 ? null : String.valueOf(takingResultIndex);

        list = VerificationsManager.getInstance().getVerificationListByNameAndTakingResult(name, takingResult);
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                checkData();
                adapter.setData(list);
                MessageEvent messageEvent = new MessageEvent<Integer>("TYPE_VERIFICATIONS", list.size());
                EventBus.getDefault().post(messageEvent);
            }
        }, 500);
    }

    @Override
    public void onSelected(View view, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            takingResultIndex = rowIndex;
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
