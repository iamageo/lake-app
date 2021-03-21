package com.iamageo.lake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ChatActivityLake extends AppCompatActivity {

    private String mDisplayName;
    private ImageButton mSendButton;
    private EditText mInputText;
    private ListView mChatListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initComponents();

        setupDisplayName();

    }



    /* inicialize components input */
    public void initComponents() {
        mSendButton = findViewById(R.id.sendButton);
        mInputText = findViewById(R.id.messageInput);
        mChatListView = findViewById(R.id.chat_list_view);


    }

    //TODO: Retrieve the name from the shared preferences
    private void setupDisplayName() {
        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS, MODE_PRIVATE);
        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY, null);

        if(mDisplayName == null) mDisplayName = "Anonymus";

    }

    //TODO: SendMessage Method
    private void sendMessage() {

    }
}