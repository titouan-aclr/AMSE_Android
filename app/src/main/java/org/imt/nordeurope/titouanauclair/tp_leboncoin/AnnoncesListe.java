package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
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
        dbManager = DBManager.getDBManager(getApplicationContext());
        dbManager.open();
        loadFromDatabase();
        adapter = new AdAdapter(getApplicationContext(), listedAnnonces);

        listView.setAdapter(adapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                Intent intent = new Intent(AnnoncesListe.this,DetailsActivity.class);
                intent.putExtra("MODEL", listedAnnonces.get(i));
                startActivity(intent);
            }
        });
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
        }
        return super.onOptionsItemSelected(item);
    }
}

