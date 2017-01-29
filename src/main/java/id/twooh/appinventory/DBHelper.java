package id.twooh.appinventory;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper{

	public static final String TABLE_NAME = "data_inventori";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "nama_barang";
	public static final String COLUMN_MERK = "merk_barang";
	public static final String COLUMN_HARGA = "harga_barang";
	private static final String db_name ="inventori.db";
	private static final int db_version=1;
	
	// Database creation sql statement
	  private static final String db_create = "create table "
	      + TABLE_NAME + "(" 
	      + COLUMN_ID +" integer primary key autoincrement, " 
	      + COLUMN_NAME+ " varchar(50) not null, "
	      + COLUMN_MERK+ " varchar(50) not null, "
	      + COLUMN_HARGA+ " varchar(50) not null);";
	
	public DBHelper(Context context) {
		super(context, db_name, null, db_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(db_create);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db);
		
	}

}
