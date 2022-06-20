package com.bellaarestakadang.ajr_0372;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bellaarestakadang.ajr_0372.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout etUsername, etPassword;
    private MaterialButton btnClear, btnLogin;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(LoginActivity.this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);

        checkLogin();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUsername.getEditText().setText("");
                etPassword.getEditText().setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    if(etUsername.getEditText().getText().toString().trim().equals("cust3@email.com")
                            && etPassword.getEditText().getText().toString().trim().equals("01062022")){
                        userPreferences.setLogin(etUsername.getEditText().getText().toString().trim(),etPassword.getEditText().getText().toString().trim());
                        checkLogin();
                    }else {
                        Toast.makeText(LoginActivity.this,"Username atau Password salah",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean validateForm(){
        if(etUsername.getEditText().getText().toString().trim().isEmpty() || etPassword.getEditText().getText().toString().trim().isEmpty()){
            Toast.makeText(LoginActivity.this,"Username atau Password kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}