package com.iamageo.lake_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    /* textviews links */
    private TextView textView_fg_pass;
    private TextView textView_have_account;

    /* variables input */
    private TextInputEditText mEmail;
    private TextInputEditText  mPassword;
    private Button btn_login;

    /* firebase */
    private FirebaseAuth mFirebaseAuth;

    final Loading loadingDialog = new Loading(LoginActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_login);

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        spannableText();

        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(v -> {

            attemptLogin();

        });



    }

    /* functions for inicialize components with findbyviewid */
    public void initComponents() {
        textView_fg_pass = findViewById(R.id.textView_fg_pass);
        textView_have_account = findViewById(R.id.textView_have_account);

        mEmail = findViewById(R.id.login_email);
        mPassword = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.login_btn_screen);

    }

    public void spannableText() {

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

    //TODO: AttemptLogin method
    private void attemptLogin () {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if(email.equals("") || password.equals("")) return;
        //adc mensagem de carregamento
        loadingDialog.loadingDialogLogin();

        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {

            loadingDialog.dismissDialog();
            Log.d("Lake", "signInWithEmail() onComplete: " + task.isSuccessful());

            if(!task.isSuccessful()){
                loadingDialog.dismissDialog();
                ShowErrorDialog("Failed to login");
                Log.d("Lake", "Problem signin in: " + task.getException());
            } else {
                Intent i = new Intent(LoginActivity.this, ChatActivityLake.class);
                finish();
                startActivity(i);
            }
        });

    }

    //TODO: Create error alert using alert dialog
    private void ShowErrorDialog (String message) {

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(R.string.dialog, null)
                .show();
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