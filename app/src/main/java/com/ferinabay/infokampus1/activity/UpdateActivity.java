package com.ferinabay.infokampus1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ferinabay.infokampus1.R;
import com.ferinabay.infokampus1.helper.DBHelper;
import com.ferinabay.infokampus1.model.Kampus;

public class UpdateActivity extends AppCompatActivity {

    private EditText mName, mAlamat, mFakultas, mAkreditasi, mAbout;
    private String name, alamat, fakultas, akreditasi, about;
    private Button mBtn;

    private DBHelper dbHelper;
    private long receivedKampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mName = findViewById(R.id.kampusName);
        mAlamat = findViewById(R.id.kampusAlamat);
        mFakultas = findViewById(R.id.kampusFakultas);
        mAkreditasi = findViewById(R.id.kampusAkreditasi);
        mAbout = findViewById(R.id.kampusAbout);
        mBtn = findViewById(R.id.updateKampus);

        dbHelper = new DBHelper(this);

        try {
            receivedKampus = getIntent().getLongExtra("KAMPUS_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Kampus queriedKampus = dbHelper.getKampus(receivedKampus);

        mName.setText(queriedKampus.getName());
        mAlamat.setText(queriedKampus.getAlamat());
        mFakultas.setText(queriedKampus.getFakultas());
        mAkreditasi.setText(queriedKampus.getAkreditasi());
        mAbout.setText(queriedKampus.getAbout());

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldValidation()) {
                    updateKampus();
                }
            }
        });
    }

    private Boolean fieldValidation() {

        name = mName.getText().toString();
        alamat = mAlamat.getText().toString();
        fakultas = mFakultas.getText().toString();
        akreditasi = mAkreditasi.getText().toString();
        about = mAbout.getText().toString();

        Boolean msg = false;

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Field nama kampus kosong!", Toast.LENGTH_SHORT).show();
        } else if (alamat.trim().isEmpty()) {
            Toast.makeText(this, "Field alamat kosong!", Toast.LENGTH_SHORT).show();
        } else if (fakultas.trim().isEmpty()) {
            Toast.makeText(this, "Field fakultas kosong!", Toast.LENGTH_SHORT).show();
        } else if (akreditasi.trim().isEmpty()) {
            Toast.makeText(this, "Field akreditasi kosong!", Toast.LENGTH_SHORT).show();
        } else if (about.trim().isEmpty()) {
            Toast.makeText(this, "Field tentang kosong!", Toast.LENGTH_SHORT).show();
        } else {
            msg = true;
        }

        return msg;
    }

    public void updateKampus() {
        String filter = "";
        Kampus updatedKampus = new Kampus(name, alamat, fakultas, akreditasi, about);

        dbHelper.updateKampus(receivedKampus, this, updatedKampus);
        KampusActivity.kampusAct.populateRecyclerView(filter);
        finish();
    }
}
