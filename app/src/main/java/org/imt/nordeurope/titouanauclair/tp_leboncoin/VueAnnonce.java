package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class VueAnnonce extends AppCompatActivity {

    public ImageView imageProduct;
    public TextView price;
    public TextView nom;
    public Spinner annee;
    public TextView description;
    private Button sendButton;
    private TextView phone;
    private  TextView mail;
    private String filePath;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_annonce);
        imageProduct  = (ImageView) findViewById(R.id.imageProduct);
        price = (TextView) findViewById(R.id.price);
        nom = (TextView) findViewById(R.id.marque);
        annee = (Spinner) findViewById(R.id.annee);
        description = (TextView) findViewById(R.id.description);
        phone = (TextView) findViewById(R.id.phone);
        mail = (TextView) findViewById(R.id.mail);

        sendButton = (Button) findViewById(R.id.sendButton);

        List<String> yearsList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 1950; year <= currentYear; year++) {
            yearsList.add(String.valueOf(year));
        }
        Collections.reverse(yearsList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        annee.setAdapter(adapter);

        imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdModel nouvelleAnnonce;

                if ((phone.getText() != "") && (mail.getText() != "")) {
                    nouvelleAnnonce = new AdModel(nom.getText().toString(), Double.parseDouble(price.getText().toString()), filePath, Integer.parseInt(annee.getSelectedItem().toString()), phone.getText().toString(), mail.getText().toString(), description.getText().toString());
                } else {
                    nouvelleAnnonce = new AdModel(nom.getText().toString(), Double.parseDouble(price.getText().toString()), filePath, Integer.parseInt(annee.getSelectedItem().toString()), description.getText().toString());
                }

                DBManager dbManager = DBManager.getDBManager(getApplicationContext());

                dbManager.open();

                dbManager.insert(nouvelleAnnonce);

                dbManager.close();

                Intent intent = new Intent(VueAnnonce.this,AnnoncesListe.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
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
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            filePath = saveImageToCache(selectedImageUri);
            imageProduct.setImageURI(selectedImageUri);
        }
    }
    private String saveImageToCache(Uri imageUri) {
        if (imageUri == null) {
            return null;
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        String imagePath = null;
        try {
            Context context = getApplicationContext();
            if (context == null) {
                return null;
            }

            // Créez un fichier temporaire dans le répertoire de cache de l'application
            File cacheDir = context.getCacheDir();
            String imageName = "image_" + System.currentTimeMillis() + ".jpg";
            File imageFile = new File(cacheDir, imageName);

            // Copiez l'image depuis son emplacement actuel vers le fichier temporaire
            inputStream = context.getContentResolver().openInputStream(imageUri);
            outputStream = new FileOutputStream(imageFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            imagePath = imageFile.getAbsolutePath();
        } catch (IOException e) {
            Log.e("VueAnnonce", "Erreur lors de l'enregistrement de l'image dans le cache.", e);
        } finally {
            // Fermez les flux
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.e("VueAnnonce", "Erreur lors de la fermeture des flux.", e);
            }
        }
        return imagePath;
    }

}