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
	public static final String DB_NAME = "tourdestination.db";
	public static final int DB_VERSION = 1;

	// tour table constants
	public static final String TOUR_TABLE = "tour";

	public static final String TOUR_ID = "_id";
	public static final int TOUR_ID_COL = 0;

	public static final String TOUR_NAME = "tour_name";
	public static final int TOUR_NAME_COL = 1;

	public static final String TOUR_DESCRIPTION = "tour_description";
	public static final int TOUR_DESCRIPTION_COL = 2;

	public static final String TOUR_PRIMARY_LATITUDE = "tour_primary_latitude";
	public static final int TOUR_PRIMARY_LATITUDE_COL = 3;

	public static final String TOUR_PRIMARY_LONGITUDE = "tour_primary_longitude";
	public static final int TOUR_PRIMARY_LONGITUDE_COL = 4;

	// destination table constants
	public static final String DESTINATION_TABLE = "destination";

	public static final String DESTINATION_ID = "_id";
	public static final int DESTINATION_ID_COL = 0;

	public static final String DESTINATION_TOUR_ID = "tour_id";
	public static final int DESTINATION_TOUR_ID_COL = 1;

	public static final String DESTINATION_NAME = "destination_name";
	public static final int DESTINATION_NAME_COL = 2;

	public static final String DESTINATION_DESCRIPTION = "destination_description";
	public static final int DESTINATION_DESCRIPTION_COL = 3;

	public static final String DESTINATION_LATITUDE = "destination_latitude";
	public static final int DESTINATION_LATITUDE_COL = 4;

	public static final String DESTINATION_LONGITUDE = "destination_longitude";
	public static final int DESTINATION_LONGITUDE_COL = 5;

	// SQL statements
	public static final String CREATE_TOUR_TABLE = 
		"CREATE TABLE " + TOUR_TABLE + " (" + 
		TOUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
		TOUR_NAME + " TEXT NOT NULL UNIQUE);";

	public static final String CREATE_DESTINATION_TABLE = 
		"CREATE TABLE " + DESTINATION_TABLE + " (" + 
		DESTINATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
		DESTINATION_TOUR_ID + " INTEGER NOT NULL, " + 
		DESTINATION_NAME + " TEXT NOT NULL, " + 
		DESTINATION_DESCRIPTION + " TEXT NOT NULL, " + 
		DESTINATION_LATITUDE + " REAL NOT NULL, " + 
		DESTINATION_LONGITUDE + " REAL NOT NULL);";

	public static final String DROP_TOUR_TABLE = 
		"DROP TABLE IF EXISTS " + TOUR_TABLE;

	public static final String DROP_DESTINATION_TABLE = 
		"DROP TABLE IF EXISTS " + DESTINATION_TABLE;

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
			db.execSQL(TourDB.DROP_DESTINATION_TABLE);
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

	public ArrayList<Destination> getDestinations(String tourName) {
		String where = DESTINATION_TOUR_ID + "= ?";
		int tourID = getTour(tourName).getId();
		String[] whereArgs = { Integer.toString(tourID) };

		this.openReadableDB();
		Cursor cursor = db.query(DESTINATION_TABLE, null, where, whereArgs, 
			null, null, null);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		while (cursor.moveToNext()) {
			destinations.add(getDestinationFromCursor(cursor));
		}
		if (cursor != null) {
			cursor.close();
		}
		this.closeDB();

		return destinations;
	}

	public Tour getTour(String tourName) {
		String where = TOUR_NAME + "= ?";
		String[] whereArgs = {tourName};

		openReadableDB();
		Cursor cursor = db.query(TOUR_TABLE, null, where, whereArgs, 
			null, null, null);
		Tour tour = null;
		cursor.moveToFirst();
		tour = new Tour(
			cursor.getInt(TOUR_ID_COL), 
			cursor.getString(TOUR_NAME_COL),
			cursor.getString(TOUR_DESCRIPTION_COL), 
			cursor.getFloat(TOUR_PRIMARY_LATITUDE_COL), 
			cursor.getFloat(TOUR_PRIMARY_LONGITUDE_COL)
		);
		if (cursor != null) {
			cursor.close();
		}
		this.closeDB();

		return tour;
	}

	private static Destination getDestinationFromCursor(Cursor cursor) {
		if (cursor == null || cursor.getCount() == 0) {
			return null;
		}
		else {
			try {
				Destination destination = new Destination(
					cursor.getInt(DESTINATION_ID_COL),
					cursor.getInt(DESTINATION_TOUR_ID_COL),
					cursor.getString(DESTINATION_NAME_COL),
					cursor.getString(DESTINATION_DESCRIPTION_COL),
					cursor.getFloat(DESTINATION_LATITUDE_COL),
					cursor.getFloat(DESTINATION_LONGITUDE_COL)
				);
				return destination;
			}
			catch (Exception e) {
				return null;
			}
		}
	}
}
