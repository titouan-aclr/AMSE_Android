package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AnnoncesListe extends AppCompatActivity {

    public ArrayList<AdModel> listedAnnonces = new ArrayList<>();
    RecyclerView recyclerView;
    private static RecyclerViewAdAdapter adAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private Menu menu;
    private boolean isListView = true;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_liste);

        recyclerView = findViewById(R.id.recyclerView);

        dbManager = DBManager.getDBManager(getApplicationContext());
        dbManager.open();
        loadFromDatabase();

        //createList();

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        adAdapter = new RecyclerViewAdAdapter(listedAnnonces, getApplicationContext());
        recyclerView.setAdapter(adAdapter);

        adAdapter.setOnItemClickListener(new AdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AnnoncesListe.this,DetailsActivity.class);
                intent.putExtra("MODEL", listedAnnonces.get(position));
                startActivity(intent);
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
}

