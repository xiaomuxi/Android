package com.project.archives.function.detail;

import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.Zancuns;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by XX on 2018/4/30.
 */

public class ZancunsDetailActivity extends BaseActivity {

    Zancuns item;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_xsly, tv_slsj,
            tv_xsgs, tv_wtxsms, tv_ypczyj, tv_czyj, tv_note;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_zancuns_detail);

        item = (Zancuns) getIntent().getSerializableExtra("item");
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
        tv_xsgs = (TextView) findViewById(R.id.tv_xsgs);
        tv_wtxsms = (TextView) findViewById(R.id.tv_wtxsms);
        tv_ypczyj = (TextView) findViewById(R.id.tv_ypczyj);
        tv_czyj = (TextView) findViewById(R.id.tv_czyj);
        tv_note = (TextView) findViewById(R.id.tv_note);

        initData();
    }


    private void initData() {
        if (item == null) {
            return;
        }

        int rank = item.getRank() == null ? -1 : item.getRank();
        String zancunTime = item.getZancunTime().length() > 10 ? item.getZancunTime().substring(0, 10) : item.getZancunTime();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_xsly.setText(getResources().getString(R.string.txt_xsly, StringUtils.getXiansuoByNumber(item.getObjectSource()==null?-1:item.getObjectSource())));//Xiansuo
        tv_slsj.setText(getResources().getString(R.string.txt_slsj, zancunTime));
        tv_xsgs.setText(StringUtils.isEmpty(item.getKeyWord())?"--":item.getKeyWord());
        tv_wtxsms.setText(StringUtils.isEmpty(item.getZancunContent())?"--": item.getZancunContent());
        tv_ypczyj.setText(StringUtils.isEmpty(item.getResult())?"--": item.getResult());
        tv_czyj.setText(StringUtils.isEmpty(item.getSurveyContent())?"--": item.getSurveyContent());
        tv_note.setText(StringUtils.isEmpty(item.getNote())?"--": item.getNote());

    }

}
