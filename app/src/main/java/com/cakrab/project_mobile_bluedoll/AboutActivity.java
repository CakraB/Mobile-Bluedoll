package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {

    DrawerLayout drawerMenu;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbarMenu;
    NavigationView navigationDrawer;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
        setDrawerToggle();
//        setUpJSON();

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


        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this::onMapReady);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng BnKmg = new LatLng(-6.201733, 106.7815);
        LatLng BnSyd = new LatLng(-6.200101, 106.785481);
        LatLng BnKjg = new LatLng(-6.193798,106.788164);

        ArrayList<LatLng> locationBlueDoll;

        locationBlueDoll = new ArrayList<>();

        locationBlueDoll.add(BnKmg);
        locationBlueDoll.add(BnSyd);
        locationBlueDoll.add(BnKjg);

        for(int i = 0; i <locationBlueDoll.size(); i++) {
            map.addMarker(new MarkerOptions().position(BnKmg).title("BlueDoll Anggrek"));
            map.addMarker(new MarkerOptions().position(BnSyd).title("BlueDoll Syahdan"));
            map.addMarker(new MarkerOptions().position(BnKjg).title("BlueDoll Kijang"));
            map.moveCamera(CameraUpdateFactory.newLatLng(locationBlueDoll.get(i)));
            map.animateCamera(CameraUpdateFactory.zoomTo(14));
        }
    }


    public void setUpJSON() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://bit.ly/3kwdAd5";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Map map = new Map(jsonObject.getString("name"), jsonObject.getDouble("lat"), jsonObject.getDouble("lng"));
                        String name = map.getName();
                        Double lat=map.getLat();
                        Double lng=map.getLng();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error", error.toString());
            }
        });
    }

    public void setDrawerToggle() {
        navigationDrawer.setNavigationItemSelectedListener(item -> {
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