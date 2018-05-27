package com.project.archives.function.detail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.Gifts;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by inrokei on 2018/5/1.
 */

public class DetailGiftListAdapter extends MyBaseAdapter<Gifts> {

    public DetailGiftListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_detail_gift;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final Gifts item = getItem(position);

        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_gift_name = (TextView) convertView.findViewById(R.id.tv_gift_name);
        TextView tv_gift_number = (TextView) convertView.findViewById(R.id.tv_gift_number);
        TextView tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
        TextView tv_note = (TextView) convertView.findViewById(R.id.tv_note);

        String time = item.getGiftHandTime() != null ? item.getGiftHandTime() : "--";
        time = time.length() > 10 ? time.substring(0, 10) : time;
        String giftName = StringUtils.isEmpty(item.getGiftName()) ? "--" : item.getGiftName();
        String giftAmount = StringUtils.isEmpty(item.getGiftHandAmount()) ? "--" : item.getGiftHandAmount();
        String giftNumber = item.getGiftNum() == null ? "--" : item.getGiftNum() + "";
        String note = StringUtils.isEmpty(item.getGiftNote()) ? "--" : item.getGiftNote();

        tv_time.setText(mContext.getResources().getString(R.string.txt_time, time));
        tv_gift_name.setText(mContext.getResources().getString(R.string.txt_gift_name, giftName));
        tv_gift_number.setText(mContext.getResources().getString(R.string.txt_mount, giftNumber));
        tv_amount.setText(mContext.getResources().getString(R.string.txt_worth, giftAmount));
        tv_note.setText(note);

        return convertView;
    }

}
