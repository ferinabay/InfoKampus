package com.ferinabay.infokampus1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ferinabay.infokampus1.activity.AddActivity;
import com.ferinabay.infokampus1.activity.KampusActivity;
import com.ferinabay.infokampus1.activity.LoginActivity;
import com.ferinabay.infokampus1.activity.SettingsActivity;
import com.ferinabay.infokampus1.session.Session;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private Session session;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void launchTambahKampus(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
    public void launchListKampus(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, KampusActivity.class);
        startActivity(intent);
    }
    public void launchSetting(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
