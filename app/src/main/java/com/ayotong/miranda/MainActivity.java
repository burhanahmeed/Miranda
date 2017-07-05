package com.ayotong.miranda;


import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by burhan on 01/07/17.
 */

public class MainActivity extends ActionBarActivity{

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home","Articles","Statistics","About"};
    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_article,R.drawable.ic_stat,R.drawable.ic_about};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Tehhutan";
    String COND = "Healthy";
    int PROFILE = R.drawable.teh;

    private Toolbar toolbar;                              // Declaring the Toolbar Object
    NavigationView mNavigationView;                           // Declaring RecyclerView
    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                   // Declaring DrawerLayout
    TextView name;
    TextView cond;
    MenuItem saveMenu;

    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setNavigationIcon(R.drawable.menus);
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
                toolbar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Drawer.openDrawer(Gravity.LEFT);
                    }
                });
            return false;}
        });

//        mNavigationView = (NavigationView) findViewById(R.id.navigation_view); // Assigning the RecyclerView Object to the xml View
//
//        View header=mNavigationView.getHeaderView(0);
///*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
////        name = (TextView)header.findViewById(R.id.name);
////        cond = (TextView)header.findViewById(R.id.cond);
////        name.setText("Teh");
////        cond.setText("SSSS");
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//
//            // This method will trigger on item Click of navigation menu
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//
//                //Checking if the item is in checked state or not, if not make it in checked state
//                if(menuItem.isChecked()) menuItem.setChecked(false);
//                else menuItem.setChecked(true);
//
//                //Closing drawer on item click
//                Drawer.closeDrawers();
//
//                //Check to see which item was being clicked and perform appropriate action
//                switch (menuItem.getItemId()){
//
//
//                    //Replacing the main content with ContentFragment Which is our Inbox View;
//                    case R.id.home:
//                        Toast.makeText(getApplicationContext(),"Home Selected",Toast.LENGTH_SHORT).show();
//                        Home_fragment fragment = new Home_fragment();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame,fragment);
//                        fragmentTransaction.commit();
//                        break;
//
//                    // For rest of the options we just show a toast on click
//
//                    case R.id.articles:
//                        Toast.makeText(getApplicationContext(),"Articles Selected",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.stat:
//                        Toast.makeText(getApplicationContext(),"Stats Selected",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.about:
//                        Toast.makeText(getApplicationContext(),"About Selected",Toast.LENGTH_SHORT).show();
//                       break;
//
//                    default:
//                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
//                        break;
//
//                } return true;
//            }
//        });

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,COND, PROFILE, this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
//         And passing the titles,icons,header view name, header view email,
//         and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView


        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
                    Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildPosition(child),Toast.LENGTH_SHORT).show();

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });


        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,null,R.string.app_name,R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem about= menu.findItem(R.id.about);
//        MenuItem article= menu.findItem(R.id.articles);
//        MenuItem stat= menu.findItem(R.id.stat);
//        MenuItem home= menu.findItem(R.id.home);
//        about.setVisible(false);
//        home.setVisible(false);
//        stat.setVisible(false);
//        article.setVisible(false);
////        super.onPrepareOptionsMenu(menu);
//        return false;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//
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
