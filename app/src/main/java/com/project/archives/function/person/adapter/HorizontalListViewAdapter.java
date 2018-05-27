package com.project.archives.function.person.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.adapter.MyBaseAdapter;
import com.project.archives.function.person.activity.PersonProblemListActivity;
import com.project.archives.function.person.bean.ListTitle;

import static com.project.archives.common.utils.UIUtils.startActivity;

/**
 * Created by inrokei on 2018/5/27.
 */

public class HorizontalListViewAdapter extends MyBaseAdapter<ListTitle>{
    public HorizontalListViewAdapter(Context c) {
        super(c);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_horizontal_title;
    }

    @Override
    protected View getView(final int position, View convertView, ViewGroup parent, ViewHolder holder) {
        final ListTitle item = getItem(position);

        TextView tv_title = convertView.findViewById(R.id.tv_title);
        tv_title.setText(item.getName());

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProblemListActivity(position, item);
            }
        });


        return convertView;
    }

    public void goToProblemListActivity(int currentIndex, ListTitle listTitle) {
        Intent intent = new Intent(mContext, PersonProblemListActivity.class);
        intent.putExtra("item", listTitle);
        intent.putExtra("currentIndex", currentIndex);
        startActivity(intent);
    }
}
