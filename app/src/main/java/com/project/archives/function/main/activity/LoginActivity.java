package com.project.archives.function.main.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private long exitTime = 0;
    private Context mContext;
    private EditText et_account;
    private EditText et_password;
    private Button btn_go;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void init() {
        super.init();
        mContext = this;
        setContentView(R.layout.activity_login);
        requestPermission();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
    }

    @Override
    protected void initView() {
        super.initView();

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_go = (Button) findViewById(R.id.btn_go);

        btn_go.setOnClickListener(this);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            if (i != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, 1001);
            }
        }
    }

    /**
     * 编辑框校验
     *
     * @return boolean值, 输入是否合法
     */
    private boolean checkInsert() {
        String account = et_account.getText().toString().trim();
        String pass = et_password.getText().toString().trim();

        if ("".equals(account)) {
            UIUtils.showToastSafe("账号不能为空");
            return false;
        }

        if ("".equals(pass)) {
            UIUtils.showToastSafe("密码不能为空");
            return false;
        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UIUtils.showToastSafe("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
                System.exit(0);
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_go:
//                List<GiftHands> giftHandsList = GiftsHandsManager.getInstance().getGiftHandsListByName("俞中华");
//                LogUtils.i(TAG, giftHandsList.size()+"");
//                if (giftHandsList.size() > 0){
//                    LogUtils.i(TAG, giftHandsList.get(0).toString());
//
//                    String handId = StringUtils.byteArrayToHexStr(giftHandsList.get(0).getId());
////                    String handId = new String(giftHandsList.get(0).getId());
////                    UUID uuid = UUID.nameUUIDFromBytes(giftHandsList.get(0).getId());
////                    String handId = uuid.toString();
//                    LogUtils.i(TAG, handId);
////                    "A8F38320-89EC-444E-98E4-56F8EF6D8920"
//                    List<Gifts> gifts = GiftsManager.getInstance().getGiftByGiftHandID(handId);
//                    LogUtils.i(TAG, gifts==null?"null":gifts.toString());
//                }


                toLogin();
                break;
        }
    }

    private void toLogin() {

        startActivity(new Intent(mContext, HomeActivity.class));
        finish();

        if (!checkInsert()) {
            return;
        }

        showDialog("正在登录中...");
        UIUtils.postDelayed(new Runnable(){
            public void run() {
                hideDialog();
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if ( StringUtils.equals(account, "admin") && StringUtils.equals(password, "123456")) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    finish();
                    return;
                }

                UIUtils.showToastSafe("账号密码错误！");
            }
        }, 1000);
    }
}

