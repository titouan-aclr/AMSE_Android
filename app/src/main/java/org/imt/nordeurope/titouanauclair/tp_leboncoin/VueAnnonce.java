package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VueAnnonce extends AppCompatActivity {

    public ImageView imageProduct;
    public TextView price;
    public TextView address;
    private Button sendButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_annonce);
        imageProduct  = (ImageView) findViewById(R.id.imageProduct);
        price = (TextView) findViewById(R.id.price);
        address = (TextView) findViewById(R.id.description);
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
                sendAdd(imageProduct,address,price);
            }
        });
    }
    private void sendAdd(ImageView imageProduct, TextView address, TextView price){

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
            imageProduct.setImageURI(selectedImageUri);
        }
    }

}