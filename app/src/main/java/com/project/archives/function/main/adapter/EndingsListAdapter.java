package com.project.archives.function.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.Endings;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.main.activity.EndingsDetailActivity;

/**
 * Created by inrokei on 2018/5/1.
 */

public class EndingsListAdapter extends MyBaseAdapter<Endings> {
    private Context context;

    public EndingsListAdapter(Context c) {
        super(c);
        context = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_endings;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final Endings item = getItem(position);
        LinearLayout ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_level = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tv_job = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_money);

        int rank = item.getRank() == null ? -1 : item.getRank();
        String addDate = item.getAddDate();
        addDate = StringUtils.isEmpty(addDate) ? "--" : addDate;
        addDate = addDate.length() > 10 ? addDate.substring(0, 10) : addDate;

        tv_time.setText(addDate);
        tv_name.setText(item.getName());
        tv_level.setText(getLevelByNumber(rank));
        String init = item.getInit().length() > 13 ? item.getInit().substring(0,12) + "..." : item.getInit();
        String itmePosition = item.getPosition().length() > 13 ? item.getPosition().substring(0,12) + "..." : item.getPosition();
        tv_company.setText(init);
        tv_job.setText(itmePosition);
        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EndingsDetailActivity.class);
                intent.putExtra("item", item);
                mContext.startActivity(intent);
            }
        });

        return convertView;
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
