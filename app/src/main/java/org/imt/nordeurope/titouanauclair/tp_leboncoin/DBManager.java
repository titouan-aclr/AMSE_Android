package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class DBManager {

    public static DBManager DBManager;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    private DBManager(Context c) {
        context = c;
        //init(); // Useful for adding ads for the first time.
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

    // Add ads manually.
    public void init(){
        open();
        insert(new AdModel("Citroën SM", 49800.0, R.drawable.citroen_sm, 1970));
        insert(new AdModel("Range Rover", 57500.0, R.drawable.range_rover, 1970));
        insert(new AdModel("Autobianchi A112 Abarth", 14900.0, R.drawable.autobianchi_a112_abarth, 1971));
        insert(new AdModel("Alpine A310", 38900.0, R.drawable.alpine_a310, 1971));
        insert(new AdModel("Fiat 130 Coupé", 11900.0, R.drawable.fiat_130_coupe, 1971));
        insert(new AdModel("Alfa Romeo Alfasud", 12900.0, R.drawable.alfasud, 1972));
        insert(new AdModel("Renault 5", 9500.0, R.drawable.renault_5, 1972));
        insert(new AdModel("Lancia Stratos", 373750.0, R.drawable.lancia_stratos, 1973));
        insert(new AdModel("Matra Bagheera", 9000.0, R.drawable.matra_bagheera, 1973));
        insert(new AdModel("Citroën CX", 16990.0, R.drawable.citroen_cx, 1974));
        insert(new AdModel("Lamborghini Countach", 597823.0, R.drawable.lambo_countach, 1974));
        insert(new AdModel("Volkswagen Golf", 23990.0, R.drawable.vw_golf, 1974));
        insert(new AdModel("Jaguar XJS", 32000.0, R.drawable.jaguar_xjs, 1975));
        insert(new AdModel("Ferrari 308 GTB", 160977.0, R.drawable.ferrari_308_gtb, 1975));
        insert(new AdModel("Peugeot 604", 7500.0, R.drawable.peugeot_604, 1975));
        insert(new AdModel("Renault 30", 4990.0, R.drawable.renault_30, 1975));
        insert(new AdModel("Simca 1307", 16151.0, R.drawable.simca_1307, 1975));
        insert(new AdModel("Triumph TR7", 7260.0, R.drawable.triumph_tr7, 1975));
        insert(new AdModel("Porsche 924", 11900.0, R.drawable.porsche_924, 1976));
        insert(new AdModel("Fiat 131 Abarth", 70273.0, R.drawable.fiat_131_abarth, 1976));
        insert(new AdModel("Volvo 240", 6890.0, R.drawable.volvo_240, 1976));
        insert(new AdModel("Audi 100 5C", 12000.0, R.drawable.audi_100, 1977));
        insert(new AdModel("BMW 323i", 119990.0, R.drawable.bmw_323i, 1977));
        insert(new AdModel("Porsche 928", 69900.0, R.drawable.porsche_928, 1977));
    }

    public void insert(AdModel ad) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper._ID, ad.getIdAnnonce());
        contentValue.put(DBHelper.TITLE, ad.getNomAnnonce());
        contentValue.put(DBHelper.PRICE, ad.getPrixAnnonce());
        contentValue.put(DBHelper.IMAGE, ad.getImageAnnonce());
        contentValue.put(DBHelper.ANNEE, ad.getAnneeAnnonce());
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper._ID, DBHelper.TITLE, DBHelper.PRICE, DBHelper.IMAGE, DBHelper.ANNEE};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }

    public int update(long _id, AdModel ad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TITLE, ad.getNomAnnonce());
        contentValues.put(DBHelper.PRICE, ad.getPrixAnnonce());
        contentValues.put(DBHelper.IMAGE, ad.getImageAnnonce());
        contentValues.put(DBHelper.ANNEE, ad.getImageAnnonce());
        int i = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }

}
