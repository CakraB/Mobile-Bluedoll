package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        // Init Component
        Spinner spinDollImage = findViewById(R.id.spinner_doll_image);
        EditText editDollName = findViewById(R.id.edit_doll_name);
        EditText editDollDesc = findViewById(R.id.edit_doll_desc);
        Button buttonSave = findViewById(R.id.button_save);
        Button buttonCancel = findViewById(R.id.button_cancel);
        // Init Array List & Adapter
        ArrayList<Doll> dolls = new ArrayList<Doll>();
        // Get Intent Data from Home Activity
        Intent getData = getIntent();
        int mDollId = getData.getIntExtra("DOLL ID", 1);
        String mDollImage = getData.getStringExtra("DOLL IMAGE");
        String mDollName = getData.getStringExtra("DOLL NAME");
        String mDollCreator = getData.getStringExtra("DOLL CREATOR");
        String mDollDesc = getData.getStringExtra("DOLL DESC");
        // Validate intent data
        if (mDollName != null || mDollDesc != null) {
            editDollName.setText(mDollName);
            editDollDesc.setText(mDollDesc);
        } else {
            editDollName.setText("");
            editDollDesc.setText("");
        }
        // Button Action Save Data and Redirect to Home Activity
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getDollName = editDollName.getText().toString();
                String getDollDesc = editDollDesc.getText().toString();
                String getDollImage = spinDollImage.getSelectedItem().toString();
                // Validate Spinner Value
                spinDollImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(ModifyActivity.this, getDollImage + " Selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(ModifyActivity.this, "Image must be chosen", Toast.LENGTH_SHORT).show();
                    }
                });
                // Validate Name Input
                try {
                    if (getDollName.isEmpty()) {
                        Toast.makeText(ModifyActivity.this, "Doll Name must be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ModifyActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
                }
                // Validate Description Input
                try {
                    if (getDollDesc.isEmpty()) {
                        Toast.makeText(ModifyActivity.this, "Doll Description must be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ModifyActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
                }
                // Move to Home Activity with passing data
                Intent i = new Intent(ModifyActivity.this, MainActivity.class);
                i.putExtra("DOLL ID", mDollId);
                i.putExtra("DOLL IMAGE", getDollImage);
                i.putExtra("DOLL NAME", getDollName);
                i.putExtra("DOLL CREATOR", "Admin");
                i.putExtra("DOLL DESC", getDollDesc);
                startActivity(i);
            }
        });
        // Button Action Cancel and Back to Home Activity
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}