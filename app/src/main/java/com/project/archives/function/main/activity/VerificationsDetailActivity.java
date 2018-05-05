package com.project.archives.function.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.dao.Verifications;
import com.project.archives.common.utils.StringUtils;


/**
 * Created by XX on 2018/4/30.
 */

public class VerificationsDetailActivity extends AppCompatActivity {

    Verifications item;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_gwy, tv_jcdx,
          tv_xsly, tv_slsj, tv_chsj, tv_ljsj, tv_ljjl, tv_bljg, tv_zzclqk,
            tv_xsgs, tv_wtxsms, tv_note;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifications_detail);

        item = (Verifications) getIntent().getSerializableExtra("item");

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

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_gwy.setText(getResources().getString(R.string.txt_gwy, item.getIsOfficer()==1?"是":"否"));
        tv_jcdx.setText(getResources().getString(R.string.txt_jcdx, item.getIsObject() == 1?"是":"否"));
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, "--"));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, item.getTakingTime()));
        tv_chsj.setText(getResources().getString(R.string.txt_chsj, item.getVerificTime()));
        tv_ljsj.setText(getResources().getString(R.string.txt_ljsj, item.getProcessTime()));
        tv_bljg.setText(getResources().getString(R.string.txt_bljg, "--"));//Organ
        tv_ljjl.setText(getResources().getString(R.string.txt_zzcl, "--"));//Organization
        tv_zzclqk.setText("--");
        tv_xsgs.setText(StringUtils.isEmpty(item.getTrail())?"--":item.getTrail());
        tv_wtxsms.setText(StringUtils.isEmpty(item.getClues())?"--": item.getClues());
        tv_note.setText(StringUtils.isEmpty(item.getNote())?"--": item.getNote());

    }

    private void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_gwy = (TextView) findViewById(R.id.tv_gwy);
        tv_jcdx = (TextView) findViewById(R.id.tv_jcdx);
        tv_xsly = (TextView) findViewById(R.id.tv_xsly);
        tv_slsj = (TextView) findViewById(R.id.tv_slsj);
        tv_chsj = (TextView) findViewById(R.id.tv_chsj);
        tv_ljsj = (TextView) findViewById(R.id.tv_ljsj);
        tv_bljg = (TextView) findViewById(R.id.tv_bljg);
        tv_ljjl = (TextView) findViewById(R.id.tv_ljjl);
        tv_zzclqk = (TextView) findViewById(R.id.tv_zzclqk);
        tv_xsgs = (TextView) findViewById(R.id.tv_xsgs);
        tv_wtxsms = (TextView) findViewById(R.id.tv_wtxsms);
        tv_note = (TextView) findViewById(R.id.tv_note);
    }

    private String getLevelByNumber(int number) {
        String level = "--";
        switch (number) {
            case 1:
                level = "正处";
                break;
            case 2:
                level = "副处";
                break;
            case 3:
                level = "正科";
                break;
            case 4:
                level = "副科";
                break;
            case 5:
                level = "科员";
                break;
            case 6:
                level = "其他";
                break;
        }
        return level;
    }
}
