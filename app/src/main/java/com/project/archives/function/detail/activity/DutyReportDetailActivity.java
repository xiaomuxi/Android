package com.project.archives.function.detail.activity;

import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.DutyReports;
import com.project.archives.common.utils.StringUtils;


/**
 * Created by XX on 2018/4/30.
 */

public class DutyReportDetailActivity extends BaseActivity {

    DutyReports item;
    TextView tv_name, tv_danwei, tv_zhiwu, tv_year, tv_report_date, tv_situation, tv_special_inspect_num,
            tv_problem_num, tv_interview_num, tv_new_system_num, tv_clue_num, tv_rectification_num,
            tv_evaluation, tv_join_meeting, tv_qwevaluation, tv_review_level, tv_insufficient, tv_other_item;
    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_duty_report_detail);

        item = (DutyReports) getIntent().getSerializableExtra("item");
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
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_report_date = (TextView) findViewById(R.id.tv_report_date);
        tv_situation = (TextView) findViewById(R.id.tv_situation);
        tv_special_inspect_num = (TextView) findViewById(R.id.tv_special_inspect_num);
        tv_problem_num = (TextView) findViewById(R.id.tv_problem_num);
        tv_interview_num = (TextView) findViewById(R.id.tv_interview_num);
        tv_new_system_num = (TextView) findViewById(R.id.tv_new_system_num);
        tv_clue_num = (TextView) findViewById(R.id.tv_clue_num);
        tv_rectification_num = (TextView) findViewById(R.id.tv_rectification_num);
        tv_evaluation = (TextView) findViewById(R.id.tv_evaluation);
        tv_join_meeting = (TextView) findViewById(R.id.tv_join_meeting);
        tv_qwevaluation = (TextView) findViewById(R.id.tv_qwevaluation);
        tv_review_level = (TextView) findViewById(R.id.tv_review_level);
        tv_insufficient = (TextView) findViewById(R.id.tv_insufficient);
        tv_other_item = (TextView) findViewById(R.id.tv_other_item);

        initData();
    }


    private void initData() {
        if (item == null) {
            return;
        }

        String year = StringUtils.isEmpty(item.getDutyReportYear()) ? "--" : item.getDutyReportYear();
        String reportDate = StringUtils.isEmpty(item.getDutyReportTime()) ? "--" : item.getDutyReportTime();
        reportDate = reportDate.length() > 10 ? reportDate.substring(0, 10) : reportDate;
        String situation = StringUtils.isEmpty(item.getSituation()) ? "--" : item.getSituation();
        String specialInspectNum = StringUtils.isEmpty(item.getSpecialInspectNum()) ? "--" : item.getSpecialInspectNum();
        String problemNum = StringUtils.isEmpty(item.getDiscoverProblemsNum()) ? "--" : item.getDiscoverProblemsNum();
        String interviewNum = StringUtils.isEmpty(item.getInterviewNum()) ? "--" : item.getInterviewNum();
        String newSystemNum = StringUtils.isEmpty(item.getNewSystemNum()) ? "--" : item.getNewSystemNum();
        String clueNum = StringUtils.isEmpty(item.getClueNum()) ? "--" : item.getClueNum();
        String rectificationNum = StringUtils.isEmpty(item.getRectificationNum()) ? "--" : item.getRectificationNum();
        String evaluation = StringUtils.isEmpty(item.getEvaluation()) ? "--" : item.getEvaluation();
        String joinMeeting = StringUtils.isEmpty(item.getIsJoinMeeting()) ? "--" : (StringUtils.equals(item.getIsJoinMeeting(), "1") ? "是" : "否");
        String qwEvaluation = StringUtils.isEmpty(item.getQwEvaluation()) ? "--" : item.getQwEvaluation();
        String reviewLevel = StringUtils.isEmpty(item.getReviewLevel()) ? "--" : item.getReviewLevel();
        String insufficient = StringUtils.isEmpty(item.getInsufficient()) ? "--" : item.getInsufficient();
        String otherItem = StringUtils.isEmpty(item.getOtherItem()) ? "--" : item.getOtherItem();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_year.setText(getResources().getString(R.string.txt_report_year, year));
        tv_report_date.setText(getResources().getString(R.string.txt_report_date, reportDate));
        tv_situation.setText(situation);
        tv_special_inspect_num.setText(specialInspectNum);
        tv_problem_num.setText(problemNum);
        tv_interview_num.setText(interviewNum);
        tv_new_system_num.setText(newSystemNum);
        tv_clue_num.setText(clueNum);
        tv_rectification_num.setText(rectificationNum);
        tv_evaluation.setText(evaluation);
        tv_join_meeting.setText(joinMeeting);
        tv_qwevaluation.setText(qwEvaluation);
        tv_review_level.setText(reviewLevel);
        tv_insufficient.setText(insufficient);
        tv_other_item.setText(otherItem);
    }

}
