package com.project.archives.function.main.personlistFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.bean.MessageEvent;
import com.project.archives.common.dao.Letters;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.adapter.LettersListAdapter;
import com.project.archives.function.main.manager.LettersManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inrokei on 2018/5/1.
 */

public class PersonLettersFragment extends BaseActivityFragment {

    private ListView listView;
    private List<Letters> list = new ArrayList<>();
    private LettersListAdapter adapter;
    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_list_letters);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {
        adapter = new LettersListAdapter(mContext);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        list = LettersManager.getInstance().getLetterList(null, null, null, null);

        adapter.setData(list);
        MessageEvent messageEvent = new MessageEvent<Integer>("PERSON_LETTERS", list.size());
        EventBus.getDefault().post(messageEvent);
    }

    public void getDatabyUserName(String username) {
        if (StringUtils.isEmpty(username)) {
            list = LettersManager.getInstance().getLetterList(null, null, null, null);
        }
        else {
            list = LettersManager.getInstance().getLetterList(username, null, null, null);
        }

        adapter.setData(list);

        MessageEvent messageEvent = new MessageEvent<Integer>("PERSON_LETTERS", list.size());
        EventBus.getDefault().post(messageEvent);
    }
}
