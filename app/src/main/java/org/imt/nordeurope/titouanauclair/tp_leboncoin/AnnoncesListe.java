package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
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
    }
    
    void createList(){
        listedAnnonces.add(new AdModel(1, "Citroen", 18500.0, R.drawable.a, 1978));
        listedAnnonces.add(new AdModel(2, "Peugeot", 18500.0, R.drawable.a, 1978));
        listedAnnonces.add(new AdModel(3, "Renault", 18500.0, R.drawable.a, 1978));

    }


    


}

