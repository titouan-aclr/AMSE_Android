package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent lancementSecondeActivite = new Intent(MainActivity.this , VueAnnonce.class );
        startActivity (lancementSecondeActivite);
    }

}