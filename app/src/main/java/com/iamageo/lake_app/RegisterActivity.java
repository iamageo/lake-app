package com.iamageo.lake_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private TextView contract_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_register);

        initComponents();

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle("back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String text = getString(R.string.contract_text);
        SpannableString ss = new SpannableString(text);

        /*clickable spannable - terms of use and condition and privacy policy */
        ClickableSpan clickableSpan_terms = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(RegisterActivity.this, "Terms Of Use" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.primary));
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan_privacy = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(RegisterActivity.this, "Privacy and Policy" , Toast.LENGTH_LONG).show();
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.primary));
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan_terms, 31, 43, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(clickableSpan_privacy, 48, 62, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        contract_textview.setText(ss);
        contract_textview.setMovementMethod(LinkMovementMethod.getInstance());


        /* other methods here */



    }


    /* functions for inicialize components with findbyviewid */
    public void initComponents() {
        contract_textview = findViewById(R.id.contract_textview);
    }


    /* cycle of activity */
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}