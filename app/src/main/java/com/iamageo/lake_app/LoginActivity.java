package com.iamageo.lake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_login);


        getSupportActionBar().setTitle("back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Objects.requireNonNull(getSupportActionBar()).setTitle("Calculo composto");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}