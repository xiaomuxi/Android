package com.project.archives.function.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.function.main.bean.PersonModel;

/**
 * Created by inrokei on 2018/4/25.
 */

public class ProblemAdapter extends MyBaseAdapter<PersonModel>{
    private Context context;
    public ProblemAdapter(Context c) {
        super(c);
        context = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_list;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        PersonModel person = getItem(position);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_level = (TextView) convertView.findViewById(R.id.tv_type);
        TextView tv_age = (TextView) convertView.findViewById(R.id.tv_phone);
        TextView tv_sex = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_money);

        tv_no.setText(person.getCode());
        tv_name.setText(person.getName());
        tv_level.setText(person.getLevel());
        tv_age.setText(person.getAge()+"岁");
        tv_sex.setText(person.getSex());
        tv_company.setText(person.getCompany());
        tv_level.setText(person.getLevel());

        return convertView;
    }
}
