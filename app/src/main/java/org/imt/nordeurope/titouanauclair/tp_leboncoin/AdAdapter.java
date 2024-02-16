package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<AdModel> adModelArrayList;
    private final LayoutInflater inflater;

    //Constructor
    public AdAdapter(Context context, ArrayList<AdModel> adModelArrayList) {
        this.context = context;
        this.adModelArrayList = adModelArrayList;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() { return adModelArrayList.size() ; } // Return ad number
    @Override
    public Object getItem(int i) { return getItemId(i)  ; } // Return ad number i
    @Override
    public long getItemId(int i) { return adModelArrayList.get(i).getIdAnnonce() ; } // Return ad id i

    @Override
    public android.view.View getView(int i, android.view.View convertView, ViewGroup parent) {

        AdModel ad = adModelArrayList.get(i);
        convertView = inflater.inflate(R.layout.item_listview_ad, null);
// Get the image view and both text views
        ImageView imageAnnonce_item = convertView.findViewById(R.id.imageAnnonce_item);
        TextView nomAnnonce_item = convertView.findViewById(R.id.nomAnnonce_item);
        TextView prixAnnonce_item = convertView.findViewById(R.id.prixAnnonce_item) ;

        imageAnnonce_item.setImageResource(adModelArrayList.get(i).getImageAnnonce());
        nomAnnonce_item.setText(adModelArrayList.get(i).getNomAnnonce());
        prixAnnonce_item.setText(adModelArrayList.get(i).getPrixAnnonce().toString());
        return convertView;
    }
}
