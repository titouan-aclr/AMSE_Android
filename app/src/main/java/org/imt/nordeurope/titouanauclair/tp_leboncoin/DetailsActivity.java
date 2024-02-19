package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private AdModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        model = (AdModel) getIntent().getSerializableExtra("MODEL");

        initData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        ImageView image = findViewById(R.id.imageAnnonce_details);
        TextView titre = findViewById(R.id.nomAnnonce_details);
        TextView annee = findViewById(R.id.anneeAnnonce_details);
        TextView prix = findViewById(R.id.prixAnnonce_details);
        TextView description = findViewById(R.id.descAnnonce_details);

        image.setImageResource(model.getImageAnnonce());
        titre.setText(model.getNomAnnonce());
        annee.setText("Année : " + model.getAnneeAnnonce());
        prix.setText(model.getPrixAnnonce().toString()+ " €");
        description.setText(model.getDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            description.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
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

    public void onClickPhone(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + model.getPhoneNumber()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClickEmailClick(android.view.View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+model.getEmail()));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name) + " : "+model.getNomAnnonce());
        intent.putExtra(Intent.EXTRA_TEXT, "Bonjour,\n\n je vous contacte par rapport à votre annonce " + model.getNomAnnonce() + " sur l'application "+ getString(R.string.app_name) + ". \nEst-elle toujours disponible ?\n\n Merci d'avance.");
        startActivity(intent);
    }
}