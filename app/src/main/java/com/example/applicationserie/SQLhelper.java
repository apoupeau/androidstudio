package com.example.applicationserie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLhelper extends SQLiteOpenHelper {

        public static final String TABLE_SERIE = "serie";
        private static final String DATABASE_NAME = "serie.db";
        private static final int DATABASE_VERSION = 1;

        public static final String COLUMN_id = "id";
        public static final String COLUMN_titre="titre";
        public static final String COLUMN_resume="resume";
        public static final String COLUMN_duree="duree";
        public static final String COLUMN_premiereDiffusion="premiereDiffusion";
        public static final String COLUMN_image="image";



        private static final String DATABASE_CREATE = "create table "
                + TABLE_SERIE + "(" + COLUMN_id
                + " integer primary key autoincrement, " + COLUMN_titre
                + " text not null, " + COLUMN_resume
                + " text not null, " + COLUMN_duree
                + " text not null, " + COLUMN_premiereDiffusion
                + " text not null, " + COLUMN_image
                + ");";

        //Partie Obligatoire
        public SQLhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(SQLhelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIE);
            onCreate(db);
        }
        //Jusqu'ici


        /************************************************/
        /******** retourne la liste des series         **/
        /************************************************/
        public ArrayList<Serie> getLesSeries() {
            ArrayList<Serie> lesSesries = new ArrayList<Serie>();
            String selectQuery = "SELECT  * FROM " + TABLE_SERIE;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Serie serie = new Serie(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5));
                    lesSesries.add(serie);
                } while (cursor.moveToNext());
            }
            return lesSesries;
        }
        /************************************************/
        /******** retourne une serie                   **/
        /************************************************/
        public Serie getSerie(long id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_SERIE, new String[] {
                            COLUMN_id,
                            COLUMN_titre,
                            COLUMN_resume,
                            COLUMN_duree,
                            COLUMN_premiereDiffusion,
                            COLUMN_image}, COLUMN_id + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Serie serie = new Serie(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            return serie;
        }
        /************************************************/
        /******** creer une serie                      **/
        /************************************************/
        public void addSerie(Serie serie) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put(COLUMN_id, serie.getId());
            values.put(COLUMN_titre, serie.getTitre());
            values.put(COLUMN_resume, serie.getResume());
            values.put(COLUMN_duree, serie.getDuree());
            values.put(COLUMN_premiereDiffusion, serie.getPremiereDiiffusion());
            values.put(COLUMN_image, serie.getImage());
            // Inserting Row
            db.insert(TABLE_SERIE, null, values);
            // Closing database connection
            db.close();
        }
}

