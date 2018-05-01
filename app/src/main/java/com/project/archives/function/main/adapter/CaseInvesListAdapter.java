package com.project.archives.function.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.CaseInves;
import com.project.archives.function.main.activity.UserDetailActivity;

/**
 * Created by inrokei on 2018/5/1.
 */

public class CaseInvesListAdapter extends MyBaseAdapter<CaseInves> {
    private Context context;

    public CaseInvesListAdapter(Context c) {
        super(c);
        context = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_caseinves;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final CaseInves item = getItem(position);

        LinearLayout ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_level = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tv_job = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_money);

        tv_time.setText("2018-01-01");
        tv_name.setText(item.getName());
        tv_level.setText("正处");
        String init = item.getInit().length() > 13 ? item.getInit().substring(0,12) + "..." : item.getInit();
        String itmePosition = item.getPosition().length() > 13 ? item.getPosition().substring(0,12) + "..." : item.getPosition();
        tv_company.setText(init);
        tv_job.setText(itmePosition);

        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserDetailActivity.class);
                intent.putExtra("type", "CaseInves");
                intent.putExtra("item", item);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
