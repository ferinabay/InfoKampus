package com.ferinabay.infokampus1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ferinabay.infokampus1.activity.LoginActivity;
import com.ferinabay.infokampus1.activity.UserKampusActivity;


public class FirstActivity extends AppCompatActivity {

    private static final String LOG_TAG = FirstActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal);
    }

    public void launchlist(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, UserKampusActivity.class);
        startActivity(intent);
    }
    public void launchlogin(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
