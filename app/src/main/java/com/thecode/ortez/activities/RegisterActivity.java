package com.thecode.ortez.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thecode.ortez.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //Variables
    private TextView btnSing;
    private EditText emailText, nameText, passText;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Llevar a otra activity
        btnSing = findViewById(R.id.link_sign);
        btnSing.setOnClickListener((v) ->{openCuenta(); });

        //Codigo de registro en firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        initializeUI();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }


    private void initializeUI() {
        emailText = findViewById(R.id.email);
        nameText = findViewById(R.id.username);
        passText = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btn_register);
    }

    //validando registo relatime database;
    private void registerNewUser(){
        String email, password, name;
        email = emailText.getText().toString().toLowerCase().trim();
        password = passText.getText().toString().trim();
        name = nameText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText((getApplicationContext()),"Porfavor ingrese el email...", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText((getApplicationContext()),"Porfavor ingrese el Nombre..", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText((getApplicationContext()), "Porfavor ingrese la contraseña...", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Map<String, Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("name", name);
                            map.put("password", password);
                            String id = mAuth.getCurrentUser().getUid();

                            mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {

                                    if(task2.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, EmailVerificationActivity.class );
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                        }
                        else{

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Esta cuenta ya existe!!!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error al registrar!!", Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                });



    }


    private void openCuenta() {
        Intent intent = new Intent(RegisterActivity.this, EmailVerificationActivity.class);
        startActivity(intent);
    }
}