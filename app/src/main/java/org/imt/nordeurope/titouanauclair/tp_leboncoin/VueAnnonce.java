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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VueAnnonce extends AppCompatActivity {

    public ImageView imageProduct;
    public TextView price;
    public TextView nom;
    private Button sendButton;
    private String filePath;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_annonce);
        imageProduct  = (ImageView) findViewById(R.id.imageProduct);
        price = (TextView) findViewById(R.id.price);
        nom = (TextView) findViewById(R.id.annee);
        sendButton = (Button) findViewById(R.id.sendButton);

        imageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdModel nouvelleAnnonce = new AdModel(nom.getText().toString(), Double.parseDouble(price.getText().toString()), R.drawable.alfasud, 2024);

                DBManager dbManager = DBManager.getDBManager(getApplicationContext());

                dbManager.open();

                dbManager.insert(nouvelleAnnonce);

                dbManager.close();
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
            String imagePath = saveImageToExternalStorage(selectedImageUri);
            filePath = imagePath;
            imageProduct.setImageURI(selectedImageUri);
        }
    }
    private String saveImageToExternalStorage(Uri imageUri) {
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

            String imageName = "image_" + System.currentTimeMillis() + ".jpg"; // Nom de fichier unique
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "VotreDossier");
            if (!directory.exists()) {
                directory.mkdirs(); // Créer le dossier s'il n'existe pas
            }
            File file = new File(directory, imageName);

            inputStream = context.getContentResolver().openInputStream(imageUri);
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            imagePath = file.getAbsolutePath();
        } catch (IOException e) {
            Log.e("FileUtils", "Erreur lors de la sauvegarde de l'image sur le stockage externe", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.e("FileUtils", "Erreur lors de la fermeture des flux", e);
            }
        }
        return imagePath;
    }


}