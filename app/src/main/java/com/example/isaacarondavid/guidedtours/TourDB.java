package com.example.isaacarondavid.guidedtours;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TourDB {

	// database constants
	public static final String DB_NAME = "tour.db";
	public static final int DB_VERSION = 1;

	// tour table constants
	public static final String TOUR_TABLE = "tour";

	public static final String TOUR_ID = "_id";
	public static final int TOUR_ID_COL = 0;

	public static final String TOUR_NAME = "tour_name";
	public static final int TOUR_NAME_COL = 1;

	public static final String CREATE_TOUR_TABLE = 
		"CREATE TABLE " + TOUR_TABLE + " (" + 
		TOUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
		TOUR_NAME + " TEXT	NOT NULL UNIQUE);";

	public static final String DROP_TOUR_TABLE = 
		"DROP TABLE IF EXISTS " + TOUR_TABLE;

	public static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// create tables
			db.execSQL(CREATE_TOUR_TABLE);
		}

		public void onUpgrade(SQLiteDatabase db, 
				int oldVersion, int newVersion) {
			Log.d("Task list", "Upgrading db from version "
					+ oldVersion + " to " + newVersion);

			db.execSQL(TourDB.DROP_TOUR_TABLE);
			onCreate(db);
		}
	}

	// database and database helper objects
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	// constructor
	public TourDB(Context context) {
		dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
	}

	// private methods
	private void openReadableDB() {
		db = dbHelper.getReadableDatabase();
	}

	private void openWriteableDB() {
		db = dbHelper.getWritableDatabase();
	}

	private void closeDB() {
		if (db != null) {
			db.close();
		}
	}
}
