package com.batch9.beritaappsjwt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batch9.beritaappsjwt.R;
import com.batch9.beritaappsjwt.entity.Berita;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    private ArrayList<Berita> berita;
    private Context context;

    public BeritaAdapter(ArrayList<Berita> berita, Context context) {
        this.berita = berita;
        this.context = context;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_berita,parent,false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        holder.txtAuthor.setText("Author : "+berita.get(position).getAuthor());
        holder.txtDesc.setText("Deskripsi : "+berita.get(position).getDeskripsi());
        holder.txtTanggal.setText("Tanggal : "+berita.get(position).getTanggal());
        holder.txtTitle.setText("Title : "+berita.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle,txtDesc,txtTanggal,txtAuthor;

        public BeritaViewHolder (@NonNull View itemview){
            super(itemview);
            txtAuthor= itemview.findViewById(R.id.txtAuthor);
            txtDesc= itemview.findViewById(R.id.txtDesc);
            txtTanggal= itemview.findViewById(R.id.txtTanggal);
            txtTitle= itemview.findViewById(R.id.txtTitle);

        }
    }
}
