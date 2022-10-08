package com.andrewsapp.accstore2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static int DATABASE_VERSION=1;
    public static String DATABASE_NAME="database";
    public static String TABLE_NAME="accounts";

    public static final String  COLUMN_ID = "id";
    public static final String  COLUMN_TITLE = "title";
    public static final String  COLUMN_ACCOUNT = "account";
    public static final String  COLUMN_USERNAME = "username";
    public static final String  COLUMN_PASSWORD = "password";
    public static final String  COLUMN_WEBSITE = "website";
    public static final String  COLUMN_ADITIONAL_INFO = "aditionalinfo";
    public static final String  COLUMN_DATE = "date";
    public static final String  COLUMN_ICON = "icon";
    public static final String  COLUMN_COLOR_LIGHT = "colorlight";
    public static final String  COLUMN_COLOR_DARK = "colordark";

    public static final String  CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("
            +COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TITLE+ " TEXT NOT NULL,"
            +COLUMN_ACCOUNT+ " TEXT NOT NULL,"
            +COLUMN_USERNAME+ " TEXT NOT NULL,"
            +COLUMN_PASSWORD+ " TEXT NOT NULL,"
            +COLUMN_WEBSITE+ " TEXT NOT NULL,"
            +COLUMN_ADITIONAL_INFO+ " TEXT NOT NULL,"
            +COLUMN_DATE+ " TEXT NOT NULL,"
            +COLUMN_ICON+ " BLOB,"
            +COLUMN_COLOR_LIGHT+ " TEXT NOT NULL,"
            +COLUMN_COLOR_DARK+ " TEXT NOT NULL);";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create new table
        onCreate(sqLiteDatabase);
    }

    public void delete_all_records(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM accounts");
        db.close();
    }

    public long insertRow(RowDB row){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COLUMN_TITLE,row.getTitle());
        contentValues.put(COLUMN_ACCOUNT,row.getAccount());
        contentValues.put(COLUMN_USERNAME,row.getUsername());
        contentValues.put(COLUMN_PASSWORD,row.getPass());
        contentValues.put(COLUMN_WEBSITE,row.getWebsite());
        contentValues.put(COLUMN_ADITIONAL_INFO,row.getAditionalinfo());
        contentValues.put(COLUMN_DATE,row.getDate());
        contentValues.put(COLUMN_ICON,row.getIcon());
        contentValues.put(COLUMN_COLOR_LIGHT,row.getColor_light());
        contentValues.put(COLUMN_COLOR_DARK,row.getColor_dark());


        long result=db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return result;

    }
    public long rowCount(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        @SuppressLint("Recycle") long recordCount=db.rawQuery(query,null).getCount();
        db.close();
        return recordCount;
    }
    public RowDB getRow(long id){
        RowDB row= new RowDB();
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=db.query(TABLE_NAME,new String[]{COLUMN_ID,COLUMN_TITLE,COLUMN_ACCOUNT,COLUMN_USERNAME,
        COLUMN_PASSWORD,COLUMN_WEBSITE,COLUMN_ADITIONAL_INFO,COLUMN_DATE,COLUMN_ICON,COLUMN_COLOR_LIGHT,COLUMN_COLOR_DARK}
        ,COLUMN_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null) {

            cursor.moveToFirst();
            row.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            row.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            row.setAccount(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)));
            row.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            row.setPass(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
            row.setWebsite(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)));
            row.setAditionalinfo(cursor.getString(cursor.getColumnIndex(COLUMN_ADITIONAL_INFO)));
            row.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            row.setIcon(cursor.getBlob(cursor.getColumnIndex(COLUMN_ICON)));
            row.setColor_light(cursor.getString(cursor.getColumnIndex(COLUMN_COLOR_LIGHT)));
            row.setColor_dark(cursor.getString(cursor.getColumnIndex(COLUMN_COLOR_DARK)));
        }

        db.close();
        return row;
    }
    public ArrayList<RowDB> getAllRows() {
        ArrayList<RowDB> rows = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RowDB row = new RowDB();

                row.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                row.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                row.setAccount(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)));
                row.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                row.setPass(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                row.setWebsite(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)));
                row.setAditionalinfo(cursor.getString(cursor.getColumnIndex(COLUMN_ADITIONAL_INFO)));
                row.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                row.setIcon(cursor.getBlob(cursor.getColumnIndex(COLUMN_ICON)));
                row.setColor_light(cursor.getString(cursor.getColumnIndex(COLUMN_COLOR_LIGHT)));
                row.setColor_dark(cursor.getString(cursor.getColumnIndex(COLUMN_COLOR_DARK)));


                rows.add(row);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return rows;
    }
    public long  updateRow(RowDB row){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TITLE,row.getTitle());
        contentValues.put(COLUMN_ACCOUNT,row.getAccount());
        contentValues.put(COLUMN_USERNAME,row.getUsername());
        contentValues.put(COLUMN_PASSWORD,row.getPass());
        contentValues.put(COLUMN_WEBSITE,row.getWebsite());
        contentValues.put(COLUMN_ADITIONAL_INFO,row.getAditionalinfo());
        contentValues.put(COLUMN_DATE,row.getDate());
        contentValues.put(COLUMN_ICON,row.getIcon());
        contentValues.put(COLUMN_COLOR_LIGHT,row.getColor_light());
        contentValues.put(COLUMN_COLOR_DARK,row.getColor_dark());

        return db.update(TABLE_NAME, contentValues,COLUMN_ID + " = ?",
                new String[]{String.valueOf(row.getId())});

    }
    public void deleteRow(RowDB row){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(row.getId())});
        db.close();

    }


}
