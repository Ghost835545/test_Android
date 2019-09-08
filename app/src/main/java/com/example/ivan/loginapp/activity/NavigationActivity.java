package com.example.ivan.loginapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.util.Variables;
import com.example.ivan.loginapp.fragment.FragmentRating;
import com.example.ivan.loginapp.fragment.FragmentResult;
import com.example.ivan.loginapp.fragment.FragmentTests;
import com.example.ivan.loginapp.fragment.FragmentUsers;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText mPasswordView;
    private AutoCompleteTextView mLoginView;
    private Boolean flag_results;
    private Boolean clockEnd;
    private Bundle extras;
    private Menu menu;
    private MenuInflater menuInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag_results = false;
        if (Variables.getFlagResults() != null && Variables.getFlagResults()) {
            startactivity();
        }
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        if (Variables.getIdRole()== 3 ) {
            displaySelectedScreen(R.id.item_tests);
            for (int menuItemIndex = 0; menuItemIndex < menu.size(); menuItemIndex++) {
                MenuItem menuItem= menu.getItem(menuItemIndex);
                if(menuItem.getItemId() == R.id.item_tests){
                    menuItem.setVisible(true);
                }
                if(menuItem.getItemId() == R.id.item_result){
                    menuItem.setVisible(true);
                }
                if(menuItem.getItemId() == R.id.item_rating){
                    menuItem.setVisible(true);
                }
            }

        }
        if (Variables.getIdRole()== 2 ) {
            displaySelectedScreen(R.id.item_created_tests);
            for (int menuItemIndex = 0; menuItemIndex < menu.size(); menuItemIndex++) {
                MenuItem menuItem= menu.getItem(menuItemIndex);
                if(menuItem.getItemId() == R.id.item_created_tests){
                    menuItem.setVisible(true);
                }
            }

        }
        TextView Fio_header_nav = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Fio_header_nav);
        MenuItem settingsItem = menu.findItem(R.id.item_users);
        String s =settingsItem.getTitle().toString();
        if (Variables.getFIO() != null) {
            Fio_header_nav.setText(Variables.getFIO());
        }
    }

    public void startactivity() {
        Intent intent = new Intent(this, ResultsOfSelectedTestActivity.class);
        startActivity(intent);
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
        Boolean flag = false;
        // Inflate the menu; this adds items to the action bar if it is present.
        if (Variables.getIdRole()==2){
            flag = true;
        }
        getMenuInflater().inflate(R.menu.answers_of_test, menu);
        return flag;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.answers:
                return true;
            case R.id.with_answers:
                if (item.isChecked()){
                    item.setChecked(false);
                    Variables.setRightAnswers(false);
                }else{
                    item.setChecked(true);
                    Variables.setRightAnswers(true);
                }
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected

        switch (itemId) {
            case R.id.item_users:
                fragment = new FragmentUsers();
                break;
            case R.id.item_result:
                fragment = new FragmentResult();
                break;
            case R.id.item_tests:
                fragment = new FragmentTests();
                break;
            case R.id.item_created_tests:
                fragment = new FragmentTests();
                break;
            case R.id.item_rating:
                fragment = new FragmentRating();
                break;
        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
