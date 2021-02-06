package com.example.sportpedia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sportpedia.model.Team;

import java.util.ArrayList;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "sportpedia";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_T = "teams";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGO = "logo";
    private static final String KEY_NAME = "teams";
    private static final String KEY_ALT = "alt";
    private static final String KEY_FORMED = "formed";

    private static final String CREATE_TABLE_TEAMS = "CREATE TABLE "
            + TABLE_T + "(" + KEY_ID
            + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + KEY_FORMED + " TEXT );";



    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_T + "'");
        onCreate(db);
    }

    public long addFavorite(int id,String name, String formed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_NAME, name);
        values.put(KEY_FORMED, formed);
        long insert = db.insert(TABLE_T, null, values);

        return insert;
    }



   public ArrayList<Team> getFavorite() {
        ArrayList<Team> userModelArrayList = new ArrayList<Team>();

        String selectQuery = "SELECT * FROM " + TABLE_T;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Team tm = new Team();
                tm.setIdTeam(c.getInt(c.getColumnIndex(KEY_ID)));
                tm.setStrTeam(c.getString(c.getColumnIndex(KEY_NAME)));
                tm.setIntFormedYear(c.getString(c.getColumnIndex(KEY_FORMED)));
                // adding to Team list
                userModelArrayList.add(tm);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_T, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
