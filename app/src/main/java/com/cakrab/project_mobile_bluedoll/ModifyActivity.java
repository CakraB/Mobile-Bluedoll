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

import com.cakrab.project_mobile_bluedoll.Database.DollHelper;

public class ModifyActivity extends AppCompatActivity {

    Spinner spinDollImage;
    EditText editDollName, editDollDesc;
    Button buttonSave, buttonCancel;
    DollHelper dbDoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        // Init Component
        init();
        // Do Action
        setAction();
    }

    public void init() {
        spinDollImage = findViewById(R.id.spinner_doll_image);
        editDollName = findViewById(R.id.edit_doll_name);
        editDollDesc = findViewById(R.id.edit_doll_desc);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);
        dbDoll = new DollHelper(getApplicationContext());
    }

    public void setAction() {
        // Get Intent Data from Home Activity
        Intent getData = getIntent();
        int dollId = getData.getIntExtra("DOLL ID", 1);
        String dollImage = getData.getStringExtra("DOLL IMAGE");
        String dollName = getData.getStringExtra("DOLL NAME");
        String dollCreator = getData.getStringExtra("DOLL CREATOR");
        String dollDesc = getData.getStringExtra("DOLL DESC");
        // Validate intent data
        if (dollName != null || dollDesc != null) {
            editDollName.setText(dollName);
            editDollDesc.setText(dollDesc);
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
//                String getDollImage = spinDollImage.getSelectedItem().toString();
                // Validate Spinner Value
//                spinDollImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(ModifyActivity.this, getDollImage + " Selected", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//                        Toast.makeText(ModifyActivity.this, "Image must be chosen", Toast.LENGTH_SHORT).show();
//                    }
//                });
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

                // Insert New Doll to User Table
                if (getDollName.isEmpty() && getDollDesc.isEmpty()) {
                    // Harus Cek Availibilty data yg ada di db, kalo dh
                    dbDoll.createDoll(getDollName,"Creator",getDollDesc, "Doll Image");
                    Toast.makeText(ModifyActivity.this, "Create Doll Success", Toast.LENGTH_SHORT).show();
                    Intent createDoll = new Intent(ModifyActivity.this, MainActivity.class);
                    startActivity(createDoll);
                } else if (!getDollName.isEmpty() && !getDollDesc.isEmpty()) {
                    dbDoll.updateDoll(String.valueOf(dollId), getDollName, "Creator", getDollDesc, "Doll Image");
                    Toast.makeText(ModifyActivity.this, "Update Doll Success", Toast.LENGTH_SHORT).show();
                    Intent updateDoll = new Intent(ModifyActivity.this, MainActivity.class);
                    startActivity(updateDoll);
                }

                // Move to Home Activity with passing data
//                Intent i = new Intent(ModifyActivity.this, MainActivity.class);
//                i.putExtra("DOLL ID", mDollId);
//                i.putExtra("DOLL IMAGE", getDollImage);
//                i.putExtra("DOLL NAME", getDollName);
//                i.putExtra("DOLL CREATOR", "Admin");
//                i.putExtra("DOLL DESC", getDollDesc);
//                startActivity(i);
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