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
        Intent lauchListActivity = new Intent(MainActivity.this , AnnoncesListe.class );
        startActivity (lauchListActivity);
    }

    public void onClickAdd(View view){
        Intent LauchAddActivity = new Intent(MainActivity.this , VueAnnonce.class );
        startActivity (LauchAddActivity);
    }

}
