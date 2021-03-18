package com.iamageo.lake_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextView textView_fg_pass;
    private TextView textView_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_login);

        initComponents();

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle("back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* configs for spannable */
        String text_fg_pass = getString(R.string.forgot_passwd);
        String text_have_account = getString(R.string.have_account);

        SpannableString ss_fg_pass = new SpannableString(text_fg_pass);
        SpannableString ss_have_account = new SpannableString(text_have_account);

        /*clickable spannable - terms of use and condition and privacy policy */
        ClickableSpan clickableForgotPassword = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(LoginActivity.this, "fg pass" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.white));
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableHaveAccount = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.primary));
                ds.setUnderlineText(false);
            }
        };

        ss_fg_pass.setSpan(clickableForgotPassword, 0, 15, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss_have_account.setSpan(clickableHaveAccount, 23, 30, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        textView_fg_pass.setText(ss_fg_pass);
        textView_fg_pass.setMovementMethod(LinkMovementMethod.getInstance());

        textView_have_account.setText(ss_have_account);
        textView_have_account.setMovementMethod(LinkMovementMethod.getInstance());



    }

    /* functions for inicialize components with findbyviewid */
    public void initComponents() {
        textView_fg_pass = findViewById(R.id.textView_fg_pass);
        textView_have_account = findViewById(R.id.textView_have_account);

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