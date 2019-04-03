package com.example.test_php2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseHelper2  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "avg.db";


    private static final String SMILE_TABLE = "smile";
    private static final String ARM_TABLE = "arm";
    private static final String SOUND_TABLE = "sound";

    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_DIST = "dist";


    private static final String SQL_CREATE_TABLE_SMILE
            = "CREATE TABLE " + SMILE_TABLE + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT,"
            + "dist DOUBLE)";

    private static final String SQL_CREATE_TABLE_ARM
            = "CREATE TABLE " + ARM_TABLE + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT,"
            + "dist DOUBLE)";

    private static final String SQL_CREATE_TABLE_SOUND
            = "CREATE TABLE " + SOUND_TABLE + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT,"
            + "dist DOUBLE)";



    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//        db.execSQL("DROP TABLE IF EXISTS " + SMILE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + ARM_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + SOUND_TABLE);
//        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {







    }



    public void updateDistRc(double dist , String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DIST, dist);
        db.insert(SOUND_TABLE, null, values);
        db.close();

    }

    public void updateDistSm(double dist , String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DIST, dist);
        values.put(COL_NAME, name);
        db.insert(SMILE_TABLE, null, values);
        db.close();


    }

    public void updateDistArm(double dist , String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DIST, dist);
        db.insert(ARM_TABLE, null, values);
        db.close();
    }

    public boolean checkRc(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SOUND_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();



            if (cursorCount > 4) {
                return true;
            } else {
                return false;
            }
    }

    public boolean checkArm(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + ARM_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();

            if (cursorCount > 4) {
                return true;
            } else {
                return false;
            }


    }


    public boolean checkSm(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SMILE_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();


            if (cursorCount > 4) {
                return true;
            } else {
                return false;
            }
    }


    public double maxSound(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        double max = 0;
        double min = 1000;
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SOUND_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        for(int i =cursor.getCount(); i>cursor.getCount()-5; --i){
            cursor.moveToPosition(i);
            double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));

            if(cr > max){
                max = cr;
            }
            if(cr < min){
                min = cr;
            }

        }

        cursor.close();
        db.close();

        return max+((max-min)/2);
    }


    public double maxSmile(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        double max = 0;
        double min = 1000;
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SMILE_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        for(int i =cursor.getCount(); i>cursor.getCount()-5; --i){
            cursor.moveToPosition(i);
            double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));

            if(cr > max){
                max = cr;
            }
            if(cr < min){
                min = cr;
            }

        }

        cursor.close();
        db.close();

        return max+((max-min)/2);
    }



    public double maxArm(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        double max = 0;
        double min = 1000;
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + ARM_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        for(int i =cursor.getCount(); i>cursor.getCount()-5; --i){
            cursor.moveToPosition(i);
            double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));

            if(cr > max){
                max = cr;
            }
            if(cr < min){
                min = cr;
            }
        }
        return max+((max-min)/2);
    }



    public String check(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        name = "'" + name + "'";

        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SOUND_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        String cursorCount = String.valueOf(cursor.getCount());
        cursor.close();
        db.close();

        return cursorCount;


    }


    public double firstSmile(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SMILE_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        cursor.moveToPosition(0);
        double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));
        return cr;
    }


    public double firstArm(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + ARM_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        cursor.moveToPosition(0);
        double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));
        return cr;
    }


    public double firstSound(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        name = "'" + name + "'";
        Cursor cursor = db.rawQuery("SELECT " + COL_DIST
                + " FROM " + SOUND_TABLE + " WHERE " + COL_NAME + "= " + name , null);
        cursor.moveToPosition(0);
        double cr = cursor.getDouble(cursor.getColumnIndex(COL_DIST));
        return cr;
    }


}
