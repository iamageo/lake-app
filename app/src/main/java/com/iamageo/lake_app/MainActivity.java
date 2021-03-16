package com.iamageo.lake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    /* variables */
    private Button btn_sign_in;
    private Button btn_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        /* functions for init screen components */
        initComponents();

        /* calls in buttons - login - register */
        btn_sign_up.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        btn_sign_in.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        });


    }

    public void initComponents() {
        btn_sign_in = findViewById(R.id.login_btn);
        btn_sign_up = findViewById(R.id.register_btn);
    }

    /* cycle of activity */


}