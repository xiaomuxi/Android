package com.project.archives.function.detail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by inrokei on 2018/5/1.
 */

public class DetailGiftCardListAdapter extends MyBaseAdapter<GiftCards> {

    public DetailGiftCardListAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_detail_giftcard;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final GiftCards item = getItem(position);

        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tv_gift_name = (TextView) convertView.findViewById(R.id.tv_gift_name);
        TextView tv_cord_number = (TextView) convertView.findViewById(R.id.tv_cord_number);
        TextView tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
        TextView tv_note = (TextView) convertView.findViewById(R.id.tv_note);

        String time = StringUtils.isEmpty(item.getCardTime()) ? "--" : item.getCardTime();
        time = time.length() > 10 ? time.substring(0, 10) : time;
        String cardName = StringUtils.isEmpty(item.getCardName()) ? "--" : item.getCardName();
        String cardNumber = StringUtils.isEmpty(item.getCardNumber()) ? "--" : item.getCardNumber();
        String cardAmount = item.getCardAmount() == null ? "--" : item.getCardAmount() + "";
        String note = StringUtils.isEmpty(item.getCardNote()) ? "--" : item.getCardNote();

        tv_time.setText(mContext.getResources().getString(R.string.txt_time, time));
        tv_gift_name.setText(mContext.getResources().getString(R.string.txt_name_mc, cardName));
        tv_cord_number.setText(mContext.getResources().getString(R.string.txt_gift_number, cardNumber));
        tv_amount.setText(mContext.getResources().getString(R.string.txt_money_number, cardAmount));
        tv_note.setText(note);

        return convertView;
    }

}
