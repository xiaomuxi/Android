package com.project.archives.function.detail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.GiftHandDetails;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by inrokei on 2018/5/1.
 */

public class DetailGiftHandDetailListAdapter extends MyBaseAdapter<GiftHandDetails> {

    public DetailGiftHandDetailListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_detail_gifthanddetail;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final GiftHandDetails item = getItem(position);

        LinearLayout ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
        TextView tv_note = (TextView) convertView.findViewById(R.id.tv_note);

        String time = StringUtils.isEmpty(item.getHandTime()) ? "--" : item.getHandTime();
        time = time.length() > 10 ? time.substring(0, 10) : time;
        String amount = item.getHandAmount() != null ? item.getHandAmount() + "" : "--";
        String note = StringUtils.isEmpty(item.getHandNote()) ? "--" : item.getHandNote();

        tv_time.setText(mContext.getResources().getString(R.string.txt_time, time));
        tv_amount.setText(mContext.getResources().getString(R.string.txt_amount, amount));
        tv_note.setText(note);

        return convertView;
    }

}
