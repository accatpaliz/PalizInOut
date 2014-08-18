package com.example.virdi;

	import java.util.ArrayList;
	import java.util.List;
	
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.SQLException;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	import android.provider.BaseColumns;

	public class DatabaseHandler extends SQLiteOpenHelper {
		 
	    // All Static variables
	    // Database Version
	    private static final int DATABASE_VERSION = 1;
	 
	    // Database Name
	    private static final String DATABASE_NAME = "Personnel";
	 
	    // Contacts table name
	    private static final String TABLE_CONTACTS = "contacts";
	 
	    // Contacts Table Columns names
	    private static final String KEY_ID = "id";
	    private static final String KEY_NAME = "name";
	    private static final String KEY_GNAME = "gname";
	 
	    public DatabaseHandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	 
	    // Creating Tables
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
	                + KEY_GNAME + " TEXT" + ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	    }
	 
	    // Upgrading database
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
	 
	        // Create tables again
	        onCreate(db);
	    }
	    public void add(Personnel personnel) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, personnel.getName()); // Name
	        values.put(KEY_GNAME, personnel.getGName()); // Given Name
	        values.put(KEY_ID, personnel.getID()); // ID 
	     
	        // Inserting Row
	        db.insert(TABLE_CONTACTS, null, values);
	        db.close(); // Closing database connection
	    }
	    public Boolean isPersonnel(String id){
	    	SQLiteDatabase db = this.getReadableDatabase();
		     
	        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
	                KEY_NAME, KEY_GNAME }, KEY_ID + "=?",
	                new String[] { id }, null, null, null, null);
	        Boolean result = cursor.moveToFirst();
	        cursor.close();
	        return result;
	    }
	    public Personnel getPersonnel(String id) {
	        SQLiteDatabase db = this.getReadableDatabase();
	     
	        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
	                KEY_NAME, KEY_GNAME }, KEY_ID + "=?",
	                new String[] { id }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	     
	        Personnel personnel = new Personnel(Integer.parseInt(cursor.getString(0)),
	                cursor.getString(1), cursor.getString(2));
	        return personnel;
	    }
	    public List<Personnel> getAllPersonnel() {
	        List<Personnel> personnelList = new ArrayList<Personnel>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	     
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	Personnel personnel = new Personnel();
	            	personnel.setID(Integer.parseInt(cursor.getString(0)));
	            	personnel.setGName(cursor.getString(1));
	            	personnel.setName(cursor.getString(2));
	            	String data = cursor.getString(0)+"\n"+cursor.getString(1) +"\n"+ cursor.getString(2);
	            	Show.ArrayofName.add(data);
	                // Adding contact to list
	            	personnelList.add(personnel);
	            } while (cursor.moveToNext());
	        }
	     
	        // return contact list
	        return personnelList;
	    }
	    public String getPersonnelCount() {
	    			String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	    			SQLiteDatabase db = this.getReadableDatabase();
	    			Cursor cursor = db.rawQuery(countQuery, null);
	    			cursor.close();
	    	
	    			// return count
	    			return String.valueOf(cursor.getCount());
	    }
	    public int updatePersonnel(Personnel personnel) {
	        SQLiteDatabase db = this.getWritableDatabase();
	     
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, personnel.getName());
	        values.put(KEY_GNAME, personnel.getGName());
	     
	        // updating row
	        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
	                new String[] { String.valueOf(personnel.getID()) });
	    }
	    public void updateData(String name, String gname,String id) {
	    	SQLiteDatabase db = this.getWritableDatabase();
	    	 ContentValues values = new ContentValues(); 
	    	values.put(KEY_NAME, name); 
	    	values.put(KEY_GNAME, gname);
	    	db.update(TABLE_CONTACTS, values, BaseColumns._ID + "=" + id, null);
    	}

	    public void deletePersonnel(Personnel personnel) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
	                new String[] { String.valueOf(personnel.getID()) });
	        db.close();
	    }
	    public void deleteAllPersonnel () throws SQLException {
	    	SQLiteDatabase db = this.getWritableDatabase();
	    	db.delete(TABLE_CONTACTS, null, null);
	    }
	}
