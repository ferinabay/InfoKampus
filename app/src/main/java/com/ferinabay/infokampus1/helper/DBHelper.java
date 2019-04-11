package com.ferinabay.infokampus1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ferinabay.infokampus1.model.Kampus;

import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private static final String DB_NAME = "infokampus";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "password";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    private static final String TABLE_KAMPUS = "kampus";
    private static final String COLUMN_KAMPUS_ID = "idkampus";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ALAMAT = "alamat";
    private static final String COLUMN_FAKULTAS = "jurusan";
    private static final String COLUMN_AKRE = "akreditasi";
    private static final String COLUMN_ABOUT = "about";
    private static final String CREATE_KAMPUS_TABLE = "CREATE TABLE " + TABLE_KAMPUS + " (\n" +
            "    " + COLUMN_KAMPUS_ID + " INTEGER NOT NULL CONSTRAINT kampus_pk PRIMARY KEY AUTOINCREMENT,\n" +
            "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_ALAMAT + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_FAKULTAS + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_AKRE + " varchar(10) NOT NULL,\n" +
            "    " + COLUMN_ABOUT + " varchar(200) NOT NULL\n" +
            ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_KAMPUS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KAMPUS);
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public void saveKampus(Kampus kampus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COLUMN_NAME, kampus.getName());
        values.put(COLUMN_ALAMAT, kampus.getAlamat());
        values.put(COLUMN_FAKULTAS, kampus.getFakultas());
        values.put(COLUMN_AKRE, kampus.getAkreditasi());
        values.put(COLUMN_ABOUT, kampus.getAbout());
        
        db.insert(TABLE_KAMPUS, null, values);
        db.close();
    }
    
    public boolean getUser(String email, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public List<Kampus> kampuslist(String filter) {
        String query;
        if (filter.equals("")) {
            query = "SELECT*FROM "+ TABLE_KAMPUS;
        } else {
            query = "SELECT*FROM "+ TABLE_KAMPUS +" ORDER BY "+ filter;
        }

        List<Kampus> kampusLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Kampus kampus;

        if (cursor.moveToFirst()) {
            do {
                kampus = new Kampus();

                kampus.setIdkampus(cursor.getInt(cursor.getColumnIndex(COLUMN_KAMPUS_ID)));
                kampus.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                kampus.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_ALAMAT)));
                kampus.setFakultas(cursor.getString(cursor.getColumnIndex(COLUMN_FAKULTAS)));
                kampus.setAkreditasi(cursor.getString(cursor.getColumnIndex(COLUMN_AKRE)));
                kampus.setAbout(cursor.getString(cursor.getColumnIndex(COLUMN_ABOUT)));

                kampusLinkedList.add(kampus);
            } while (cursor.moveToNext());
        }

        return kampusLinkedList;
    }

    public Kampus getKampus(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT*FROM  "+TABLE_KAMPUS+" WHERE idkampus = "+id;
        Cursor cursor = db.rawQuery(query, null);

        Kampus receivedKampus = new Kampus();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedKampus.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            receivedKampus.setAlamat(cursor.getString(cursor.getColumnIndex(COLUMN_ALAMAT)));
            receivedKampus.setFakultas(cursor.getString(cursor.getColumnIndex(COLUMN_FAKULTAS)));
            receivedKampus.setAkreditasi(cursor.getString(cursor.getColumnIndex(COLUMN_AKRE)));
            receivedKampus.setAbout(cursor.getString(cursor.getColumnIndex(COLUMN_ABOUT)));
        }

        return receivedKampus;
    }

    public void deleteKampus(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_KAMPUS, "idkampus = ?", new String[]{String.valueOf(id)});
        Toast.makeText(context, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
    }

    public void updateKampus(long id, Context context, Kampus update) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COLUMN_NAME, update.getName());
        value.put(COLUMN_ALAMAT, update.getAlamat());
        value.put(COLUMN_FAKULTAS, update.getFakultas());
        value.put(COLUMN_AKRE, update.getAkreditasi());
        value.put(COLUMN_ABOUT, update.getAbout());

        db.update(TABLE_KAMPUS, value, "idkampus = ?", new String[]{String.valueOf(id)});

        Toast.makeText(context, "Berhasil di update!", Toast.LENGTH_SHORT).show();
    }
}
