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

public class KampusAdapter extends RecyclerView.Adapter<KampusAdapter.ViewHolder>{

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

    public KampusAdapter(List<Kampus> myDataset, Context context, RecyclerView recyclerView) {
        mKampusList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
        this.settings = new Settings(context);
    }

    @Override
    public KampusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Kampus kampus = mKampusList.get(position);

        holder.txtName.setText(kampus.getName());
        holder.txtAlamat.setText(kampus.getAlamat());
        holder.txtFakultas.setText(kampus.getFakultas());
        holder.txtAkreditasi.setText(kampus.getAkreditasi());
        holder.txtAbout.setText(kampus.getAbout());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setTitle("Option");
                builder.setMessage("Apa yang ingin anda lakukan?");

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent update = new Intent(mContext, UpdateActivity.class);
                        update.putExtra("KAMPUS_ID", kampus.getIdkampus());
                        mContext.startActivity(update);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper = new DBHelper(mContext);

                        dbHelper.deleteKampus(kampus.getIdkampus(), mContext);
                        mKampusList.remove(position);
                        mRecyclerView.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mKampusList.size());
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.create().show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mKampusList.size();
        }
}


