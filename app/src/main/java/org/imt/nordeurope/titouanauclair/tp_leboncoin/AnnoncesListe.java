package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.database.Cursor;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class AnnoncesListe extends AppCompatActivity {

    public ArrayList<AdModel> listedAnnonces = new ArrayList<>();
    public ArrayList<AdModel> listedAnnoncesFiltrees = new ArrayList<>();
    private SeekBar SeekBarAnnee;
    private SeekBar SeekBarPrix;
    int annee_max =2024;
    int prix_max = 10000000;
    TextView annee_max_visualisation;
    TextView prix_max_visualisation;
    RecyclerView recyclerView;
    private static RecyclerViewAdAdapter adAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Menu menu;
    private boolean isListView = true;
    private DBManager dbManager;



    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);

        recyclerView = findViewById(R.id.recyclerView);

        dbManager = DBManager.getDBManager(getApplicationContext());
        dbManager.open();
        loadFromDatabase();

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        adAdapter = new RecyclerViewAdAdapter(listedAnnoncesFiltrees, getApplicationContext());
        recyclerView.setAdapter(adAdapter);

        adAdapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AnnoncesListe.this,DetailsActivity.class);
                intent.putExtra("MODEL", listedAnnonces.get(position));
                startActivity(intent);
            }
        });

        filtrageListes();
        this.annee_max_visualisation = (TextView) findViewById(R.id.valueBarAnnee);
        this.prix_max_visualisation = (TextView) findViewById(R.id.valueBarPrix);
        this.SeekBarAnnee = (SeekBar) findViewById(R.id.seekBarAnnee);
        this.SeekBarPrix = (SeekBar) findViewById(R.id.seekBarPrix);
        this.SeekBarAnnee.setMax(findMaxYear(listedAnnonces));
        this.SeekBarPrix.setMax((int) findMaxPrice(listedAnnonces));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.SeekBarAnnee.setMin(findMinYear(listedAnnonces));
            this.SeekBarPrix.setMin((int) findMinPrice(listedAnnonces));
        }

        setUptSeekBar();

        this.SeekBarAnnee.setProgress(findMaxYear(listedAnnonces));
        this.SeekBarPrix.setProgress((int) findMaxPrice(listedAnnonces));
        this.annee_max_visualisation.setText("Année maximum de recherche : " + Integer.toString(findMaxYear(listedAnnonces)));
        this.prix_max_visualisation.setText("Prix maximum de recherche : " + String.format("%,.0f", findMaxPrice(listedAnnonces)) + "€");

        SeekBarAnnee.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                annee_max = progress;
                annee_max_visualisation.setText("Année maximum de recherche : " + String.valueOf(annee_max));
                listedAnnoncesFiltrees.clear();
                filtrageListes(); //
                adAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBarPrix.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prix_max = progress;
                prix_max_visualisation.setText("Prix maximum de recherche : " + String.format("%,.0f", new Double(prix_max)) + "€");
                listedAnnoncesFiltrees.clear();
                filtrageListes(); //
                adAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
    private void loadFromDatabase() {
        Cursor cursor = dbManager.fetch();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                //int id = cursor.getInt(cursor.getColumnIndex(DBHelper._ID));
                String title = cursor.getString(cursor.getColumnIndex(DBHelper.TITLE));
                double price = cursor.getDouble(cursor.getColumnIndex(DBHelper.PRICE));
                String image = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE));
                int year = cursor.getInt(cursor.getColumnIndex(DBHelper.ANNEE));
                String phone = cursor.getString(cursor.getColumnIndex(DBHelper.PHONE));
                String mail = cursor.getString(cursor.getColumnIndex(DBHelper.MAIL));
                String description = cursor.getString(cursor.getColumnIndex(DBHelper.DESCRIPTION));
                AdModel adModel = new AdModel(title, price, image, year, phone, mail, description);
                listedAnnonces.add(adModel);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.actionChangeDisplayMode:
                changeDisplayMode();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void filtrageListes(){
        listedAnnoncesFiltrees.clear();
        for(int i = 0; i< listedAnnonces.size(); i++){
            if(listedAnnonces.get(i).getAnneeAnnonce()<=annee_max && listedAnnonces.get(i).getPrixAnnonce()<=prix_max){
                listedAnnoncesFiltrees.add(listedAnnonces.get(i));
            }
        }
    }

    int findMinYear(ArrayList<AdModel> liste){
        int annee_min_trouvee = 2024;
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i).getAnneeAnnonce()<annee_min_trouvee){
                annee_min_trouvee = liste.get(i).getAnneeAnnonce();
            }
        }
        return annee_min_trouvee;
    }

    double findMinPrice(ArrayList<AdModel> liste){
        double prix_min_trouve = 100000000;
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i).getPrixAnnonce()<prix_min_trouve){
                prix_min_trouve = liste.get(i).getPrixAnnonce();
            }
        }
        return prix_min_trouve;
    }
    int findMaxYear(ArrayList<AdModel> liste){
        int annee_max_trouvee = 0;
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i).getAnneeAnnonce()>annee_max_trouvee){
                annee_max_trouvee = liste.get(i).getAnneeAnnonce();
            }
        }
        return annee_max_trouvee;
    }

    double findMaxPrice(ArrayList<AdModel> liste){
        double prix_max_trouve = 0;
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i).getPrixAnnonce()>prix_max_trouve){
                prix_max_trouve = liste.get(i).getPrixAnnonce();
            }
        }
        return prix_max_trouve;
    }

    private void changeDisplayMode() {
        if(isListView){
            adAdapter.setIsListView(false);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adAdapter);
            isListView = false;
            menu.getItem(0).setIcon(R.drawable.baseline_view_list_24);
        } else {
            adAdapter.setIsListView(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adAdapter);
            isListView = true;
            menu.getItem(0).setIcon(R.drawable.baseline_grid_view_24);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    void setUptSeekBar(){
        SeekBarAnnee.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                annee_max = progress;
                annee_max_visualisation.setText("Année maximum de recherche : " + String.valueOf(annee_max));
                listedAnnoncesFiltrees.clear();
                filtrageListes(); //
                adAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}


