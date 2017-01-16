package de.fhdw.bfwi115a.caltracker.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*
        //Set the fragments initially
        FragmentCalenderDialog fragmentCD = new FragmentCalenderDialog();
        android.support.v4.app.FragmentTransaction fragmentTransactionCD = getSupportFragmentManager().beginTransaction();
        fragmentTransactionCD.replace(R.id.fragmentDashboard_container_SelectDate, fragmentCD);
        fragmentTransactionCD.commit();
        */
        FragmentDashboard fragmentDB = new FragmentDashboard();
        android.support.v4.app.FragmentTransaction fragmentTransactionIL = getSupportFragmentManager().beginTransaction();
        fragmentTransactionIL.replace(R.id.fragmentDashboard_container_itemList, fragmentDB);
        fragmentTransactionIL.commit();

        // toolbar_dashboard is defined in layout file app_bar_dashboard
        Toolbar toolbar_dashboard = (Toolbar) findViewById(R.id.toolbar_dashboard);
        setSupportActionBar(toolbar_dashboard);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar_dashboard, R.string.dashboard_drawer_open, R.string.dashboard_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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
            startActivity(new Intent(this, ActivitySettings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recipes) {
            Intent recipesIntent = new Intent(ActivityDashboard.this, ActivityRecipes.class);
            startActivity(recipesIntent);
        } else if (id == R.id.nav_foods) {
            Intent foodsIntent = new Intent(ActivityDashboard.this, ActivityFoods.class);
            startActivity(foodsIntent);
        } else if (id == R.id.nav_favourites) {
            Intent favIntent = new Intent(ActivityDashboard.this, ActivityFavourites.class);
            startActivity(favIntent);
        } else if (id == R.id.nav_history) {
            Intent historyIntent = new Intent(ActivityDashboard.this, ActivityHistory.class);
            startActivity(historyIntent);
        } else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent(ActivityDashboard.this, ActivitySettings.class);
            startActivity(settingsIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new FragmentCalenderDialog();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
