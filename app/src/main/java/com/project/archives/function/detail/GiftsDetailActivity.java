package com.project.archives.function.detail;

import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.dao.GiftCards;
import com.project.archives.common.dao.GiftHandDetails;
import com.project.archives.common.dao.GiftHands;
import com.project.archives.common.dao.Gifts;
import com.project.archives.common.utils.StringUtils;

/**
 * Created by XX on 2018/4/30.
 */

public class GiftsDetailActivity extends BaseActivity {

    GiftHands item;
    Gifts giftsItem;
    GiftHandDetails giftHandDetailsItem;
    GiftCards giftCardsItem;
    TextView tv_name, tv_zhiji, tv_danwei, tv_zhiwu, tv_time1, tv_amount1,
            tv_note1, tv_time2, tv_gift_name2, tv_cord_number, tv_amount2,
            tv_note2, tv_time3, tv_gift_name3, tv_gift_number, tv_amount3, tv_note3;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_gifts_detail);

        item = (GiftHands) getIntent().getSerializableExtra("item");
        if (item != null) {
//            giftsItem = GiftsManager.getInstance().getGiftByGiftHandID(item.getId());
//            giftHandDetailsItem = GiftHandDetailsManager.getInstance().getGiftHandDetailByGiftHandID(item.getId());
//            giftCardsItem = GiftCardsManager.getInstance().getGiftCardByGiftHandID(item.getId());
        }
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("信息详情");
    }

    @Override
    protected void initView() {
        super.initView();
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_zhiji = (TextView) findViewById(R.id.tv_zhiji);
        tv_danwei = (TextView) findViewById(R.id.tv_danwei);
        tv_zhiwu = (TextView) findViewById(R.id.tv_zhiwu);
        tv_time1 = (TextView) findViewById(R.id.tv_time1);
        tv_amount1 = (TextView) findViewById(R.id.tv_amount1);
        tv_note1 = (TextView) findViewById(R.id.tv_note1);
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        tv_gift_name2 = (TextView) findViewById(R.id.tv_gift_name2);
        tv_cord_number = (TextView) findViewById(R.id.tv_cord_number);
        tv_amount2 = (TextView) findViewById(R.id.tv_amount2);
        tv_note2 = (TextView) findViewById(R.id.tv_note2);
        tv_time3 = (TextView) findViewById(R.id.tv_time3);
        tv_gift_name3 = (TextView) findViewById(R.id.tv_gift_name3);
        tv_gift_number = (TextView) findViewById(R.id.tv_gift_number);
        tv_amount3 = (TextView) findViewById(R.id.tv_amount3);
        tv_note3 = (TextView) findViewById(R.id.tv_note3);

        initData();
    }

    private void initData() {
        if (item == null) {
            return;
        }
        if (giftsItem == null) {
            giftsItem = new Gifts();
        }
        if (giftHandDetailsItem == null) {
            giftHandDetailsItem = new GiftHandDetails();
        }
        if (giftCardsItem == null) {
            giftCardsItem = new GiftCards();
        }

        int rank = item.getRank() == null ? -1 : item.getRank();
        String time1 = StringUtils.isEmpty(giftHandDetailsItem.getHandTime()) ? "--" : giftHandDetailsItem.getHandTime();
        time1 = time1.length() > 10 ? time1.substring(0, 10) : time1;
        String amount1 = giftHandDetailsItem.getHandAmount() != null ? giftHandDetailsItem.getHandAmount() + "" : "--";
        String note1 = StringUtils.isEmpty(giftHandDetailsItem.getHandNote()) ? "--" : giftHandDetailsItem.getHandNote();

        String time2 = StringUtils.isEmpty(giftCardsItem.getCardTime()) ? "--" : giftCardsItem.getCardTime();
        time2 = time2.length() > 10 ? time2.substring(0, 10) : time2;
        String cardName = StringUtils.isEmpty(giftCardsItem.getCardName()) ? "--" : giftCardsItem.getCardName();
        String cardNumber = StringUtils.isEmpty(giftCardsItem.getCardNumber()) ? "--" : giftCardsItem.getCardNumber();
        String cardAmount = giftCardsItem.getCardAmount() == null ? "--" : giftCardsItem.getCardAmount() + "";
        String note2 = StringUtils.isEmpty(giftCardsItem.getCardNote()) ? "--" : giftCardsItem.getCardNote();

        String time3 = giftsItem.getGiftHandTime() != null ? giftsItem.getGiftHandTime() : "--";
        time3 = time3.length() > 10 ? time3.substring(0, 10) : time3;
        String giftName = StringUtils.isEmpty(giftsItem.getGiftName()) ? "--" : giftsItem.getGiftName();
        String giftAmount = StringUtils.isEmpty(giftsItem.getGiftHandAmount()) ? "--" : giftsItem.getGiftHandAmount();
        String giftNumber = giftsItem.getGiftNum() == null ? "--" : giftsItem.getGiftNum() + "";
        String note3 = StringUtils.isEmpty(giftsItem.getGiftNote()) ? "--" : giftsItem.getGiftNote();

        tv_name.setText(getResources().getString(R.string.txt_name, item.getName()));
        tv_zhiji.setText(getResources().getString(R.string.txt_zhiji, StringUtils.getLevelByNumber(rank)));
        tv_danwei.setText(StringUtils.isEmpty(item.getInit())?"--":item.getInit());
        tv_zhiwu.setText(StringUtils.isEmpty(item.getPosition())?"--":item.getPosition());
        tv_time1.setText(getResources().getString(R.string.txt_time, time1));
        tv_amount1.setText(getResources().getString(R.string.txt_amount, amount1));
        tv_note1.setText(note1);
        tv_time2.setText(getResources().getString(R.string.txt_time, time2));
        tv_gift_name2.setText(getResources().getString(R.string.txt_name_mc, cardName));
        tv_cord_number.setText(getResources().getString(R.string.txt_gift_number, cardNumber));
        tv_amount2.setText(getResources().getString(R.string.txt_money_number, cardAmount));
        tv_note2.setText(note2);
        tv_time3.setText(getResources().getString(R.string.txt_time, time3));
        tv_gift_name3.setText(getResources().getString(R.string.txt_gift_name, giftName));
        tv_gift_number.setText(getResources().getString(R.string.txt_gift_number, giftNumber));
        tv_amount3.setText(getResources().getString(R.string.txt_worth, giftAmount));
        tv_note3.setText(note3);
    }

}
