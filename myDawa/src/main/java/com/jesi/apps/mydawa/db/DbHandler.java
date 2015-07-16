package com.jesi.apps.mydawa.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "mobidawa_db";
	
	//Table Names
	public static final String TABLE_DRUGS = "drugs";
	public static final String TABLE_PRESCRIPTION = "prescriptions";
	
	//Drug Fields
	public static final String KEY_DRUG_ID = "id";
	public static final String KEY_DRUG_BRAND_NAME = "name";
	public static final String KEY_DRUG_SCIENTIFIC_NAME = "scientific";
	public static final String KEY_DRUG_FULL_DOSE = "full_dose";
	public static final String KEY_DRUG_AVOID = "avoid";
	public static final String KEY_DRUG_TAKE_TIME = "take_time";
	public static final String KEY_DRUG_NOT_FOR = "not_for";
	
	public DbHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_TABLE_DRUGS = "CREATE TABLE " + TABLE_DRUGS + "("
				+ KEY_DRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_DRUG_BRAND_NAME + " TEXT, "
				+ KEY_DRUG_SCIENTIFIC_NAME + " TEXT, "
				+ KEY_DRUG_FULL_DOSE + " TEXT, "
				+ KEY_DRUG_AVOID + " TEXT, "
				+ KEY_DRUG_TAKE_TIME + " TEXT, "
				+ KEY_DRUG_NOT_FOR + " TEXT " +")";
		db.execSQL(CREATE_TABLE_DRUGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		if(newVersion > oldVersion){
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGS);
		}
	}
	
	public void insertDrug(Drug drug){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DRUG_ID, drug.getId());
		values.put(KEY_DRUG_BRAND_NAME, drug.getBrand_name());
		values.put(KEY_DRUG_SCIENTIFIC_NAME, drug.getScientific_name());
		values.put(KEY_DRUG_FULL_DOSE, drug.getFull_dose());
		values.put(KEY_DRUG_AVOID, drug.getAvoid());
		values.put(KEY_DRUG_TAKE_TIME, drug.getTake_time());
		values.put(KEY_DRUG_NOT_FOR, drug.getNot_for());
		db.insert(TABLE_DRUGS, null, values);
		db.close();
	}
	
	public List<Drug> getDrugs(){
		List<Drug> drugs = new ArrayList<Drug>();
		String selectQuery = "SELECT * FROM " + TABLE_DRUGS + " ORDER BY " + KEY_DRUG_BRAND_NAME + " ASC";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			do{
				Drug drug = new Drug();
				drug.setId(cursor.getInt(0));
				drug.setBrand_name(cursor.getString(1));
				drug.setScientific_name(cursor.getString(2));
				drugs.add(drug);
			} while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return drugs;
	}
	
	public Drug getDrug(int id){
		SQLiteDatabase db = this.getReadableDatabase();
    	Drug drug = new Drug();
    	Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_DRUG_ID + " = " + id, null);  
    	
    	try{
    		if (cursor != null)
    			cursor.moveToFirst();
    		drug.setId(cursor.getInt(0));
    		drug.setBrand_name(cursor.getString(1));
    		drug.setScientific_name(cursor.getString(2));
    		cursor.close();
    		db.close();
    	}
    	catch(Exception e){
    		Log.d("Drugs", "Error Getting Drugs");
    	}
    	return drug;
	}
	
	public Drug getDrug(String name){
		SQLiteDatabase db = this.getReadableDatabase();
    	Drug drug = new Drug();
    	Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_DRUG_BRAND_NAME + " LIKE '%" + name + "%'", null);  
    	
    	try{
    		if (cursor != null)
    			cursor.moveToFirst();
    		drug.setId(cursor.getInt(0));
    		drug.setBrand_name(cursor.getString(1));
    		drug.setScientific_name(cursor.getString(2));
    		cursor.close();
    		db.close();
    	}
    	catch(Exception e){
    		Log.d("Drugs", "Error Getting Drugs");
    	}
    	return drug;
	}
	
	public void resetData(String tableName){
		SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(tableName, null, null);
	}
}