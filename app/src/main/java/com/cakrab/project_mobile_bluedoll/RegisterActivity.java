package com.cakrab.project_mobile_bluedoll;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cakrab.project_mobile_bluedoll.Database.UserHelper;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editEmail, editPassword, editPasswordConfirm;
    RadioGroup radioGender;
    CheckBox checkTerms;
    Button buttonRegister;
    TextView textLoginHere;
    //    EditText editBirthday = findViewById(R.id.edit_birthday);
    UserHelper dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        setAction();
    }

    private void init() {
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editPasswordConfirm = findViewById(R.id.edit_password_confirm);
        radioGender = findViewById(R.id.radio_gender);
        checkTerms = findViewById(R.id.check_terms);
        buttonRegister = findViewById(R.id.button_register);
        textLoginHere = findViewById(R.id.text_login_here);
//        EditText editBirthday = findViewById(R.id.edit_birthday);
        dbUser = new UserHelper(getApplicationContext());
    }

    private void setAction() {
        // Button Register Action
        buttonRegister.setOnClickListener(view -> {
            String getName = editName.getText().toString();
            String getEmail = editEmail.getText().toString();
            String getPassword = editPassword.getText().toString();
            String getPasswordConfirm = editPasswordConfirm.getText().toString();
            int selectedGender = radioGender.getCheckedRadioButtonId();
            // Validate Name Input
            try {
                if (getName.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Email Input
            try {
                if (getEmail.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!getEmail.contains("@") || !getEmail.endsWith(".com")) {
                    Toast.makeText(RegisterActivity.this, "Email is invalid format", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Password Input
            try {
                if (getPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                    return;
                } else if (getPassword.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be contains at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Password Confirmation Input
            try {
                if (getPasswordConfirm.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password Confirmation must be filled", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!getPasswordConfirm.equals(getPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password Confirmation must be match with password", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Gender Radio Value
            if (selectedGender == -1) {
                Toast.makeText(RegisterActivity.this, "Gender must be selected", Toast.LENGTH_SHORT).show();
                return;
            }
            // Get Radio Value
            RadioButton radioButton = findViewById(selectedGender);
            String getGender = radioButton.getText().toString();
            // Validate Terms & Condition Checkbox
            if (!checkTerms.isChecked()) {
                Toast.makeText(RegisterActivity.this, "User must agreed on the terms and conditions", Toast.LENGTH_SHORT).show();
                return;
            }
            // Insert New User to User Table
            if (dbUser.createUser(getName, getEmail, getPassword, getGender, "User")) {
                Toast.makeText(RegisterActivity.this, "User Register Success", Toast.LENGTH_SHORT).show();
                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(register);
            } else {
                Toast.makeText(RegisterActivity.this, "User Register Failed", Toast.LENGTH_SHORT).show();
            }
            // Reset All Data on Input Fields
            editName.setText("");
            editEmail.setText("");
            editPassword.setText("");
            editPasswordConfirm.setText("");
            radioButton.setChecked(false);
            checkTerms.setChecked(false);
        });
        // Button Move to Login Page Action
        textLoginHere.setOnClickListener(view -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        });
    }
}