package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class AnnoncesListe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);
    }
    

    public List<AdModel> listedAnnonces = new ArrayList<>();
    
    void createList(){
        listedAnnonces.add(new AdModel(1, "drawable/1.-CITROEN-SM.jpg", 123.0, "XXX", 1987));
        listedAnnonces.add(new AdModel(2, "drawable/10.-CITROEN-CX.jpg", 123.0, "XXX", 1966));
        listedAnnonces.add(new AdModel(3, "drawable/11.-LAMBO-COUNTACH.jpg", 123.0, "XXX", 1978));
    }
    


}

