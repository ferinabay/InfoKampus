package com.ferinabay.infokampus1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ferinabay.infokampus1.R;
import com.ferinabay.infokampus1.Settings;
import com.ferinabay.infokampus1.activity.UpdateActivity;
import com.ferinabay.infokampus1.helper.DBHelper;
import com.ferinabay.infokampus1.model.Kampus;

import java.util.List;

public class KampusUserAdapter extends RecyclerView.Adapter<KampusUserAdapter.ViewHolder>{

    private List<Kampus> mKampusList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    Settings settings;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtAlamat, txtFakultas, txtAkreditasi, txtStatus, txtAbout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = v.findViewById(R.id.txtName);
            txtAlamat = v.findViewById(R.id.txtAlamat);
            txtFakultas = v.findViewById(R.id.txtFakultas);
            txtAkreditasi = v.findViewById(R.id.txtAkreditasi);
            txtStatus = v.findViewById(R.id.txtAbout);
            txtAbout = v.findViewById(R.id.txtAbout);

            float textSize = settings.getTextSize();
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
    }

    public KampusUserAdapter(List<Kampus> myDataset, Context context, RecyclerView recyclerView) {
        mKampusList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
        this.settings = new Settings(context);
    }

    @Override
    public KampusUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item, parent, false);
        KampusUserAdapter.ViewHolder vh = new KampusUserAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(KampusUserAdapter.ViewHolder holder, final int position) {
        final Kampus kampus = mKampusList.get(position);

        holder.txtName.setText(kampus.getName());
        holder.txtAlamat.setText(kampus.getAlamat());
        holder.txtFakultas.setText(kampus.getFakultas());
        holder.txtAkreditasi.setText(kampus.getAkreditasi());
        holder.txtAbout.setText(kampus.getAbout());

    }

    @Override
    public int getItemCount() {
        return mKampusList.size();
    }
}
