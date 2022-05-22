package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperCondition extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Condition.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME =  "conditions";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER = "user";
    private static final String COLUMN_CONDITION = "condtion";
    private static final String COLUMN_TREATMENT = "treatment";

    public DatabaseHelperCondition(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER + " TEXT, "+
                COLUMN_CONDITION + " TEXT, "+
                COLUMN_TREATMENT + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    void addCondition(String user, String condition, String treatment){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER, user);
        cv.put(COLUMN_CONDITION, condition);
        cv.put(COLUMN_TREATMENT, treatment);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Succes", Toast.LENGTH_SHORT).show();
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

    Cursor readAllDataFrom(String unique_user){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COLUMN_USER + " = '" + unique_user + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
