package com.destinyapp.skripsiapps.SharedPreferance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.skripsiapps.DashboardActivity;
import com.destinyapp.skripsiapps.Maps.MapsActivity;
import com.destinyapp.skripsiapps.Model.Pahlawan;
import com.destinyapp.skripsiapps.R;

import java.util.List;

public class PahlawanAdapter extends RecyclerView.Adapter<PahlawanAdapter.ViewHolder> {
    private List<Pahlawan> mPahlawanList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView namaText;
        public TextView remarksText;
        public ImageView pahlawanImage;
        public Button Left,Right;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            namaText= (TextView) v.findViewById(R.id.tv_item_name);
            remarksText = (TextView) v.findViewById(R.id.tv_item_remarks);
            pahlawanImage = (ImageView) v.findViewById(R.id.img_item_photo);
            Left = (Button)v.findViewById(R.id.btn_set_favorite);
            Right = (Button)v.findViewById(R.id.btn_set_share);
        }
    }

    public void add(int position, Pahlawan pahlawan) {
        mPahlawanList.add(position, pahlawan);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mPahlawanList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public PahlawanAdapter(List<Pahlawan> myDataset, Context context, RecyclerView recyclerView) {
        mPahlawanList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PahlawanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.list_pahlawan, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Pahlawan pahlawan = mPahlawanList.get(position);
        holder.namaText.setText(pahlawan.getNama());
        holder.remarksText.setText(pahlawan.getRemarks());
        holder.Left.setText("Detail");

        Glide.with(mContext)
                .load(pahlawan.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.pahlawanImage);

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Apakah Anda Yakin ingin menghapus Pahlawan Favorite Anda ??")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DB_Helper dbHelper = new DB_Helper(mContext);
                                dbHelper.deletePahlawanRecord(pahlawan.getNama(),mContext);
                                Intent goInput = new Intent(mContext, DashboardActivity.class);
                                goInput.putExtra("LIST","123");
                                goInput.putExtra("DATA","All");
                                mContext.startActivities(new Intent[]{goInput});
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Perhatian !!!")
                        .setIcon(R.drawable.ic_close_black_24dp);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        holder.Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(mContext, DashboardActivity.class);
                goInput.putExtra("DetailExtra","detail");
                goInput.putExtra("Nama",pahlawan.getNama());
                goInput.putExtra("Remarks",pahlawan.getRemarks());
                goInput.putExtra("Photo",pahlawan.getPhoto());
                goInput.putExtra("Detail",pahlawan.getDetail());
                goInput.putExtra("Lahir",pahlawan.getLahir());
                goInput.putExtra("Wafat",pahlawan.getWafat());
                goInput.putExtra("Lang",pahlawan.getLangitude());
                goInput.putExtra("Long",pahlawan.getLongitude());
                mContext.startActivities(new Intent[]{goInput});
            }
        });
        holder.Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(mContext, MapsActivity.class);
                goInput.putExtra("Nama",pahlawan.getNama());
                goInput.putExtra("Remarks",pahlawan.getRemarks());
                goInput.putExtra("Photo",pahlawan.getPhoto());
                goInput.putExtra("Detail",pahlawan.getDetail());
                goInput.putExtra("Lahir",pahlawan.getLahir());
                goInput.putExtra("Wafat",pahlawan.getWafat());
                goInput.putExtra("Lang",pahlawan.getLangitude());
                goInput.putExtra("Long",pahlawan.getLongitude());
                mContext.startActivities(new Intent[]{goInput});
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPahlawanList.size();
    }



}
