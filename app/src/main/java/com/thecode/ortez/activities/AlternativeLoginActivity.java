package com.thecode.ortez.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thecode.ortez.R;

public class AlternativeLoginActivity extends AppCompatActivity {
    private Button btnEmailPass;
    private TextView btnCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternative_login);

        btnEmailPass = findViewById(R.id.btn_EmailPass);
        btnCuenta = findViewById(R.id.txtCuenta);
        btnEmailPass.setOnClickListener((v)->{openLoginPage(); });
        btnCuenta.setOnClickListener((view) -> {OpenRegister();});
    }

    private void OpenRegister() {

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void openLoginPage() {
        Intent intent = new Intent(this,EmailVerificationActivity.class);
        startActivity(intent);
    }
}
