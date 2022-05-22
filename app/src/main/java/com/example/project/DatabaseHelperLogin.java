package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperLogin extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "Accounts.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_accounts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "username";
    public static final String COLUMN_PASS = "password";




    public DatabaseHelperLogin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USER + " TEXT, " +
                        COLUMN_PASS + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER, user);
        cv.put(COLUMN_PASS, password);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed to register.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Registered succesfully. You may now login.", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
