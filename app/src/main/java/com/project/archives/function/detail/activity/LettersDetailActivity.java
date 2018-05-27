package com.project.archives.function.detail.activity;

import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by XX on 2018/4/30.
 */

public class LettersDetailActivity extends BaseActivity {

    Letters item;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_xsly, tv_slsj,
            tv_wjxw, tv_bljg, tv_zzcl, tv_xsgs, tv_ljsj,
            tv_wtxsms, tv_cljg, tv_note, tv_jys, tv_bxgd;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_letters_detail);

        item = (Letters) getIntent().getSerializableExtra("item");
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("信息详情");
    }

    @Override
    protected void initView() {
        super.initView();
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_xsly = (TextView) findViewById(R.id.tv_xsly);
        tv_slsj = (TextView) findViewById(R.id.tv_slsj);
        tv_ljsj = (TextView) findViewById(R.id.tv_ljsj);
        tv_wjxw = (TextView) findViewById(R.id.tv_wjxw);
        tv_bljg = (TextView) findViewById(R.id.tv_bljg);
        tv_zzcl = (TextView) findViewById(R.id.tv_zzcl);
        tv_xsgs = (TextView) findViewById(R.id.tv_xsgs);
        tv_wtxsms = (TextView) findViewById(R.id.tv_wtxsms);
        tv_cljg = (TextView) findViewById(R.id.tv_cljg);
        tv_note = (TextView) findViewById(R.id.tv_note);
        tv_jys = (TextView) findViewById(R.id.tv_jys);
        tv_bxgd = (TextView) findViewById(R.id.tv_bxgd);

        initData();
    }

    private void initData() {
        if (item == null) {
            return;
        }

        int rank = item.getRank() == null ? -1 : item.getRank();
        String letterTime = item.getLetterTime().length() > 10 ? item.getLetterTime().substring(0, 10) : item.getLetterTime();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_jys.setText(getResources().getString(R.string.txt_jys, "是"));
        tv_bxgd.setText(getResources().getString(R.string.txt_bxgd, "否"));
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, StringUtils.getXiansuoByNumber(item.getObjectSource()==null?-1:item.getObjectSource())));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, letterTime));
        tv_wjxw.setText("--");
        tv_bljg.setText(getResources().getString(R.string.txt_bljg, StringUtils.getOrganByNumber(item.getOrgan()==null?-1:item.getOrgan())));//Organ
        tv_zzcl.setText(getResources().getString(R.string.txt_zzclqk, StringUtils.getTrueResultByNumber(item.getTrueDegree()==null?-1:item.getTrueDegree())));//Organization
        tv_xsgs.setText(StringUtils.isEmpty(item.getKeyWord())?"--":item.getKeyWord());
        tv_wtxsms.setText(StringUtils.isEmpty(item.getLetterContent())?"--": item.getLetterContent());
        tv_cljg.setText(StringUtils.isEmpty(item.getResult())?"--": item.getResult());
        tv_note.setText(StringUtils.isEmpty(item.getNote())?"--": item.getNote());

    }
}
