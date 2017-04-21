package com.cs60333.jwang28.lab2_jwang28;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivy Wang on 4/8/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public SQLiteDatabase db;
    public static String DATABASE_NAME = "teams.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_TEAM = "Team";
    public static String COL_NAME = "team_name";
    public static String COL_DATE = "game_date";
    public static String COL_PLACE = "game_place";
    public static String COL_LOGO = "team_logo";
    public static String COL_SCORE = "game_score";
    public static String COL_LTL = "left_team_logo";
    public static String COL_RTL = "right_team_logo";
    public static String COL_LT = "left_Team";
    public static String COL_LN = "left_nick";
    public static String COL_LS = "left_score";
    public static String COL_RT = "righ_team";
    public static String COL_RN = "right_nick";
    public static String COL_RS = "right_score";
    public static String COL_ID = "_id";
    public static String TABLE_IMAGES = "Team_Images";
    public static String COL_IMAGE_ID = "_id";
    public static String COL_IMAGE = "image";
    public static String COL_TEAM_ID = "team_id";
    public static String COL_URI = "uri";
    public static String COL_IMG_DATE = "date";
    public DBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TEAM + "( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_LOGO + " TEXT, " + COL_NAME + " TEXT, " + COL_DATE + " TEXT, " + COL_PLACE + " TEXT, " + COL_LT + " TEXT, " + COL_LN + " TEXT, " + COL_LS + " TEXT, " + COL_SCORE + " TEXT, " + COL_RT + " TEXT, " + COL_RN + " TEXT, " + COL_RS + " TEXT, " + COL_LTL + " TEXT, " + COL_RTL + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " ( " + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TEAM_ID + " INTEGER, " +  COL_IMAGE + " BLOB, " +
                COL_IMG_DATE + " TEXT, " + COL_URI + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + TABLE_TEAM);
        db.execSQL("DROP TABLE if exists " + TABLE_IMAGES);
        onCreate(db);
    }

    public void insertData(String tblname,ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_ID, team.getTeamName());
//        contentValues.put(COL_ID, team.getDate());
//        contentValues.put(COL_ID, team.getPlace());
        long ret = db.insert(tblname, null, contentValues);

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Unsuccessful insert");
        }


        db.close();
    }

    public void deleteData(String tblname, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblname, clause, new String[]{Integer.toString(_id)});
        db.close();
    }

    public Cursor getAllEntries(String tblname, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblName, String[] columns, String where, String[] args, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, where, args, null, null, orderBy);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblname, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

}
