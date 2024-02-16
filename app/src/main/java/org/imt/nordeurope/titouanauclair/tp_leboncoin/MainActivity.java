package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickList(View view){
        Intent lancementSecondeActivite = new Intent(MainActivity.this , AnnoncesListe.class );
        Log.d("Button click", "Clic sur list");
        startActivity (lancementSecondeActivite);
    }

    public void onClickAdd(View view){
        //Intent lancementSecondeActivite = new Intent(MainActivity.this , SecondActivity . class );
        Log.d("Button click", "Clic sur add");
        Intent lancementSecondeActivite = new Intent(MainActivity.this , VueAnnonce.class );
        startActivity (lancementSecondeActivite);
    }

}
