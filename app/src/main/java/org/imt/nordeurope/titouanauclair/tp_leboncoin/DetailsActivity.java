package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        AdModel model = (AdModel) getIntent().getSerializableExtra("MODEL");

        ImageView image = findViewById(R.id.imageAnnonce_details);
        TextView titre = findViewById(R.id.nomAnnonce_details);
        TextView annee = findViewById(R.id.anneeAnnonce_details);
        TextView prix = findViewById(R.id.prixAnnonce_details);

        image.setImageResource(model.getImageAnnonce());
        titre.setText(model.getNomAnnonce());
        annee.setText("Année : " + model.getAnneeAnnonce());
        prix.setText(model.getPrixAnnonce().toString()+ " €");
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