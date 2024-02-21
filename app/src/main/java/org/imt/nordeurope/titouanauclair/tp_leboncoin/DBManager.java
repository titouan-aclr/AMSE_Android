package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.prefs.Preferences;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    private static final String PREF_INITIALIZED = "initialized";


    private static final String TAG = "DBManager"; // TAG pour identifier les messages de cette classe dans la console

    private DBManager(Context c) {
        context = c;
        if (!isInitialized()) {
            init();
            setInitialized();
        }
    }

    private boolean isInitialized() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(PREF_INITIALIZED, false);
    }

    private void setInitialized() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_INITIALIZED, true);
        editor.apply();
    }
    private String saveDrawableToExternalStorage(Context context, int drawableResId) {
        OutputStream outputStream = null;
        String imagePath = null;
        try {
            String imageName = "image_" + System.currentTimeMillis() + ".jpg";
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "VotreDossier");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, imageName);

            outputStream = new FileOutputStream(file);

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableResId);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

            imagePath = file.getAbsolutePath();
        } catch (IOException e) {
            Log.e("FileUtils", "Erreur lors de la sauvegarde de l'image drawable sur le stockage externe", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Log.e("FileUtils", "Erreur lors de la fermeture du flux de sortie", e);
            }
        }
        return imagePath;
    }


    public static DBManager getDBManager(Context context) {
        if (DBManager == null){
            return new DBManager(context);
        }
        return DBManager;
    }
    public void resetDatabase() {
        database.execSQL("DELETE FROM " + DBHelper.TABLE_NAME);
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void init(){
        open();
        insert(new AdModel("Citroën SM", 49800.0, "citroen_cx", 1970, "0320335571", "test1@gmail.com", "À vendre : Citroën SM de 1970, une berline de luxe emblématique des années 70. Dotée d'un moteur V6 Maserati, cette voiture arbore un design distinctif et futuriste, avec sa suspension hydropneumatique révolutionnaire. Kilométrage : 85 000 km. Intérieur en cuir bien entretenu, peinture d'origine en bon état. Prix négociable dans la limite du raisonnable. Une opportunité rare pour les amateurs de voitures classiques."));
        insert(new AdModel("Range Rover", 57500.0, "range_rover", 1970, "À saisir : Range Rover Classic de 1970, un véhicule tout-terrain emblématique qui incarne le luxe et la robustesse. Ce modèle particulier est équipé du puissant moteur V8 de 3,5 litres, parfait pour affronter tous les terrains. Avec seulement 70 000 km au compteur, cet exemplaire bénéficie d'un entretien méticuleux et d'une restauration récente. Intérieur spacieux en cuir, carrosserie en excellent état. Prix compétitif et possibilité de discuter. Une occasion unique de posséder un morceau d'histoire automobile."));
        insert(new AdModel("Autobianchi A112 Abarth", 14900.0, "autobianchi_a112_abarth", 1971, "0320335573",null, "À vendre : Autobianchi A112 Abarth de 1971. Performante et agile, équipée d'un moteur 4 cylindres Abarth. En bon état général, avec environ 95 000 km au compteur. Intérieur préservé, carrosserie soignée. Prix négociable. Parfaite pour les amateurs de conduite sportive des années 70."));
        insert(new AdModel("Alpine A310", 38900.0, "alpine_a310", 1971, null,"test5@gmail.com","À vendre : Alpine A310 de 1971, une voiture de sport emblématique des années 70. Équipée d'un moteur V6 en position centrale, offrant des performances exceptionnelles. Ce modèle spécifique affiche 80 000 km au compteur et a été soigneusement entretenu. Intérieur en bon état, carrosserie avec quelques traces d'usure. Prix à discuter dans la limite du raisonnable. Une occasion unique de posséder un morceau de l'histoire automobile française."));
        insert(new AdModel("Fiat 130 Coupé", 11900.0, "fiat_130_coupe", 1971, "0320335575","test5@gmail.com", "À vendre : Fiat 130 Coupé de 1971, une élégante voiture de grand tourisme des années 70. Propulsée par un moteur V6, cette Fiat offre une conduite souple et confortable. Affichant un kilométrage de 90 000 km, elle a été méticuleusement entretenue. L'intérieur spacieux et luxueux est en excellent état, tout comme la carrosserie. Prix compétitif et possibilité de négociation. Une pièce rare pour les amateurs de voitures classiques italiennes."));
        insert(new AdModel("Alfa Romeo Alfasud", 12900.0, "alfasud", 1972,  "À vendre : Alfa Romeo Alfasud de 1972, une berline compacte dynamique et pleine de caractère. Dotée d'un moteur boxer à quatre cylindres, cette Alfasud offre une conduite agile et sportive. Avec seulement 65 000 km au compteur, elle présente un intérieur bien préservé et une carrosserie en bon état. Prix attractif et ouvert à la négociation. Une opportunité idéale pour les passionnés de voitures italiennes classiques."));
        insert(new AdModel("Renault 5", 9500.0, "renault_5", 1972, "0320335577","test7@gmail.com", "À vendre : Renault 5 de 1972, une icône des voitures compactes des années 70. Propulsée par un moteur économique et fiable, cette Renault 5 offre une conduite urbaine plaisante. Avec un kilométrage de 80 000 km, elle a été bien entretenue et bénéficie d'un intérieur propre. La carrosserie présente quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Parfaite pour les amateurs de voitures rétro et de style français."));
        insert(new AdModel("Lancia Stratos", 373750.0, "lancia_stratos", 1973, "0320335578","test8@gmail.com", "À vendre : Lancia Stratos de 1973, une légende de la compétition automobile des années 70. Propulsée par un moteur V6 Ferrari, cette voiture de rallye emblématique offre des performances époustouflantes. Avec seulement 50 000 km au compteur, elle est en excellent état, aussi bien à l'intérieur qu'à l'extérieur. Prix sur demande pour les collectionneurs sérieux. Une occasion rare de posséder un morceau d'histoire de la course automobile."));
        insert(new AdModel("Matra Bagheera", 9000.0, "matra_bagheera", 1973, "0320335579","test9@gmail.com", "À vendre : Matra Bagheera de 1973, une voiture de sport unique des années 70. Dotée d'une configuration à trois places en rangée, cette Matra offre une expérience de conduite distinctive. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur soigné. La carrosserie est en bon état, avec quelques marques d'usure normales pour son âge. Prix compétitif et ouvert à la négociation. Une opportunité idéale pour les amateurs de voitures françaises originales."));
        insert(new AdModel("Citroën CX", 16990.0, "citroen_cx", 1974, "0320335580","test10@gmail.com", "À vendre : Citroën CX de 1974, une berline élégante et innovante des années 70. Propulsée par le célèbre système hydropneumatique de Citroën, cette CX offre un confort de conduite exceptionnel. Avec un kilométrage de 90 000 km, elle a été méticuleusement entretenue et présente un intérieur spacieux en bon état. La carrosserie arbore une peinture d'origine bien préservée. Prix attractif et possibilité de négociation. Une occasion rare de posséder une voiture française classique et confortable."));
        insert(new AdModel("Lamborghini Countach", 597823.0, "lambo_countach", 1974, "0320335581","test11@gmail.com", "À vendre : Lamborghini Countach de 1974, une supercar emblématique des années 70. Propulsée par un moteur V12 atmosphérique, cette Countach offre des performances stupéfiantes. Avec seulement 25 000 km au compteur, elle est en excellent état, tant à l'intérieur qu'à l'extérieur. L'intérieur est luxueux avec des finitions en cuir de haute qualité. Prix sur demande pour les collectionneurs sérieux. Une opportunité unique de posséder un morceau de l'histoire de l'automobile italienne."));
        insert(new AdModel("Volkswagen Golf", 23990.0, "vw_golf", 1974, "0320335582","test12@gmail.com", "À vendre : Volkswagen Golf de 1974, une voiture compacte iconique des années 70. Propulsée par un moteur robuste et économique, cette Golf offre une conduite agréable et fiable. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur propre et confortable. La carrosserie est en bon état, avec quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Idéale pour les amateurs de voitures classiques et pratiques."));
        insert(new AdModel("Jaguar XJS", 32000.0, "jaguar_xjs", 1975, "0320335583","test13@gmail.com", "À vendre : Jaguar XJS de 1975, une voiture de grand tourisme élégante et puissante des années 70. Propulsée par un moteur 12 cylindres, cette XJS offre une conduite luxueuse et raffinée. Avec un kilométrage de 60 000 km, elle a été bien entretenue et présente un intérieur spacieux en cuir de haute qualité. La carrosserie est en bon état, avec une peinture brillante d'origine. Prix compétitif et possibilité de négociation. Une occasion idéale pour les amateurs de voitures classiques britanniques."));
        insert(new AdModel("Ferrari 308 GTB", 160977.0, "ferrari_308_gtb", 1975, "0320335584","test14@gmail.com", "À vendre : Ferrari 308 GTB de 1975, une véritable icône des voitures de sport des années 70. Dotée d'un moteur V8 en position centrale, cette Ferrari offre des performances exceptionnelles et un son envoûtant. Avec un kilométrage de 40 000 km, elle est en excellent état, tant à l'intérieur qu'à l'extérieur. L'intérieur luxueux est agrémenté de finitions en cuir de haute qualité. Prix sur demande pour les collectionneurs sérieux. Une occasion rare de posséder un morceau de l'histoire de l'automobile italienne."));
        insert(new AdModel("Peugeot 604", 7500.0, "peugeot_604", 1975, "0320335585","test15@gmail.com", "À vendre : Peugeot 604 de 1975, une berline haut de gamme française des années 70. Propulsée par un moteur robuste et fiable, cette Peugeot offre un confort de conduite exceptionnel. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur spacieux et luxueux en velours. La carrosserie est en bon état, avec une peinture d'origine bien conservée. Prix attractif et possibilité de négociation. Une voiture de collection idéale pour les amateurs de voitures françaises classiques."));
        insert(new AdModel("Renault 30", 4990.0, "renault_30", 1975, "0320335586","test16@gmail.com", "À vendre : Renault 30 de 1975, une berline élégante et innovante des années 70. Propulsée par un moteur V6, cette Renault offre une conduite confortable et silencieuse. Avec un kilométrage de 80 000 km, elle a été méticuleusement entretenue et présente un intérieur spacieux en tissu en excellent état. La carrosserie est bien préservée, avec une peinture d'origine brillante. Prix abordable et possibilité de négociation. Une opportunité idéale pour les amateurs de voitures françaises rétro."));
        insert(new AdModel("Simca 1307", 16151.0, "simca_1307", 1975, "0320335587","test17@gmail.com", "À vendre : Simca 1307 de 1975, une voiture familiale polyvalente des années 70. Propulsée par un moteur robuste et économique, cette Simca offre une conduite confortable et fiable. Avec un kilométrage de 90 000 km, elle a été bien entretenue et présente un intérieur spacieux et fonctionnel en tissu. La carrosserie est en bon état, avec quelques signes d'usure normale pour son âge. Prix abordable et possibilité de négociation. Idéale pour les amateurs de voitures classiques françaises."));
        insert(new AdModel("Triumph TR7", 7260.0, "triumph_tr7", 1975, "0320335588","test18@gmail.com", "À vendre : Triumph TR7 de 1975, une voiture de sport emblématique des années 70. Dotée d'un design distinctif et de lignes épurées, cette TR7 offre une conduite dynamique et plaisante. Avec un kilométrage de 70 000 km, elle a été bien entretenue et présente un intérieur sportif en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une occasion idéale pour les passionnés de voitures classiques britanniques."));
        insert(new AdModel("Porsche 924", 11900.0, "porsche_924", 1976, "0320335589","test19@gmail.com", "À vendre : Porsche 924 de 1976, une voiture de sport emblématique des années 70. Propulsée par un moteur quatre cylindres en ligne, cette Porsche offre une conduite agile et sportive. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur spacieux en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une opportunité idéale pour les amateurs de voitures classiques allemandes."));
        insert(new AdModel("Fiat 131 Abarth", 70273.0, "fiat_131_abarth", 1976, "0320335590","test20@gmail.com", "À vendre : Fiat 131 Abarth de 1976, une légende du rallye des années 70. Propulsée par un moteur quatre cylindres préparé par Abarth, cette Fiat offre des performances exceptionnelles. Avec un kilométrage de 70 000 km, elle a été méticuleusement entretenue et présente un intérieur sportif en bon état. La carrosserie arbore une livrée emblématique de course, avec des détails authentiques. Prix compétitif pour les connaisseurs passionnés. Une opportunité rare de posséder une pièce de l'histoire du sport automobile italien."));
        insert(new AdModel("Volvo 240", 6890.0, "volvo_240", 1976, "0320335591","test21@gmail.com", "À vendre : Volvo 240 de 1976, une berline robuste et fiable des années 70. Propulsée par un moteur quatre cylindres réputé pour sa longévité, cette Volvo offre une conduite confortable et sécuritaire. Avec un kilométrage de 90 000 km, elle a été bien entretenue et présente un intérieur spacieux en tissu en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix abordable et possibilité de négociation. Parfaite pour les amateurs de voitures classiques suédoises."));
        insert(new AdModel("Audi 100 5C", 12000.0, "audi_100", 1977, "0320335592","test22@gmail.com", "À vendre : Audi 100 5C de 1977, une berline élégante et confortable des années 70. Propulsée par un moteur robuste et fiable, cette Audi offre une conduite agréable et sécuritaire. Avec un kilométrage de 80 000 km, elle a été bien entretenue et présente un intérieur spacieux en tissu en bon état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une voiture de collection idéale pour les amateurs de voitures allemandes classiques."));
        insert(new AdModel("BMW 323i", 119990.0, "bmw_323i", 1977, "0320335593","test23@gmail.com", "À vendre : BMW 323i de 1977, une berline sportive et raffinée des années 70. Propulsée par un moteur six cylindres en ligne, cette BMW offre des performances exceptionnelles et une conduite dynamique. Avec un kilométrage de 70 000 km, elle a été méticuleusement entretenue et présente un intérieur luxueux en cuir en excellent état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif et possibilité de négociation. Une occasion idéale pour les passionnés de voitures allemandes classiques."));
        insert(new AdModel("Porsche 928", 69900.0, "porsche_928", 1977, "0320335594","test24@gmail.com", "À vendre : Porsche 928 de 1977, une GT de luxe emblématique des années 70. Propulsée par un moteur V8, cette Porsche offre des performances exceptionnelles et un confort de conduite supérieur. Avec un kilométrage de 80 000 km, elle a été méticuleusement entretenue et présente un intérieur luxueux en cuir en excellent état. La carrosserie est également en bon état, avec une peinture d'origine bien conservée. Prix compétitif pour les collectionneurs sérieux. Une opportunité rare de posséder une pièce de l'histoire de Porsche."));
    }

    public void insert(AdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper._ID, ad.getIdAnnonce());
        contentValue.put(DBHelper.TITLE, ad.getNomAnnonce());
        contentValue.put(DBHelper.PRICE, ad.getPrixAnnonce());
        contentValue.put(DBHelper.IMAGE, ad.getImageAnnonce());
        contentValue.put(DBHelper.ANNEE, ad.getAnneeAnnonce());
        contentValue.put(DBHelper.PHONE, ad.getPhoneNumber());
        contentValue.put(DBHelper.MAIL, ad.getEmail());
        contentValue.put(DBHelper.DESCRIPTION, ad.getDescription());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.PRICE, DBHelper.IMAGE, DBHelper.ANNEE, DBHelper.PHONE, DBHelper.MAIL, DBHelper.DESCRIPTION};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public int update(long _id, AdModel ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getNomAnnonce());
        contentValues.put(DBHelper.PRICE, ad.getPrixAnnonce());
        contentValues.put(DBHelper.IMAGE, ad.getImageAnnonce());
        contentValues.put(DBHelper.ANNEE, ad.getImageAnnonce());
        contentValues.put(DBHelper.PHONE, ad.getImageAnnonce());
        contentValues.put(DBHelper.MAIL, ad.getImageAnnonce());
        contentValues.put(DBHelper.DESCRIPTION, ad.getImageAnnonce());
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }

}
