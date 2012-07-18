package com.example.ourfirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class FetchFeed {

	private static final String TAG = FetchFeed.class.getSimpleName();

	// DB-related constants
	static final String DB_NAME = "datafeed.db";
	static final int DB_VERSION = 3;
	
	static final String TABLE = "posts";
	static final String COL_ID = BaseColumns._ID;
	static final String COL_CREATED_AT = "created_at";
	static final String COL_USER = "user_name";
	static final String COL_TEXT = "user_text";

	static final String SORT_BY = COL_CREATED_AT + " DESC";

	private DbHelper dbHelper;
	Context context;
	SQLiteDatabase db;

	/** Constructor */
	public FetchFeed(Context context) {
		this.context = context;
		dbHelper = new DbHelper();
	}

	/** Converts Post to ContentValues. */
	public static ContentValues postToValues(Comment post) {
		ContentValues values = new ContentValues();
		values.put(COL_ID, post.id);
		values.put(COL_CREATED_AT, post.createdAt);
		values.put(COL_USER, post.user_name);
		values.put(COL_TEXT, post.user_text);
		return values;
	}

	/** Inserts the status into the database. */
	public long insert(Comment post) {
		db = dbHelper.getWritableDatabase();

		// Create content values from status object
		ContentValues values = postToValues(post);

		return db.insertWithOnConflict(TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);
	}

	
	/** Returns all statuses in timeline. */
	public Cursor query() {
		db = dbHelper.getReadableDatabase();
		return db.query(TABLE, null, null, null, null, null, SORT_BY);
	}

	
	/** Class to help us create and upgrade database. */
	class DbHelper extends SQLiteOpenHelper {

		public DbHelper() {
			super(context, DB_NAME, null, DB_VERSION);
			Log.d(TAG, "DbHelper() instantiated");
			//onCreate(db);
			db = this.getWritableDatabase();
			//(context.openOrCreateDatabase(DB_NAME, 0, null)).close();
			
		}

		
		/** Called only once, first time we create the database file. */
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "onCreate SQLiteDatabase");
			String sql = String.format("CREATE TABLE %s "
					+ "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INT," + "%s TEXT, %s TEXT)",
					TABLE, COL_ID, COL_CREATED_AT, COL_USER, COL_TEXT);
			Log.d(TAG, "onCreate with SQL: " + sql);
			db.execSQL(sql);
		}

		/** Called when the old schema is different than new schema. */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d(TAG, "onUpgrade SQLiteDatabase");
			// Typically SQL such as: ALTER TABLE ADD COLUMN ...
			db.execSQL("DROP TABLE IF EXISTS " + TABLE);
			onCreate(db);
		}
	}

	/** Getter. */
	public DbHelper getDbHelper() {
		return dbHelper;
	}
	
	
}
