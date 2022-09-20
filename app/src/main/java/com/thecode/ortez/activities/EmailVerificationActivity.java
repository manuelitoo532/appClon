package com.thecode.ortez.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thecode.ortez.R;

public class EmailVerificationActivity extends AppCompatActivity {
    /** VARIABLES*/
    private EditText emailText, passText;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        /**inicio de sesesion codigo*/
        mAuth = FirebaseAuth.getInstance();
        initializeUI();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });
    }
    private void initializeUI() {
        emailText = findViewById(R.id.input_email);
        passText = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.btn_login);

    }
    private  void loginUserAccount(){
        String email, password;
        email = emailText.getText().toString();
        password = passText.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Porfavor ingresar email...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Porfavor ingresar contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Inicio de sesion correcto", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(EmailVerificationActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "El inicio de sesion fallo!!", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
