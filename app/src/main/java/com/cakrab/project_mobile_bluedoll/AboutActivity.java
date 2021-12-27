package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    DrawerLayout drawerMenu;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbarMenu;
    NavigationView navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
        setDrawerToggle();
    }

    public void init() {
        // Init and Settings Toolbar Menu
        toolbarMenu = findViewById(R.id.toolbar_drawer);
        setSupportActionBar(toolbarMenu);
        toolbarMenu.setTitleTextColor(getResources().getColor(R.color.white));
        toolbarMenu.setOverflowIcon(getDrawable(R.drawable.ic_more));
        // Init & Settings Drawer Menu
        drawerMenu = findViewById(R.id.drawer_about);
        drawerToggle = new ActionBarDrawerToggle(this, drawerMenu, toolbarMenu, R.string.open_menu, R.string.close_menu);
        // Change Hamburger Color
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        // Add toggle as Drawer Trigger
        drawerMenu.addDrawerListener(drawerToggle);
        // Add Hamburger as Trigger
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        // Init Navigation
        navigationDrawer = findViewById(R.id.navigation_drawer);
    }

    public void setDrawerToggle() {
        navigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                // Move to Home Activity
                if (itemId == R.id.option_view_all_dolls) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                // Move to Modify Activity
                if (itemId == R.id.option_create) {
                    Intent o = new Intent(getApplicationContext(), ModifyActivity.class);
                    startActivity(o);
                }
                // Move to Login Activity
                if (itemId == R.id.option_logout) {
                    Intent u = new Intent(AboutActivity.this, LoginActivity.class);
                    Toast.makeText(AboutActivity.this, "You Sign out Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(u);
                }
                return true;
            }
        });
    }

    // Set Menu Layout Kebab Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    // Set Option Menu Item Logic Kebab Menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.option_location) {
            Intent l = new Intent(AboutActivity.this, AboutActivity.class);
            startActivity(l);
        }
        return super.onOptionsItemSelected(item);
    }
}