package com.iamageo.lake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private TextView contract_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_register);

        initComponents();

        getSupportActionBar().setTitle("back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String text = "By signing up you agree to our Terms of Use and Privacy Policy";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(getResources().getColor(R.color.primary));
        ss.setSpan(fcsGreen, 31, 43, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(fcsGreen, 48, 62, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        contract_textview.setText(ss);


    }


    public void initComponents() {
        contract_textview = findViewById(R.id.contract_textview);
    }
}