package com.ferinabay.infokampus1.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ferinabay.infokampus1.MainActivity;
import com.ferinabay.infokampus1.R;
import com.ferinabay.infokampus1.adapter.KampusAdapter;
import com.ferinabay.infokampus1.helper.DBHelper;
import com.ferinabay.infokampus1.session.Session;

public class KampusActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManeger;
    private DBHelper dbHelper;
    private KampusAdapter adapter;
    private String filter = "";
    private static final String LOG_TAG = KampusActivity.class.getSimpleName();

    public static KampusActivity kampusAct;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampus);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kampusAct = this;

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManeger = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManeger);

        populateRecyclerView(filter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(KampusActivity.this, AddActivity.class);
                startActivity(i);
            }
        });
    }

    public void populateRecyclerView(String filter) {
        dbHelper = new DBHelper(this);
        adapter = new KampusAdapter(dbHelper.kampuslist(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nama:
                filter = "name";
                populateRecyclerView(filter);
                return true;
            case R.id.kembali:
                Intent intent1= new Intent(KampusActivity.this,MainActivity.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
