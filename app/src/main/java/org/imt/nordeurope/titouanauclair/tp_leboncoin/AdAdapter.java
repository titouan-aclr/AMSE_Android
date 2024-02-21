package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public void loadImage(String resName, ImageView imageView) {
        int resId = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());

        if (resId != 0) {
            Drawable drawable = context.getResources().getDrawable(resId);
            imageView.setImageDrawable(drawable);
        }
    }
    public void loadImageFromContentUri(String filePath, ImageView imageView) {
        Glide.with(context)
                .load(filePath)
                .into(imageView);
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Context context;

        AdModel ad = adModelArrayList.get(i);
        convertView = inflater.inflate(R.layout.item_listview_ad, null);

        ImageView imageAnnonce_item = convertView.findViewById(R.id.imageAnnonce_item);
        TextView nomAnnonce_item = convertView.findViewById(R.id.nomAnnonce_item);
        TextView prixAnnonce_item = convertView.findViewById(R.id.prixAnnonce_item) ;
        TextView anneeAnnonce_item = convertView.findViewById(R.id.anneeAnnonce_item) ;

        String file_path = ad.getImageAnnonce();
        if (file_path.endsWith(".jpg")){
            loadImageFromContentUri(file_path,imageAnnonce_item);

        }else{
            loadImage(file_path,imageAnnonce_item);
        }

        nomAnnonce_item.setText(adModelArrayList.get(i).getNomAnnonce());
        prixAnnonce_item.setText(adModelArrayList.get(i).getPrixAnnonce().toString() + " €");
        anneeAnnonce_item.setText(String.valueOf(adModelArrayList.get(i).getAnneeAnnonce()));
        return convertView;
    }
}