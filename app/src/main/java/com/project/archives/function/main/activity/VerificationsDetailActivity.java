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
        String verificationTime = item.getVerificTime().length() > 10 ? item.getVerificTime().substring(0, 10) : item.getVerificTime();
        String processTime = item.getProcessTime().length() > 10 ? item.getProcessTime().substring(0, 10) : item.getProcessTime();
        String takingTime = item.getTakingTime().length() > 10 ? item.getTakingTime().substring(0, 10) : item.getTakingTime();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_gwy.setText(getResources().getString(R.string.txt_gwy, item.getIsOfficer()==1?"是":"否"));
        tv_jcdx.setText(getResources().getString(R.string.txt_jcdx, item.getIsObject() == 1?"是":"否"));
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, StringUtils.getXiansuoByNumber(item.getObjectSource()==null?-1:item.getObjectSource())));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, verificationTime));
        tv_chsj.setText(getResources().getString(R.string.txt_chsj, processTime));
        tv_ljsj.setText(getResources().getString(R.string.txt_ljsj, takingTime));
        tv_bljg.setText(getResources().getString(R.string.txt_bljg, StringUtils.getOrganByNumber(item.getOrgan()==null?-1:item.getOrgan())));//Organ
        tv_ljjl.setText(StringUtils.getLiaoJieResult(item.getTakingResult()==null?-1:item.getTakingResult()));
        tv_zzclqk.setText(getResources().getString(R.string.txt_zzclqk, StringUtils.getResultSituationByNumber(item.getResultSituation() == null ? -1 : item.getResultSituation())));
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

}
