package com.project.archives.function.main.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.archives.R;
import com.project.archives.common.base.activity.BaseActivity;
import com.project.archives.common.manager.SPManager;
import com.project.archives.common.userdao.User;
import com.project.archives.common.userdao.manager.UserManager;
import com.project.archives.common.utils.StringUtils;
import com.project.archives.common.utils.UIUtils;

/**
 * Created by inrokei on 2018/5/30.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener{
    private EditText editText_old;
    private EditText editText_new;
    private EditText editText_new_again;
    private User userInfo;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_reset_password);
        userInfo = SPManager.getUser();
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("修改密码");
    }

    @Override
    protected void initView() {
        super.initView();
        Button btn_true = (Button) findViewById(R.id.btn_true);
        editText_old = (EditText) findViewById(R.id.editText_old);
        editText_new = (EditText) findViewById(R.id.editText_new);
        editText_new_again = (EditText) findViewById(R.id.editText_new_again);
        btn_true.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_true:
                ensureResetPwdClick();
                break;
        }
    }

    private void ensureResetPwdClick() {
        if(!checkInput()){
            return;
        }

        if (StringUtils.isEmpty(userInfo.getUserName())) {
            UIUtils.showToastSafe("无法获取用户名，请重新登录！");
            return;
        }
        showDialog("正在请求中...");

        UIUtils.postDelayed(new Runnable() {
            public void run() {
                User oldUser = UserManager.getInstance().getUserByNameAndPassword(userInfo.getUserName(), editText_old.getText().toString());

                if (oldUser == null) {
                    hideDialog();
                    UIUtils.showToastSafe("旧密码输入错误!");
                    return;
                }
                oldUser.setPassword(editText_new.getText().toString());
                UserManager.getInstance().updateUserPassword(oldUser);
                hideDialog();
                UIUtils.showToastSafe("密码修改成功!");
                finish();
            }
        }, 300);

    }

    /**
     * 检查输入数据
     * @return
     */
    private boolean checkInput() {
        if (StringUtils.isEmpty(editText_old.getText().toString())) {
            UIUtils.showToastSafe("请输入旧密码");
            return false;
        }
        if (StringUtils.isEmpty(editText_new.getText().toString())) {
            UIUtils.showToastSafe("请输入新密码");
            return false;
        }
        if(editText_new.length() < 6 || editText_new.length()>16 ){
            UIUtils.showToastSafe("密码至少需要6位，最长16位");
            editText_new.requestFocus();
            return false;
        }
        if (StringUtils.isEmpty(editText_new_again.getText().toString())) {
            UIUtils.showToastSafe("请再次输入新密码");
            return false;
        }
        if (!editText_new.getText().toString().equals(editText_new_again.getText().toString())) {
            UIUtils.showToastSafe("两次输入的不一致");
            return false;
        }
        return true;
    }
}
