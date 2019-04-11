package com.ferinabay.infokampus1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ferinabay.infokampus1.MainActivity;
import com.ferinabay.infokampus1.R;
import com.ferinabay.infokampus1.adapter.KampusUserAdapter;
import com.ferinabay.infokampus1.helper.DBHelper;
import com.ferinabay.infokampus1.session.Session;

public class UserKampusActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManeger;
    private DBHelper dbHelper;
    private KampusUserAdapter adapter;
    private String filter = "";
    private static final String LOG_TAG = KampusActivity.class.getSimpleName();

    public static UserKampusActivity kampusAct;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userkampus);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kampusAct = this;

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManeger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManeger);

        populateRecyclerView(filter);

    }

    public void populateRecyclerView(String filter) {
        dbHelper = new DBHelper(this);
        adapter = new KampusUserAdapter(dbHelper.kampuslist(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nama:
                filter = "name";
                populateRecyclerView(filter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
