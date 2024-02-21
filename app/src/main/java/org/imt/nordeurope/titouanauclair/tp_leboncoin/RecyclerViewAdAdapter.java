package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdAdapter extends
        RecyclerView.Adapter<RecyclerViewAdAdapter.RecyclerViewHolder>{
    private ArrayList<AdModel> data;
    private boolean isListView = true;

    public RecyclerViewAdAdapter(ArrayList<AdModel> data) {
        this.data = data;
    }

    public void setIsListView(boolean isListView) {
        this.isListView = isListView;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int layout;
        if(isListView) {
            layout = R.layout.item_listview_ad;
        } else {
            layout = R.layout.item_recyclerview_ad;
        }
        android.view.View view = inflater.inflate(layout, parent, false);
        return new RecyclerViewHolder(view, isListView);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // This method is called for each of the visible rows displayed in our RecyclerView. It is usually here that we update their appearance.
        AdModel ad = data.get(position);
        holder.nomAnnonce.setText(ad.getNomAnnonce());
        holder.prixAnnonce.setText(ad.getPrixAnnonce().toString());
        holder.imageView.setImageResource(ad.getImageAnnonce());
        holder.anneeAnnonce.setText(String.valueOf(ad.getAnneeAnnonce()));
    }
    @Override
    public int getItemCount() {return data.size();}
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public final TextView nomAnnonce;
        public final TextView prixAnnonce;
        public final TextView anneeAnnonce;
        public RecyclerViewHolder(@NonNull android.view.View itemView, boolean isListView) {
            super(itemView);
            if(isListView) {
                imageView = itemView.findViewById(R.id.imageAnnonce_item);
                nomAnnonce = itemView.findViewById(R.id.nomAnnonce_item);
                prixAnnonce = itemView.findViewById(R.id.prixAnnonce_item);
                anneeAnnonce = itemView.findViewById(R.id.anneeAnnonce_item);
            } else {
                imageView = itemView.findViewById(R.id.imageAnnonce_recycle);
                nomAnnonce = itemView.findViewById(R.id.nomAnnonce_recycle);
                prixAnnonce = itemView.findViewById(R.id.prixAnnonce_recycle);
                anneeAnnonce = itemView.findViewById(R.id.anneeAnnonce_recycle);
            }
        }
    }
}