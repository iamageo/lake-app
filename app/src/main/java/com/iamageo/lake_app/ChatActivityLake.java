package com.iamageo.lake_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ChatActivityLake extends AppCompatActivity {


    /* variables input */
    private String mDisplayName;
    private ImageButton mSendButton;
    private EditText mInputText;
    //private ListView mChatListView;

    /* firebase */
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        setupDisplayName();

        /* active action bar */
        Objects.requireNonNull(getSupportActionBar()).setTitle(mDisplayName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        initComponents();


        mInputText.setOnEditorActionListener((v, actionId, event) -> {
            sendMessage();
            return true;
        });

        mSendButton.setOnClickListener(v -> sendMessage());

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
        if(mDisplayName == null) mDisplayName = "anonymous";
    }

    //TODO: SendMessage Method
    private void sendMessage() {
        String input = mInputText.getText().toString();

        if(!input.equals("")) {
            InstantMessage chat = new InstantMessage(input, mDisplayName);
            mDatabaseReference.child("messages").push().setValue(chat);
            mInputText.setText("");
        }

    }
}