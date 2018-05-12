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
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.VerificationsListAdapter;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    private String[] takingResultArr = new String[]{"全部了结结论类型", "失实", "适当处理", "转立案", "其他"};
    private String[] situationArr = new String[]{"全部组织处理类型", "通报批评", "解聘", "辞退", "调整岗位", "免职",
            "取消预备党员资格", "取消荣誉称号", "诫勉谈话", "停职", "公开道歉", "引咎辞职", "责令辞职",
            "其他组织处理", "不实澄清", "提醒谈话"};
    private String[] situationEmptyArr = new String[]{"全部组织处理类型"};
    private NiceSpinner ns_result_type, ns_handle_situation;

    private int takingResultIndex = 0;
    private int resultSituationIndex = 0;

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
        ns_result_type = (NiceSpinner) view.findViewById(R.id.ns_result_type);
        ns_handle_situation = (NiceSpinner) view.findViewById(R.id.ns_handle_situation);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        ns_result_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                List<String> situations = new LinkedList<>(Arrays.asList(position == 2 ? situationArr : situationEmptyArr));
                ns_handle_situation.attachDataSource(situations);

                takingResultIndex = position;
                resultSituationIndex = 0;

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
        adapter = new VerificationsListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    public void initSelectMenu() {
        List<String> takingResult = new LinkedList<>(Arrays.asList(takingResultArr));
        List<String> situation = new LinkedList<>(Arrays.asList(situationEmptyArr));
        ns_result_type.attachDataSource(takingResult);
        ns_handle_situation.attachDataSource(situation);
    }

    private void initData() {

        String name = et_search.getText().toString().trim();
        String takingResult = takingResultIndex == 0 ? null : String.valueOf(takingResultIndex);

        list = VerificationsManager.getInstance().getVerificationListByNameTakingResultAndResultSituation(name, takingResult, resultSituationIndex);
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
