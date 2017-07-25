package com.ayotong.miranda;

//import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayotong.miranda.database.UserInfoDB;
import com.ayotong.miranda.app.About_fragment;
import com.ayotong.miranda.app.Article_fragment;
import com.ayotong.miranda.app.Home_fragment;
import com.ayotong.miranda.app.Profile_fragment;
import com.ayotong.miranda.app.Stat_fragment;
import com.ayotong.miranda.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    UserInfo user;
    UserInfoDB userdb;
    TextView name, cond,level;
    int fragID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Home_fragment fragment = new Home_fragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header=navigationView.getHeaderView(0);

        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        name = (TextView)header.findViewById(R.id.username);
        cond = (TextView)header.findViewById(R.id.condisi);
        level = (TextView)findViewById(R.id.level);

        userdb = new UserInfoDB(getApplicationContext());
        user = new UserInfo();

        Log.i("Action: ", "userdb created");
        user = userdb.loadInfo();
        Log.i("Action: ", "success loading info");
        name.setText(user.getUsername()); //nama
        Log.i("Action: ", "get username");
        cond.setText(user.genderToText(user.getGender())); //iki ganti gender ae
        level.setText("100"); //total xp

        ImageView img = (ImageView)header.findViewById(R.id.profile_image);
        Resources res = getResources(); // need this to fetch the drawable
        Drawable draw = res.getDrawable( R.drawable.ic_logo );
        img.setImageDrawable(draw);
        navigationView.getMenu().getItem(0).setChecked(true);
        ImageButton profile = (ImageButton) header.findViewById(R.id.btn_setting);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                navigationView.getMenu().getItem(0).setChecked(false);
                navigationView.getMenu().getItem(1).setChecked(false);
                navigationView.getMenu().getItem(2).setChecked(false);
                navigationView.getMenu().getItem(3).setChecked(false);
                openFragment(new Profile_fragment());
            }
        });

        if(getIntent().getIntExtra("fragnum", 0)==1){
            openFragment(new Profile_fragment());
        }

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_menu_feeds);
        toolbar.setOverflowIcon(drawable);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navigationView.getMenu().getItem(0).setChecked(true);
//                        Toast.makeText(getApplicationContext(),"Inbox Selected",Toast.LENGTH_SHORT).show();
//                        Home_fragment fragment = new Home_fragment();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame,fragment);
//                        fragmentTransaction.commit();
                        openFragment(new Home_fragment());
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.nav_article:
                        navigationView.getMenu().getItem(1).setChecked(true);
//                        Toast.makeText(getApplicationContext(),"Stared Selected",Toast.LENGTH_SHORT).show();
                        openFragment(new Article_fragment());
                        return true;
                    case R.id.nav_stat:
                        navigationView.getMenu().getItem(2).setChecked(true);
//                        Toast.makeText(getApplicationContext(),"Stats Selected",Toast.LENGTH_SHORT).show();
                        openFragment(new Stat_fragment());
                        return true;
                    case R.id.nav_about:
                        navigationView.getMenu().getItem(3).setChecked(true);
//                        Toast.makeText(getApplicationContext(),"Drafts Selected",Toast.LENGTH_SHORT).show();
                        openFragment(new About_fragment());
                        return true;

                }
                return true;}
        });


        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_sort_black_24dp);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_sort_black_24dp);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        //Setting the actionbarToggle to drawer layout
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//
//        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    public void openFragment(final android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}