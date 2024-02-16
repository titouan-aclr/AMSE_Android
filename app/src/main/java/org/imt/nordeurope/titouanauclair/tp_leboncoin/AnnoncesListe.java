package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class AnnoncesListe extends AppCompatActivity {

    public ArrayList<AdModel> listedAnnonces = new ArrayList<>();
    ListView listView;
    private static AdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);
        listView = findViewById(R.id.listeVehicules);

        createList();

        adapter = new AdAdapter(getApplicationContext(), listedAnnonces);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                Intent intent = new Intent(AnnoncesListe.this,DetailsActivity.class);
                intent.putExtra("MODEL", listedAnnonces.get(i));
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    void createList(){
        listedAnnonces.add(new AdModel("Citroën SM", 49800.0, R.drawable.citroen_sm, 1970));
        listedAnnonces.add(new AdModel("Range Rover", 57500.0, R.drawable.range_rover, 1970));
        listedAnnonces.add(new AdModel("Autobianchi A112 Abarth", 14900.0, R.drawable.autobianchi_a112_abarth, 1971));
        listedAnnonces.add(new AdModel("Alpine A310", 38900.0, R.drawable.alpine_a310, 1971));
        listedAnnonces.add(new AdModel("Fiat 130 Coupé", 11900.0, R.drawable.fiat_130_coupe, 1971));
        listedAnnonces.add(new AdModel("Alfa Romeo Alfasud", 12900.0, R.drawable.alfasud, 1972));
        listedAnnonces.add(new AdModel("Renault 5", 9500.0, R.drawable.renault_5, 1972));
        listedAnnonces.add(new AdModel("Lancia Stratos", 373750.0, R.drawable.lancia_stratos, 1973));
        listedAnnonces.add(new AdModel("Matra Bagheera", 9000.0, R.drawable.matra_bagheera, 1973));
        listedAnnonces.add(new AdModel("Citroën CX", 16990.0, R.drawable.citroen_cx, 1974));
        listedAnnonces.add(new AdModel("Lamborghini Countach", 597823.0, R.drawable.lambo_countach, 1974));
        listedAnnonces.add(new AdModel("Volkswagen Golf", 23990.0, R.drawable.vw_golf, 1974));
        listedAnnonces.add(new AdModel("Jaguar XJS", 32000.0, R.drawable.jaguar_xjs, 1975));
        listedAnnonces.add(new AdModel("Ferrari 308 GTB", 160977.0, R.drawable.ferrari_308_gtb, 1975));
        listedAnnonces.add(new AdModel("Peugeot 604", 7500.0, R.drawable.peugeot_604, 1975));
        listedAnnonces.add(new AdModel("Renault 30", 4990.0, R.drawable.renault_30, 1975));
        listedAnnonces.add(new AdModel("Simca 1307", 16151.0, R.drawable.simca_1307, 1975));
        listedAnnonces.add(new AdModel("Triumph TR7", 7260.0, R.drawable.triumph_tr7, 1975));
        listedAnnonces.add(new AdModel("Porsche 924", 11900.0, R.drawable.porsche_924, 1976));
        listedAnnonces.add(new AdModel("Fiat 131 Abarth", 70273.0, R.drawable.fiat_131_abarth, 1976));
        listedAnnonces.add(new AdModel("Volvo 240", 6890.0, R.drawable.volvo_240, 1976));
        listedAnnonces.add(new AdModel("Audi 100 5C", 12000.0, R.drawable.audi_100, 1977));
        listedAnnonces.add(new AdModel("BMW 323i", 119990.0, R.drawable.bmw_323i, 1977));
        listedAnnonces.add(new AdModel("Porsche 928", 69900.0, R.drawable.porsche_928, 1977));
    }


    


}

