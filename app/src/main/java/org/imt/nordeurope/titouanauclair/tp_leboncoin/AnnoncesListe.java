package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AnnoncesListe extends AppCompatActivity {

    public ArrayList<AdModel> listedAnnonces = new ArrayList<>();
    ListView listView;
    private static AdAdapter adapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);
        listView = findViewById(R.id.listeVehicules);

        adapter = new AdAdapter(getApplicationContext(), listedAnnonces);

        listView.setAdapter(adapter);

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
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper._ID));
                String title = cursor.getString(cursor.getColumnIndex(DBHelper.TITLE));
                double price = cursor.getDouble(cursor.getColumnIndex(DBHelper.PRICE));
                int image = cursor.getInt(cursor.getColumnIndex(DBHelper.IMAGE));
                int year = cursor.getInt(cursor.getColumnIndex(DBHelper.ANNEE));
                AdModel adModel = new AdModel(id, title, price, image, year);
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
        }
        return super.onOptionsItemSelected(item);
    }


}

