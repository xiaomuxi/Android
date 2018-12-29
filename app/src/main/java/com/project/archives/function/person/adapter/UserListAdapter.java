package com.project.archives.function.person.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.common.dao.Users;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.function.person.activity.PersonDetailActivity;

/**
 * Created by inrokei on 2018/5/5.
 */

public class UserListAdapter extends MyBaseAdapter<Users>{
    private Context mContext;

    public UserListAdapter(Context c) {
        super(c);
        mContext = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list_user;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        LinearLayout ll_item = (LinearLayout) convertView.findViewById(R.id.ll_item);

        TextView tv_code = (TextView) convertView.findViewById(R.id.tv_code);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_sex = (TextView) convertView.findViewById(R.id.tv_sex);
        TextView tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        TextView tv_zhiwu = (TextView) convertView.findViewById(R.id.tv_zhiwu);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_company);
        TextView tv_zhiji = (TextView) convertView.findViewById(R.id.tv_zhiji);
        TextView tv_type = (TextView) convertView.findViewById(R.id.tv_type);

        final Users user = getItem(position);
        String code = StringUtils.isEmpty(user.getGerenID())? "--": user.getGerenID();
        String name = StringUtils.isEmpty(user.getRealName())? "--": user.getRealName();
        String sex = user.getSex() == 2 ? "女": "男";
        String age = StringUtils.isEmpty(String.valueOf(user.getAge()))? "--": String.valueOf(user.getAge());
        String zhiwu = StringUtils.isEmpty(user.getPosition())? "--": user.getPosition();
        String company = StringUtils.isEmpty(user.getInit())? "--": user.getInit();
        String zhiji = StringUtils.getLevelByNumber(user.getRank() == null ? -1 : user.getRank());

        String type = "";
        if (user.getCaseInvesList().size() > 0) {
            type += "处分";
        }
        if (user.getVerificationsList().size() > 0) {
            if (!StringUtils.isEmpty(type)) {
                type += "|";
            }
            type += "初核";
        }
        if (user.getLettersList().size() > 0) {
            if (!StringUtils.isEmpty(type)) {
                type += "|";
            }
            type += "函询";
        }

        zhiwu = zhiwu.length()>13?zhiwu.substring(0,13) + "..." :zhiwu;
        company = company.length()>16?company.substring(0,16) + "..." :company;

        tv_code.setText(code);
        tv_name.setText(name);
        tv_sex.setText(sex);
        tv_age.setText(age);
        tv_company.setText(company);
        tv_zhiji.setText(zhiji);
        tv_zhiwu.setText(zhiwu);
        tv_type.setText(type);

        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PersonDetailActivity.class);
                intent.putExtra("item", user);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
