package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission non accordée, demande à l'utilisateur la permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES},
                    PERMISSION_REQUEST_CODE);
        } else {
            // La permission est déjà accordée, vous pouvez maintenant accéder au stockage externe
            // et charger votre image
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Vérifiez si la permission a été accordée
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, vous pouvez maintenant accéder au stockage externe
                // et charger votre image
            } else {
                // La permission a été refusée, vous pouvez informer l'utilisateur ou
                // demander à nouveau la permission
            }
        }
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
