package com.project.archives.function.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by XX on 2018/4/30.
 */

public class ZancunsDetailActivity extends AppCompatActivity {

    Zancuns item;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_xsly, tv_slsj,
            tv_xsgs, tv_wtxsms, tv_ypczyj, tv_czyj, tv_note;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zancuns_detail);

        item = (Zancuns) getIntent().getSerializableExtra("item");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });

        initView();
        initData();
    }

    private void initData() {
        if (item == null) {
            return;
        }

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, item.getRank() + ""));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, "--"));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, item.getZancunTime()));
        tv_xsgs.setText(StringUtils.isEmpty(item.getKeyWord())?"--":item.getKeyWord());
        tv_wtxsms.setText(StringUtils.isEmpty(item.getZancunContent())?"--": item.getZancunContent());
        tv_ypczyj.setText(StringUtils.isEmpty(item.getResult())?"--": item.getResult());
        tv_czyj.setText(StringUtils.isEmpty(item.getSurveyContent())?"--": item.getSurveyContent());
        tv_note.setText(StringUtils.isEmpty(item.getNote())?"--": item.getNote());

    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_xsly = (TextView) findViewById(R.id.tv_xsly);
        tv_slsj = (TextView) findViewById(R.id.tv_slsj);
        tv_xsgs = (TextView) findViewById(R.id.tv_xsgs);
        tv_wtxsms = (TextView) findViewById(R.id.tv_wtxsms);
        tv_ypczyj = (TextView) findViewById(R.id.tv_ypczyj);
        tv_czyj = (TextView) findViewById(R.id.tv_czyj);
        tv_note = (TextView) findViewById(R.id.tv_note);
    }
}
