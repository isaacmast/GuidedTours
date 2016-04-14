package com.example.isaacarondavid.guidedtours;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class for handling interaction with the database
 * @author Isaac Mast 
 */
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

	/**
	 * Controls creation and upgrading of TourDB database
	 */
	public static class DBHelper extends SQLiteOpenHelper {

		/**
		 * Constructs a new DBHelper object
		 * @param context - provides information about application environment
		 * @param name - the name of the database
		 * @param factory - allows the returning of Cursor sub-classes during DB query
		 * @param version - the version of the database
		 */
		public DBHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// create tables
			db.execSQL(CREATE_TOUR_TABLE);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
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

	/**
	 * Constructs a new TourDB object
	 * @param context - provides information about application environment
	 */
	public TourDB(Context context) {
		dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * Create and/or open a database for read access
	 */
	private void openReadableDB() {
		db = dbHelper.getReadableDatabase();
	}

	/**
	 * Create and/or open a database for read/write access 
	 */
	private void openWriteableDB() {
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * Closes the database object if open
	 */
	private void closeDB() {
		if (db != null) {
			db.close();
		}
	}

	/**
	 * Retrieves multiple rows from the destinations table
	 * @param tourName - the name of the associated Tour with the retrieved destinations
	 * @return destinations - an ArrayList containing the destinations associated with 
	 * the tour of name tourName
	 */
	public ArrayList<Destination> getDestinations(String tourName) {
		String where = DESTINATION_TOUR_ID + "= ?";			// ? represents parameter supplied later
		int tourID = getTour(tourName).getId();
		String[] whereArgs = { Integer.toString(tourID) };	// WHERE clause parameter 

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

	/**
	 * Retrieves a tour of name tourName from the tour table
	 * @param tourName - the name of the tour to be retrieved
	 * @return tour - the tour of name tourName retrieved from the database
	 */
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

	/**
	 * Retrieves the current destination from the Cursor object
	 * @param cursor - the Cursor object that contains the results from a DB query
	 * @return destination - the destination pointed at by cursor
	 * @return null - if cursor is empty or null or exception is thrown
	 */
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

	/**
	/**
	 * Inserts a new row into the Destination table
	 * @param destination - the destination to be inserted into table
	 * @return rowID - the row ID of the newly inserted row (-1 if an error occurred)
	 */
	public long insertDestination(Destination destination) {
		ContentValues cv = new ContentValues();
		cv.put(DESTINATION_TOUR_ID, destination.getTourId());
		cv.put(DESTINATION_NAME, destination.getName());
		cv.put(DESTINATION_DESCRIPTION, destination.getDescription());
		cv.put(DESTINATION_LATITUDE, destination.getLatitude());
		cv.put(DESTINATION_LONGITUDE, destination.getLongitude());

		this.openWriteableDB();
		long rowID = db.insert(DESTINATION_TABLE, null, cv);
		this.closeDB();

		return rowID;
	}

	/**
	 * Updates the Destination table in the DB 
	 * @param destination - the destination to update the DB with
	 * @return rowCount - the number of rows affected by the update
	 */
	public int updateDestination(Destination destination) {
		ContentValues cv = new ContentValues();
		cv.put(DESTINATION_TOUR_ID, destination.getTourId());
		cv.put(DESTINATION_NAME, destination.getName());
		cv.put(DESTINATION_DESCRIPTION, destination.getDescription());
		cv.put(DESTINATION_LATITUDE, destination.getLatitude());
		cv.put(DESTINATION_LONGITUDE, destination.getLongitude());

		String where = DESTINATION_ID + "= ?";
		String[] whereArgs = { String.valueOf(destination.getDestinationId()) };

		this.openWriteableDB();
		int rowCount = db.update(DESTINATION_TABLE, cv, where, whereArgs);
		this.closeDB();
		
		return rowCount;
	}

	/**
	 * Deletes the destination with destinationId of id from DB
	 * @param id - the destinationId of the destination to be deleted from DB
	 * @return rowCount - the number of rows affected by the deletion
	 */
	public int deleteDestination(long id) {
		String where = DESTINATION_ID + "= ?";
		String[] whereArgs = { String.valueOf(id) };

		this.openWriteableDB();
		int rowCount = db.delete(DESTINATION_TABLE, where, whereArgs);
		this.closeDB();

		return rowCount;
	}
}
