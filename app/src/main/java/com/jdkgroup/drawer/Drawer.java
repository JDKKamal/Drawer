package com.jdkgroup.drawer;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdkgroup.adapter.AdapterDrawer;
import com.jdkgroup.general.General;
import com.jdkgroup.general.GeneralImplement;
import com.jdkgroup.model.DrawerItem;
import com.jdkgroup.utils.AppKeyword;
import com.jdkgroup.utils.GlobalClass;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends AppCompatActivity {

    private Activity activity;

    private Toolbar toolBar;
    private RecyclerView recyclerView;

    private List<DrawerItem> alDrawerItem;

    private AdapterDrawer adapterDrawer;

    public static DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private AppCompatImageView aciToolBarDrawer;
    public static AppCompatTextView actToolbarTitle;
    public static AppCompatImageView aciToolbarSave, aciToolbarSearch;
    public static LinearLayout llToolbarTitle, llToolbarSave, llToolbarSearch;

    private int status_drawer;
    private String titleClick;

    private GlobalClass globalClass;
    private General general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        activity = this;

        globalClass = GlobalClass.getInstance();
        general = new GeneralImplement();
        general.NavigationBar(activity);

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        aciToolBarDrawer = (AppCompatImageView) toolBar.findViewById(R.id.aciToolBarDrawer);
        actToolbarTitle = (AppCompatTextView) toolBar.findViewById(R.id.actToolbarTitle);
        aciToolbarSave = (AppCompatImageView) toolBar.findViewById(R.id.aciToolbarSave);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        llToolbarTitle = (LinearLayout) findViewById(R.id.llToolbarTitle);
        llToolbarSave = (LinearLayout) findViewById(R.id.llToolbarSave);
        aciToolbarSearch = (AppCompatImageView) toolBar.findViewById(R.id.aciToolbarSearch);
        llToolbarSearch = (LinearLayout) toolBar.findViewById(R.id.llToolbarSearch);

        general.FontAppCompatTextView(actToolbarTitle, AppKeyword.sourcesanspro_regular);

        alDrawerItem = new ArrayList<>();
        alDrawerItem.add(new DrawerItem(AppKeyword.REDIRECT_HOME_4_1, "Home", "home", ""));
        alDrawerItem.add(new DrawerItem(AppKeyword.REDIRECT_PROFILE_4_2, "Profile", "username", ""));
        alDrawerItem.add(new DrawerItem(AppKeyword.REDIRECT_CHANGE_PASSWORD_4_3, "Change Password", "change_password", ""));
        alDrawerItem.add(new DrawerItem(AppKeyword.REDIRECT_SETTING_4_4, "Setting", "setting", ""));
        alDrawerItem.add(new DrawerItem(AppKeyword.REDIRECT_CONTACT_US_4_5, "Contact Us", "contact_us", ""));

        general.RecyclerViewListGrid(activity, recyclerView, 0, AppKeyword.RECYCLERVIEW_LIST);
        adapterDrawer = new AdapterDrawer(activity, alDrawerItem);
        recyclerView.setAdapter(adapterDrawer);

        general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_HOME_4_1);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                status_drawer = 0;

                int resID = activity.getResources().getIdentifier("va_drawer", "drawable", activity.getPackageName());
                aciToolBarDrawer.setImageResource(resID);

                if (titleClick == null) {
                    //actToolbarTitle.setText(AppKeyword.APPNAME);
                } else {
                    //actToolbarTitle.setText(titleClick);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                status_drawer = 1;

                int resID = activity.getResources().getIdentifier("va_back", "drawable", activity.getPackageName());
                aciToolBarDrawer.setImageResource(resID);

                //actToolbarTitle.setText("Menu");
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        aciToolBarDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_drawer == 0) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    int resID = activity.getResources().getIdentifier("va_back", AppKeyword.FOLDER_DRAWABLE, activity.getPackageName());
                    aciToolBarDrawer.setImageResource(resID);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    int resID = activity.getResources().getIdentifier("va_drawer", AppKeyword.FOLDER_DRAWABLE, activity.getPackageName());
                    aciToolBarDrawer.setImageResource(resID);
                }
            }
        });

        adapterDrawer.setOnItemClickLister(new AdapterDrawer.OnItemSelecteListener() {
            @Override
            public void onItemSelected(View v, int position) {
                mDrawerLayout.closeDrawers();
                DrawerItem drawerItem = alDrawerItem.get(position - 1);
                general.SwitchPassdataChangeFragment(activity, drawerItem.getRedirect());
            }
        });
    }
}
