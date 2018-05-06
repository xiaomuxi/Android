package com.project.archives.function.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.dao.CaseInves;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by XX on 2018/4/30.
 */

public class CaseInvesDetailActivity extends AppCompatActivity {

    CaseInves item;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_dang, tv_renda,
            tv_zzmm, tv_jcdx, tv_xsly, tv_slsj, tv_chsj,tv_ljsj,
            tv_wjxw, tv_bljg, tv_zzcl, tv_dncf, tv_xzcf, tv_xsgs,
            tv_wtxsms, tv_wjss, tv_dxclyj, tv_note;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caseinves_detail);

        item = (CaseInves) getIntent().getSerializableExtra("item");

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
        int rank = item.getRank() == null ? -1 : item.getRank();
        String shouTime = item.getShouTime().length() > 10 ? item.getShouTime().substring(0, 10) : item.getShouTime();
        String chuheTime = item.getChuheTime().length() > 10 ? item.getChuheTime().substring(0, 10) : item.getChuheTime();
        String liaojieTime = item.getLiaojieTime().length() > 10 ? item.getLiaojieTime().substring(0, 10) : item.getLiaojieTime();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_dang.setText(getResources().getString(R.string.txt_dang, item.getIsPartyMember()==1?"是":"否"));
        tv_renda.setText(getResources().getString(R.string.txt_renda, item.getIsPcongress() == 1 ? "是" : "否"));
        tv_zzmm.setText(getResources().getString(R.string.txt_zzmm, StringUtils.isEmpty(item.getPoliticsStatus())?"--": item.getPoliticsStatus()));
        tv_jcdx.setText(getResources().getString(R.string.txt_jcdx, item.getIsObject() == 1?"是":"否"));
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, StringUtils.getXiansuoByNumber(item.getXiansou()==null?-1:item.getXiansou())));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, shouTime));
        tv_chsj.setText(getResources().getString(R.string.txt_chsj, chuheTime));
        tv_ljsj.setText(getResources().getString(R.string.txt_ljsj, liaojieTime));
        tv_wjxw.setText("--");
        tv_bljg.setText(getResources().getString(R.string.txt_bljg, StringUtils.getOrganByNumber(item.getOrgan()==null?-1:item.getOrgan())));//Organ
        tv_zzcl.setText(getResources().getString(R.string.txt_zzcl, StringUtils.getResultSituationByNumber(item.getOrganization()==null?-1:item.getOrganization())));//Organization
        tv_dncf.setText(getResources().getString(R.string.txt_dncf, StringUtils.getDangjiByNumber(item.getDisTypeD()==null?-1:item.getDisTypeD())));//DisTypeD
        tv_xzcf.setText(getResources().getString(R.string.txt_xzcf, StringUtils.getZhengjiByNumber(item.getDisTypeX()==null?-1:item.getDisTypeX())));//DisTypeX
        tv_xsgs.setText(StringUtils.isEmpty(item.getTrail())?"--":item.getTrail());
        tv_wtxsms.setText(StringUtils.isEmpty(item.getDescription())?"--": item.getDescription());
        tv_wjss.setText(StringUtils.isEmpty(item.getFacts())?"--": item.getFacts());
        tv_dxclyj.setText(StringUtils.isEmpty(item.getSurveyContent())?"--": item.getSurveyContent());
        tv_note.setText(StringUtils.isEmpty(item.getNote())?"--": item.getNote());

    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_dang = (TextView) findViewById(R.id.tv_dang);
        tv_renda = (TextView) findViewById(R.id.tv_renda);
        tv_zzmm = (TextView) findViewById(R.id.tv_zzmm);
        tv_jcdx = (TextView) findViewById(R.id.tv_jcdx);
        tv_xsly = (TextView) findViewById(R.id.tv_xsly);
        tv_slsj = (TextView) findViewById(R.id.tv_slsj);
        tv_chsj = (TextView) findViewById(R.id.tv_chsj);
        tv_ljsj = (TextView) findViewById(R.id.tv_ljsj);
        tv_wjxw = (TextView) findViewById(R.id.tv_wjxw);
        tv_bljg = (TextView) findViewById(R.id.tv_bljg);
        tv_zzcl = (TextView) findViewById(R.id.tv_zzcl);
        tv_dncf = (TextView) findViewById(R.id.tv_dncf);
        tv_xzcf = (TextView) findViewById(R.id.tv_xzcf);
        tv_xsgs = (TextView) findViewById(R.id.tv_xsgs);
        tv_wtxsms = (TextView) findViewById(R.id.tv_wtxsms);
        tv_wjss = (TextView) findViewById(R.id.tv_wjss);
        tv_dxclyj = (TextView) findViewById(R.id.tv_dxclyj);
        tv_note = (TextView) findViewById(R.id.tv_note);
    }

}
