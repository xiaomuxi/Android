package com.project.archives.common.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.archives.R;
import com.project.archives.common.manager.StatusBarManager;
import com.project.archives.common.utils.LogUtils;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.common.view.MaterialDialog;
import com.project.archives.common.view.SpinView;


/**
 * 类名称：
 * 类功能：
 * 类作者：Riky
 * 类日期：2015/12/14
 **/
public class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    public static BaseActivity mForegroundActivity = null; // 记录处于前台的Activity
    public Activity mContext;
    public ViewGroup mRootView;
    public View mTopLeft;
    public View mTopTitle;
    public View mTopRight;
    public ImageView mTopTitleImage;
    public TextView mTopTitleText;
    public TextView mTopRightText;
    public ImageView mTopRightImage;
    public ImageView mTopRightImage2;
    public ImageView mTopLeftImage;
    public TextView mTopLeftText;
    public LinearLayout mContentView;
    public LinearLayout mActionBar;
    public MaterialDialog mDialog;
    public InputMethodManager mInputManager;
    private int mActivityLayoutId;
    private FrameLayout fl_root;
    private View mStatusBarView;
    private LinearLayout mTopBarView;
    private int mStatusHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initActionBar();
        initView();
    }

    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }

    protected void init() {
        mContext = this;
//        mRootView = (ViewGroup) mContext.findViewById(android.R.id.content); //  获取content的view
        // 默认为普通风格
        mActivityLayoutId = R.layout.theme_acitity_normal;
        // 设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    public void setActivityLayoutId(int layoutId) {
        mActivityLayoutId = layoutId;
    }

    @Override
    public void setContentView(int layoutId) {
        LogUtils.i(TAG, "set content view");
        mRootView = (ViewGroup) UIUtils.inflate(this, mActivityLayoutId);
        if (mRootView != null) {
            mContentView = (LinearLayout) mRootView.findViewById(R.id.ll_content);
            if (mContentView != null) {
                View view = UIUtils.inflate(this, layoutId);
                mContentView.addView(view, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mContentView.setVisibility(View.VISIBLE);
            }
            super.setContentView(mRootView);
        } else {
            super.setContentView(layoutId);
        }
    }

    protected void initView() {
    }

    protected void initActionBar() {
        LogUtils.i(TAG, "init action bar");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setViewFitSystem(mContentView, true);
            //清除系统提供的默认保护色
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setViewFitSystem(mContentView, true);
            //清除系统提供的默认保护色
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置系统UI的显示方式
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // 添加属性可以自定义设置系统工具栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 透明导航
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setStatusBarColor(StatusBarManager.COLOR_TRANSPARENT);
//            window.setNavigationBarColor(StatusBarManager.COLOR_BLUE_LIGHT);
        } else {
            setViewFitSystem(mContentView, false);
        }
        mActionBar = (LinearLayout) mRootView.findViewById(R.id.ll_action_bar);
        if (mActionBar != null) {
            mActionBar.setVisibility(View.VISIBLE);
            mActionBar.setOrientation(LinearLayout.VERTICAL);
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initStatusBar(LinearLayout view) {
        LogUtils.i(TAG, "init status bar");
//            int height = UIUtils.getDimens(R.dimen.height_action_bar);
        mStatusHeight = StatusBarManager.getStatusBarHeight(mContext);
        if (view == null) {
            mStatusBarView = new LinearLayout(mContext);
        } else {
            mStatusBarView = view;
        }
        mStatusBarView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_main_shape));
        mStatusBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mStatusHeight));
    }

    public void setViewFitSystem(ViewGroup view, boolean isFit) {
        view.setFitsSystemWindows(isFit);
        view.setClipToPadding(isFit);
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int margin = isFit ? -StatusBarManager.getStatusBarHeight(mContext) : 0;
            p.setMargins(0, margin, 0, 0);
            view.requestLayout();
        }
    }

    public void setActionBar(int layoutId) {
        if (mActionBar != null) {
            initStatusBar(null);
            mActionBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mActionBar.addView(mStatusBarView);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mActionBar.addView(mStatusBarView);
            } else {
            }
            mTopBarView = (LinearLayout) UIUtils.inflate(this, layoutId);
            mActionBar.addView(mTopBarView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void setCustomStatusBar(LinearLayout view) {
        if (view == null) return;
        initStatusBar(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            view.setVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void setTopTitle(String title) {
        mTopTitle = mRootView.findViewById(R.id.title);
        if (mTopTitle != null) {
            mTopTitleText = (TextView) mTopTitle.findViewById(R.id.tv_title);
            if (mTopTitleText != null) {
                mTopTitleText.setText(title);
            }
        }
    }

    public void setTopTitleAndLeft(String title) {
        setTopTitle(title);
        mTopLeft = mRootView.findViewById(R.id.left);
        if (mTopLeft != null) {
            mTopLeft.setVisibility(View.VISIBLE);
            mTopLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void setTopTitleAndRight(String title) {
        setTopTitle(title);
        mTopRight = mRootView.findViewById(R.id.right);
        if (mTopRight != null) {
            mTopRight.setVisibility(View.VISIBLE);
        }
    }

    public void setTopTitleAndLeftAndRight(String title) {
        setTopTitleAndLeft(title);
        mTopRight = mRootView.findViewById(R.id.right);
        if (mTopRight != null) {
            mTopRight.setVisibility(View.VISIBLE);
        }
    }

    public void setTopTitleImage(int drawable) {
        mTopTitleImage = (ImageView) mRootView.findViewById(R.id.iv_title);
        if (mTopTitleImage != null) {
            mTopTitleImage.setImageResource(drawable);
            mTopTitleImage.setVisibility(View.VISIBLE);
        }
    }

    public void setTopLeftImage(int drawable) {
        mTopLeftImage = (ImageView) mRootView.findViewById(R.id.iv_left);
        if (mTopLeftImage != null) {
            mTopLeftImage.setImageResource(drawable);
            mTopLeftImage.setVisibility(View.VISIBLE);
        }
    }

    public void setTopLeftText(String text) {
        mTopLeftText = (TextView) mRootView.findViewById(R.id.tv_left);
        if (mTopLeftText != null) {
            mTopLeftText.setText(text);
            mTopLeftText.setVisibility(View.VISIBLE);
        }
    }

    public void setTopRightImage(int drawable) {
        mTopRightImage = (ImageView) mRootView.findViewById(R.id.iv_right);
        if (mTopRightImage != null) {
            mTopRightImage.setImageResource(drawable);
            mTopRightImage.setVisibility(View.VISIBLE);
        }
    }
    public void setTopRightImage2(int drawable) {
        mTopRightImage2 = (ImageView) mRootView.findViewById(R.id.iv_right2);
        if (mTopRightImage2 != null) {
            mTopRightImage2.setImageResource(drawable);
            mTopRightImage2.setVisibility(View.VISIBLE);
        }
    }

    public void setTopRightText(String text) {
        mTopRightText = (TextView) mRootView.findViewById(R.id.tv_right);
        if (mTopRightText != null) {
            mTopRightText.setText(text);
            mTopRightText.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyBoard() {
        if (getCurrentFocus() != null
                && getCurrentFocus().getWindowToken() != null) {
            mInputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showKeyBoard(EditText et) {
        mInputManager.showSoftInput(et, 0);
    }


    /**
     * 获取当前处于前台的activity
     */
    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }


    /**
     * 退出应用
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void showDialog(String message) {
        mDialog = new MaterialDialog(this);
        mDialog.setTitle(message);
        SpinView view = new SpinView(this);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(false);
        mDialog.show();

    }

    public void hideDialog() {
        if (null != mDialog) {
            mDialog.dismiss();
        }

    }

    /**
     * 让windows背景透明
     *
     * @param bgAlpha 比例
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    // 关闭当前Activity
    public void doBack(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                mInputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }

        return super.onTouchEvent(event);
    }
}
