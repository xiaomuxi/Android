package com.project.archives.function.main.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.utils.UIUtils;
import com.project.archives.function.main.fragment.FifthFragment;
import com.project.archives.function.main.fragment.FirstFragment;
import com.project.archives.function.main.fragment.ForthFragment;
import com.project.archives.function.main.fragment.SecondFragment;
import com.project.archives.function.main.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private ForthFragment forthFragment;
    private FifthFragment fifthFragment;
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        initFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_camera);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (!"FIRST".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("FIRST"))
                        .commit();
                currentFragment = "FIRST";
            }
        } else if (id == R.id.nav_gallery) {
            if (!"THIRD".equals(currentFragment)) {

                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("THIRD"))
                        .commit();
                currentFragment = "THIRD";
            }

        } else if (id == R.id.nav_slideshow) {
            if (!"SECOND".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("SECOND"))
                        .commit();
                currentFragment = "SECOND";
            }

        } else if (id == R.id.nav_manage) {
            if (!"FORTH".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("FORTH"))
                        .commit();
                currentFragment = "FORTH";
            }

        } else if (id == R.id.nav_age) {
            if (!"FIFTH".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("FIFTH"))
                        .commit();
                currentFragment = "FORTH";
            }
        }
        else if (id == R.id.nav_share) {
            UIUtils.showToastSafe("功能正在开发中...");
            return false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if (null == firstFragment) {
            firstFragment = new FirstFragment();
            transaction.add(R.id.frame_home_content, firstFragment);
        } else {
            transaction.show(firstFragment);
        }
        currentFragment = "FIRST";
        transaction.commitAllowingStateLoss();
    }

    private BaseActivityFragment getFragment(String type) {
        switch (type) {
            case "FIRST":
                if (null == firstFragment) {
                    firstFragment = new FirstFragment();
                }
                return firstFragment;
            case "SECOND":
                if (null == secondFragment) {
                    secondFragment = new SecondFragment();
                }
                return secondFragment;
            case "THIRD":
                if (null == thirdFragment) {
                    thirdFragment = new ThirdFragment();
                }
                return thirdFragment;
            case "FORTH":
                if (null == forthFragment) {
                    forthFragment = new ForthFragment();
                }
                return forthFragment;
            case "FIFTH":
                if (null == fifthFragment) {
                    fifthFragment = new FifthFragment();
                }
                return fifthFragment;
            default:
                if (null == firstFragment) {
                    firstFragment = new FirstFragment();
                }
                return firstFragment;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != firstFragment) {
            transaction.hide(firstFragment);
        }
        if (null != secondFragment) {
            transaction.hide(secondFragment);
        }
        if (null != thirdFragment) {
            transaction.hide(thirdFragment);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (null != fragmentManager) {
//            fragmentManager.beginTransaction().remove();
//        }
    }
}
