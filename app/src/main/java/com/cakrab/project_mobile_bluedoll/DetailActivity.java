package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    Button detailShare, detailBack;
    ImageView detailImage;
    TextView textDetailName, textDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        setAction();
    }

    private void init() {
        // Init Component
        detailShare = findViewById(R.id.button_detail_share);
        detailBack = findViewById(R.id.button_detail_back);
        detailImage = findViewById(R.id.image_detail);
        textDetailName = findViewById(R.id.text_detail_name);
        textDetailDescription = findViewById(R.id.text_detail_description);
    }

    private void setAction() {
        // Get Data From Home Activity
        Intent getData = getIntent();
        String dollDetailImage = getData.getStringExtra("DOLL IMAGE");
        String dollDetailName = getData.getStringExtra("DOLL NAME");
        String dollDetailDescription = getData.getStringExtra("DOLL DESC");
        // Mapping Image by String Image from database
        Map<String, Integer> setImageView = new HashMap<>();
        setImageView.put("Brown Doll",R.drawable.img_brown);
        setImageView.put("Panda Doll",R.drawable.img_panda);
        setImageView.put("White Doll",R.drawable.img_white);
        // Set Data that get from Home Activity
        detailImage.setImageResource(setImageView.get(dollDetailImage));
        textDetailName.setText(dollDetailName);
        textDetailDescription.setText(dollDetailDescription);
        // Button Share Action
        detailShare.setOnClickListener(view -> {
            Intent intentShare = new Intent();
            intentShare.setAction(Intent.ACTION_SEND);
            intentShare.putExtra(Intent.EXTRA_TEXT, "Hey, check this doll from BlueDoll! It’s the " + dollDetailName + " and it’s so awesome!" + " - User");
            intentShare.setType("text/plain");
            startActivity(intentShare);
        });
        // Button Back Action
        detailBack.setOnClickListener(view -> {
            Intent intentBack = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intentBack);
        });
    }
}