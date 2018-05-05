package com.project.archives.function.main.activity;

import android.content.Intent;
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
import com.project.archives.function.main.fragment.*;
import com.project.archives.function.person.activity.PersonActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private long exitTime = 0;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private PersonFragment personFragment;
    private CompanyFragment companyFragment;
    private TypeFragment typeFragment;
    private AgeFragment ageFragment;
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
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UIUtils.showToastSafe("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
                System.exit(0);
            }

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

        if (id == R.id.nav_home) {
            if (!"HOME".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("HOME"))
                        .commitAllowingStateLoss();
                currentFragment = "HOME";
            }
        } else if (id == R.id.nav_person) {
            if (!"PERSON".equals(currentFragment)) {
                startActivity(new Intent(this, PersonActivity.class));
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frame_home_content, getFragment("PERSON"))
//                        .commitAllowingStateLoss();
                currentFragment = "PERSON";
            }

        } else if (id == R.id.nav_company) {
            if (!"COMPANY".equals(currentFragment)) {
                startActivity(new Intent(this, CompanyActivity.class));
//                fragmentManager.beginTransaction()
//                        .replace(R.id.frame_home_content, getFragment("COMPANY"))
//                        .commitAllowingStateLoss();
                currentFragment = "COMPANY";
            }

        } else if (id == R.id.nav_type) {
            if (!"TYPE".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("TYPE"))
                        .commitAllowingStateLoss();
                currentFragment = "TYPE";
            }

        } else if (id == R.id.nav_age) {
            if (!"AGE".equals(currentFragment)) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_home_content, getFragment("AGE"))
                        .commitAllowingStateLoss();
                currentFragment = "AGE";
            }

        } else if (id == R.id.nav_setting) {
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
        if (null == homeFragment) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.frame_home_content, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        currentFragment = "HOME";
        transaction.commitAllowingStateLoss();
    }

    private BaseActivityFragment getFragment(String type) {
        switch (type) {
            case "HOME":
                if (null == homeFragment) {
                    homeFragment = new HomeFragment();
                }
                return homeFragment;
            case "PERSON":
                if (null == personFragment) {
                    personFragment = new PersonFragment();
                }
                return personFragment;
            case "COMPANY":
                if (null == companyFragment) {
                    companyFragment = new CompanyFragment();
                }
                return companyFragment;
            case "TYPE":
                if (null == typeFragment) {
                    typeFragment = new TypeFragment();
                }
                return typeFragment;
            case "AGE":
                if (null == ageFragment) {
                    ageFragment = new AgeFragment();
                }
                return ageFragment;
            default:
                if (null == homeFragment) {
                    homeFragment = new HomeFragment();
                }
                return homeFragment;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (null != homeFragment) {
            transaction.hide(homeFragment);
        }
        if (null != personFragment) {
            transaction.hide(personFragment);
        }
        if (null != companyFragment) {
            transaction.hide(companyFragment);
        }
        if (null != typeFragment) {
            transaction.hide(typeFragment);
        }
        if (null != ageFragment) {
            transaction.hide(ageFragment);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != fragmentManager) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (homeFragment != null) {
                transaction.remove(homeFragment);
            }
            if (companyFragment != null) {
                transaction.remove(companyFragment);
            }
            if (personFragment != null) {
                transaction.remove(personFragment);
            }
            if (typeFragment != null) {
                transaction.remove(typeFragment);
            }
            if (ageFragment != null) {
                transaction.remove(ageFragment);
            }

            transaction.commit();
        }
    }
}
