package com.iamageo.lake_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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

public class RegisterActivity extends AppCompatActivity {

    // Constants
    public static final String CHAT_PREFS = "ChatPrefs";
    public static final String DISPLAY_NAME_KEY = "username";

    /* button */
    private Button btn_register;

    /* variables input */
    private TextView contract_textview;
    private TextInputEditText  first_name;
    private TextInputEditText email;
    private TextInputEditText  password;
    private TextInputEditText  cf_password;

    /* firebase instacevariables */
    private FirebaseAuth mFirebaseAuth;

    final Loading loadingDialog = new Loading(RegisterActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lake_register);

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        spannableText();

        mFirebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(v ->
                attemptRegistration()
        );


    }

    /* functions for inicialize components with findbyviewid */
    public void initComponents() {
        btn_register = findViewById(R.id.register_btn_screen2);

        contract_textview = findViewById(R.id.contract_textview);
        first_name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_pass);
        cf_password = findViewById(R.id.register_cf_pass);

    }

    public void spannableText() {
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
    }

    private void attemptRegistration() {
        // Reset errors displayed in the form.
        email.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password1) || !isPasswordValid(password1)) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email1)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(email1)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // TODO: Call create FirebaseUser() here
            createFirebaseUser();
            loadingDialog.loadingDialog();

        }
    }

    /* verify email is valid method */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /* verify password is valid method */
    private boolean isPasswordValid(String password) {
        String confirmPassword = Objects.requireNonNull(cf_password.getText()).toString();
        return confirmPassword.equals(password) && password.length() > 5;
    }

    //TODO: Create firebase user
    private void createFirebaseUser () {
        String firebase_email = email.getText().toString();
        String firebase_password = password.getText().toString();
        mFirebaseAuth.createUserWithEmailAndPassword(firebase_email, firebase_password).addOnCompleteListener(this, task -> {

            Log.d("Lake", "createUserOnComplete: " + task.isSuccessful());
            loadingDialog.dismissDialog();

            if(!task.isSuccessful()) {
                loadingDialog.dismissDialog();
                ShowErrorDialog("Failed to insert new user, try again");
                Log.d("Lake", "createUserOnComplete failed");
            } else {
                saveDisplayName();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });

    }

    //TODO: Save the display name to shared Preferences
    private void saveDisplayName() {
        String displayName = first_name.getText().toString();
        SharedPreferences prefs = getSharedPreferences(CHAT_PREFS, 0);
        prefs.edit().putString(DISPLAY_NAME_KEY, displayName).apply();
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