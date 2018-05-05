package com.project.archives.function.person.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.Users;
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.dao.manager.ZancunsManager;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by inrokei on 2018/5/5.
 */

public class PersonDetailActivity extends BaseActivity{

    private TextView tv_caseinves, tv_verifications, tv_letters, tv_endings, tv_zancuns;

    private TextView tv_name, tv_sex, tv_birth;
    private TextView tv_age, tv_minzu, tv_jiguan;
    private TextView tv_birth_place, tv_rudang_time, tv_work_time;
    private TextView tv_health, tv_zhuanyejishuzhiwu, tv_zhuanyezhuanchang;
    private TextView tv_quanrizhijiaoyu, tv_school1;
    private TextView tv_zaizhijiaoyu, tv_school2;
    private TextView tv_xianrenzhiwu, tv_nirenzhiwu, tv_nimianzhiwu;
    private TextView tv_jianli;

    private LinearLayout ll_problem;

    private Users item;

    private long caseinvesCount = 0;
    private long verificationsCount = 0;
    private long lettersCount = 0;
    private long endingsCount = 0;
    private long zancunsCount = 0;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_person_detail);

        item = (Users) getIntent().getSerializableExtra("item");
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("个人信息");
    }

    @Override
    protected void initView() {
        super.initView();
        ll_problem = (LinearLayout) findViewById(R.id.ll_problem);
        tv_caseinves = (TextView) findViewById(R.id.tv_caseinves);
        tv_verifications = (TextView) findViewById(R.id.tv_verifications);
        tv_letters = (TextView) findViewById(R.id.tv_letters);
        tv_endings = (TextView) findViewById(R.id.tv_endings);
        tv_zancuns = (TextView) findViewById(R.id.tv_zancuns);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_birth = (TextView) findViewById(R.id.tv_birth);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_minzu = (TextView) findViewById(R.id.tv_minzu);
        tv_jiguan = (TextView) findViewById(R.id.tv_jiguan);

        tv_birth_place = (TextView) findViewById(R.id.tv_birth_place);
        tv_rudang_time = (TextView) findViewById(R.id.tv_rudang_time);
        tv_work_time = (TextView) findViewById(R.id.tv_work_time);

        tv_health = (TextView) findViewById(R.id.tv_health);
        tv_zhuanyejishuzhiwu = (TextView) findViewById(R.id.tv_zhuanyejishuzhiwu);
        tv_zhuanyezhuanchang = (TextView) findViewById(R.id.tv_zhuanyezhuanchang);

        tv_quanrizhijiaoyu = (TextView) findViewById(R.id.tv_quanrizhijiaoyu);
        tv_school1 = (TextView) findViewById(R.id.tv_school1);
        tv_zaizhijiaoyu = (TextView) findViewById(R.id.tv_zaizhijiaoyu);
        tv_school2 = (TextView) findViewById(R.id.tv_school2);

        tv_xianrenzhiwu = (TextView) findViewById(R.id.tv_xianrenzhiwu);
        tv_nirenzhiwu = (TextView) findViewById(R.id.tv_nirenzhiwu);
        tv_nimianzhiwu = (TextView) findViewById(R.id.tv_nimianzhiwu);
        tv_jianli = (TextView) findViewById(R.id.tv_jianli);

        ll_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PersonProblemListActivity.class);
                intent.putExtra("caseinves_count", caseinvesCount);
                intent.putExtra("verifications_count", verificationsCount);
                intent.putExtra("letters_count", lettersCount);
                intent.putExtra("endings_count", endingsCount);
                intent.putExtra("zancuns_count", zancunsCount);
                intent.putExtra("name", item.getRealName());
                startActivity(intent);
            }
        });

        initData();
        initCount();
    }

    private void initCount() {
        caseinvesCount = CaseInvesManager.getInstance().getCountByName(item.getRealName());
        verificationsCount = VerificationsManager.getInstance().getCountByName(item.getRealName());
        lettersCount = LettersManager.getInstance().getCountByName(item.getRealName());
        endingsCount = EndingsManager.getInstance().getCountByName(item.getRealName());
        zancunsCount = ZancunsManager.getInstance().getCountByName(item.getRealName());

        tv_caseinves.setText(getResources().getString(R.string.list_caseinves_title, String.valueOf(caseinvesCount)));
        tv_verifications.setText(getResources().getString(R.string.list_verifications_title, String.valueOf(verificationsCount)));
        tv_letters.setText(getResources().getString(R.string.list_letters_title, String.valueOf(lettersCount)));
        tv_endings.setText(getResources().getString(R.string.list_endings_title, String.valueOf(endingsCount)));
        tv_zancuns.setText(getResources().getString(R.string.list_zancuns_title, String.valueOf(zancunsCount)));
    }

    private void initData() {
        String name = getResources().getString(R.string.txt_name, item.getRealName());
        String sex = getResources().getString(R.string.txt_sex, item.getSex() == 2 ?"女":"男");
        String birth = getResources().getString(R.string.txt_birth, StringUtils.isEmpty(item.getBirth()) ? "--": item.getBirth());
        String age = getResources().getString(R.string.txt_age, String.valueOf(item.getAge()));
        String minzu = getResources().getString(R.string.txt_minzu, StringUtils.isEmpty(item.getNational())?"--":item.getNational());
        String jiguan = getResources().getString(R.string.txt_jiguan, StringUtils.isEmpty(item.getNativePlace())?"--":item.getNativePlace());
        String birthPlace = getResources().getString(R.string.txt_birth_place, StringUtils.isEmpty(item.getHomePlace())?"--":item.getHomePlace());
        String rudangTime = getResources().getString(R.string.txt_rudang_time, StringUtils.isEmpty(item.getPartyTimeStr())?"--":item.getPartyTimeStr());
        String workTime = getResources().getString(R.string.txt_work_time, StringUtils.isEmpty(item.getWorkTimeStr())?"--":item.getWorkTimeStr());
        String health = getResources().getString(R.string.txt_health, StringUtils.isEmpty(item.getHealth())?"--":item.getHealth());
        String zhuanyezhiwu = StringUtils.isEmpty(item.getZPosition())?"--":item.getZPosition();
        String zhuanyezhuanchang = StringUtils.isEmpty(item.getSpecialty())?"--":item.getSpecialty();
        String quanrizhijiaoyu = StringUtils.isEmpty(item.getQEducation())?"--":item.getQEducation();
        String school1 = StringUtils.isEmpty(item.getQSchool())?"--":item.getQSchool();
        String zaizhijiaoyu = StringUtils.isEmpty(item.getEducation())?"--":item.getEducation();
        String school2 = StringUtils.isEmpty(item.getSchool())?"--":item.getSchool();
        String xianrenzhiwu = StringUtils.isEmpty(item.getPosition())?"--":item.getPosition();
        String jianli = StringUtils.isEmpty(item.getResume())?"--":item.getResume();

        tv_name.setText(name);
        tv_sex.setText(sex);
        tv_birth.setText(birth);
        tv_age.setText(age);
        tv_minzu.setText(minzu);
        tv_jiguan.setText(jiguan);
        tv_birth_place.setText(birthPlace);
        tv_rudang_time.setText(rudangTime);
        tv_work_time.setText(workTime);
        tv_health.setText(health);
        tv_zhuanyejishuzhiwu.setText(zhuanyezhiwu);
        tv_zhuanyezhuanchang.setText(zhuanyezhuanchang);
        tv_quanrizhijiaoyu.setText(quanrizhijiaoyu);
        tv_school1.setText(school1);
        tv_school2.setText(school2);
        tv_xianrenzhiwu.setText(xianrenzhiwu);
        tv_jianli.setText(jianli);
    }
}
