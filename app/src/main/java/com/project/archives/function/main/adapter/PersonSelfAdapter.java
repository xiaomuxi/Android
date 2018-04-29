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

public class PersonSelfAdapter extends MyBaseAdapter<PersonModel>{
    private Context context;
    public PersonSelfAdapter(Context c) {
        super(c);
        context = c;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_self_list;
    }

    @Override
    protected View getView(int position, View convertView, ViewGroup parent, ViewHolder holder) {
        PersonModel person = getItem(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        TextView tv_company = (TextView) convertView.findViewById(R.id.tv_company);
        TextView tv_type1 = (TextView) convertView.findViewById(R.id.tv_type1);
        TextView tv_type2 = (TextView) convertView.findViewById(R.id.tv_type2);
        TextView tv_type3 = (TextView) convertView.findViewById(R.id.tv_type3);
        TextView tv_type4 = (TextView) convertView.findViewById(R.id.tv_type4);
        TextView tv_type5 = (TextView) convertView.findViewById(R.id.tv_type5);

        tv_name.setText(person.getName());
        tv_age.setText(person.getAge());
        tv_company.setText(person.getCompany());
        tv_type1.setText(person.getType_1()+"");
        tv_type2.setText(person.getType_2()+"");
        tv_type3.setText(person.getType_3()+"");
        tv_type4.setText(person.getType_4()+"");
        tv_type5.setText(person.getType_5()+"");

        return convertView;
    }
}
