package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Init Component
        Button detailShare = findViewById(R.id.button_detail_share);
        Button detailBack = findViewById(R.id.button_detail_back);
        ImageView detailImage = findViewById(R.id.image_detail);
        TextView textDetailName = findViewById(R.id.text_detail_name);
        TextView textDetailDescription = findViewById(R.id.text_detail_description);
        // Get Data From Home Activity
        Intent getData = getIntent();
        String dollDetailImage = getData.getStringExtra("DOLL IMAGE");
        String dollDetailName = getData.getStringExtra("DOLL NAME");
        String dollDetailDescription = getData.getStringExtra("DOLL DESC");
        // Mapping Image by String Image from database
        Map<String, Integer> setImageView = new HashMap<String, Integer>();
        setImageView.put("Blue Doll",R.drawable.ic_user);
        setImageView.put("Red Doll",R.drawable.ic_email);
        setImageView.put("Brown Doll",R.drawable.ic_password);
        setImageView.put("White Doll",R.drawable.ic_text);
        // Set Data that get from Home Activity
        detailImage.setImageResource(setImageView.get(dollDetailImage));
        textDetailName.setText(dollDetailName);
        textDetailDescription.setText(dollDetailDescription);
        // Button Share Action
        detailShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShare = new Intent();
                intentShare.setAction(Intent.ACTION_SEND);
                intentShare.putExtra(Intent.EXTRA_TEXT, "Hey, check this doll from BlueDoll! It’s the " + dollDetailName + " and it’s so awesome!" + " - User");
                intentShare.setType("text/plain");
                startActivity(intentShare);
            }
        });
        // Button Back Action
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });
    }
}