package com.project.archives.function.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.DutyReports;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.detail.activity.DutyReportDetailActivity;

/**
 * Created by inrokei on 2018/5/1.
 */

public class DutyReportListAdapter extends MyBaseAdapter<DutyReports> {
    private Context context;

    public DutyReportListAdapter(Context c) {
        super(c);
        context = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_duty_report;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final DutyReports item = getItem(position);
        LinearLayout ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_job = (TextView) convertView.findViewById(R.id.tv_position);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_init);

        String addDate = item.getAddDate();
        addDate = StringUtils.isEmpty(addDate) ? "--" : addDate;
        addDate = addDate.length() > 10 ? addDate.substring(0, 10) : addDate;

        tv_time.setText(addDate);
        tv_name.setText(item.getName());
        String init = StringUtils.isEmpty(item.getInit()) ? "--" : item.getInit();
        init = init.length() > 13 ? init.substring(0,12) + "..." : init;
        String itemPosition = StringUtils.isEmpty(item.getPosition()) ? "--" : item.getPosition();
        itemPosition = itemPosition.length() > 13 ? itemPosition.substring(0, 12) + "..." : itemPosition;
        tv_company.setText(init);
        tv_job.setText(itemPosition);
        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DutyReportDetailActivity.class);
                intent.putExtra("item", item);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
