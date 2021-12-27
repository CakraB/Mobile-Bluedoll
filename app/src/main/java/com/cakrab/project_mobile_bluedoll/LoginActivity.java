package com.cakrab.project_mobile_bluedoll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button buttonLogin;
    TextView textRegisterHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setAction();
    }

    private void init() {
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        buttonLogin = findViewById(R.id.button_login);
        textRegisterHere = findViewById(R.id.text_register_here);
    }

    private void setAction() {
        //  Button Login Action
        buttonLogin.setOnClickListener(view -> {
            String getEmail = editEmail.getText().toString();
            String getPassword = editPassword.getText().toString();
            // Validate Email Input
            try {
                if (getEmail.trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Password Input
            try {
                if (getPassword.trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error Found!, Please Try Again", Toast.LENGTH_SHORT).show();
            }
            // Validate Success and Redirect to Home
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            // Reset All Data on Input Fields
            editEmail.setText("");
            editPassword.setText("");
        });
        // Button Move to Register Page Action
        textRegisterHere.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }
}