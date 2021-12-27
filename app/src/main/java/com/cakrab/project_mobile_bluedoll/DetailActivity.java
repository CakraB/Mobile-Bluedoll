package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Init Component
        Button detailBack = findViewById(R.id.button_detail_back);
        ImageView detailImage = findViewById(R.id.image_detail);
        TextView textDetailName = findViewById(R.id.text_detail_name);
        TextView textDetailDescription = findViewById(R.id.text_detail_description);
        // Get Data From Home Activity
        Intent getData = getIntent();
        String dollDetailImage = getData.getStringExtra("DOLL IMAGE");
        String dollDetailName = getData.getStringExtra("DOLL NAME");
        String dollDetailDescription = getData.getStringExtra("DOLL DESC");
        // Set Data that get from Home Activity
        detailImage.setImageResource(R.drawable.ic_launcher_background);
        textDetailName.setText(dollDetailName);
        textDetailDescription.setText(dollDetailDescription);
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