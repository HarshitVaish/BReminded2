package com.example.android.breminded.ui.landing;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.breminded.R;
import com.example.android.breminded.ui.SavedBirthdayActivity;

public class HomeActivity extends AppCompatActivity {
public static final int REQ_CODE1=1;
    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigator;
    ViewPager pager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intializingScreen();
    }

    public void intializingScreen() {
        getSupportActionBar().setTitle("BReminded");
        toolbar.setSubtitle("Get Reminded");

        toolbar.setLogo(R.drawable.ic_action_birthday_cake_icon_28); // Logo Image
        toolbar.setNavigationIcon(android.R.drawable.ic_media_previous); // Navigation icon image

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10f);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* navigator=(NavigationView)findViewById(R.id.)*/
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_saved_birthdays:
                        savedBirthdayItem();
                        // Code here
                        break;
                    case R.id.item_settings:
                        // Code here
                        break;
                }
                return true;
            }
        });

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

    private class SectionPagerAdapter extends FragmentStatePagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RecentListFragment();
                case 1:
                    return new AddNewFragment();
                case 2:
                    return new CalenderFragment();
                default:
                    return new RecentListFragment();
            }
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RecentList";
                case 1:
                    return "Add New";
                case 2:
                    return "Calender";
                default:
                    return "RecentList";
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* boolean handled= false;
        switch (item.getItemId()){
            case R.id.:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                handled = true;
                break;
            case R.id.action_date_time_demo:
                Intent intent1 = new Intent(this, DateTimeDialogDemoActivity.class);
                startActivity(intent1);
                handled = true;
                break;
        }
*/
        return /*handled*/ true;
    }

    public void savedBirthdayItem() {
   /*     android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        SavedBirthdaysFragment savedbithdays = new SavedBirthdaysFragment();
        android.support.v4.app.FragmentTransaction fragTansaction = fm.beginTransaction();
*/
        Intent intent=new Intent(HomeActivity.this, SavedBirthdayActivity.class);
        startActivityForResult(intent,REQ_CODE1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE1){
            if(resultCode==RESULT_OK){

            }
        }


    }
}


