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
import com.project.archives.common.manager.SPManager;
import com.project.archives.common.userdao.User;
import com.project.archives.common.userdao.manager.UserManager;
import com.project.archives.common.utils.LogUtils;
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

        initFirstInstall();
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

    private void initFirstInstall() {
        boolean isFirstInstall = SPManager.getPublicSP().getBoolean(SPManager.PUBLIC_FIRST_INSTALL, true);
        if (isFirstInstall) {
            SPManager.getPublicSP().putBoolean(SPManager.PUBLIC_FIRST_INSTALL, false);
            User user = new User();
            user.setUserName("admin");
            user.setPassword("123456");
            long result = UserManager.getInstance().insertUserInfo(user);
            LogUtils.i(TAG, "Insert user success : " +result);
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_go:

                toLogin();
                break;
        }
    }

    private void toLogin() {

        if (!checkInsert()) {
            return;
        }

        showDialog("正在登录中...");

        UIUtils.postDelayed(new Runnable(){
            public void run() {
                hideDialog();
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                User user = UserManager.getInstance().getUserByNameAndPassword(account, password);
                hideDialog();
                if (user == null) {
                    UIUtils.showToastSafe("账号密码错误！");
                    return;
                }

                startActivity(new Intent(mContext, HomeActivity.class));
                finish();
            }
        }, 8000);
    }
}

