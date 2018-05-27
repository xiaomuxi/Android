package com.project.archives.function.detail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.detail.activity.GiftsDetailActivity;
import com.project.archives.function.detail.adapter.DetailGiftCardListAdapter;

import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class GiftCardListFragment extends BaseActivityFragment{

    private ListView listView;
    private TextView tv_empty;
    private DetailGiftCardListAdapter adapter;
    private List<GiftCards> list;

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_detail_list_giftcard);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
        tv_empty = (TextView) view.findViewById(R.id.tv_empty);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        adapter = new DetailGiftCardListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();

    }

    private void initData() {
        GiftsDetailActivity giftsDetailActivity = (GiftsDetailActivity) mContext;



        list = giftsDetailActivity.getGiftCardsList();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                checkData();
                adapter.setData(list);
            }
        }, 500);
    }

    private void checkData(){
        if (list.size() == 0) {
            listView.setVisibility(View.GONE);
            tv_empty.setVisibility(View.VISIBLE);
        }
        else {
            tv_empty.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}
