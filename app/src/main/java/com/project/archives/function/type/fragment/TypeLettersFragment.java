package com.project.archives.function.type.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jayfang.dropdownmenu.OnMenuSelectedListener;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.LettersListAdapter;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    private String[] trueDegreeArr = new String[]{"全部处理情况", "失实", "部分属实适当处理", "转初核", "转立案", "其他"};
    private String[] situationArr = new String[]{"全部组织处理类型", "通报批评", "解聘", "辞退", "调整岗位", "免职",
            "取消预备党员资格", "取消荣誉称号", "诫勉谈话", "停职", "公开道歉", "引咎辞职", "责令辞职",
            "其他组织处理", "不实澄清", "提醒谈话"};
    private String[] situationEmptyArr = new String[]{"全部组织处理类型"};
    private NiceSpinner ns_true_degree, ns_handle_situation;

    private int trueDegreeIndex = 0;
    private int resultSituationIndex = 0;

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
        ns_true_degree = (NiceSpinner) view.findViewById(R.id.ns_true_degree);
        ns_handle_situation = (NiceSpinner) view.findViewById(R.id.ns_handle_situation);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        ns_true_degree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                trueDegreeIndex = position;
                resultSituationIndex = 0;

                List<String> situations = new LinkedList<>(Arrays.asList(position == 2 ? situationArr : situationEmptyArr));
                ns_handle_situation.attachDataSource(situations);

                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ns_handle_situation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultSituationIndex = position;
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        List<String> takingResult = new LinkedList<>(Arrays.asList(trueDegreeArr));
        List<String> situation = new LinkedList<>(Arrays.asList(situationEmptyArr));
        ns_true_degree.attachDataSource(takingResult);
        ns_handle_situation.attachDataSource(situation);
    }


    private void initData() {

        String name = et_search.getText().toString().trim();
        String trueDegreeResult = trueDegreeIndex == 0 ? null : String.valueOf(trueDegreeIndex);

        list = LettersManager.getInstance().getLetterListByNameTrueDegreeAndResultSituation(name, trueDegreeResult, resultSituationIndex);
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
