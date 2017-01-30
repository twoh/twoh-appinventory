package id.web.twoh.appinventory;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DBDataSource {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = {DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_MERK, DBHelper.COLUMN_HARGA};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert barang ke database
    public Barang createBarang(String nama, String merk, String harga) {

		/*membuat sebuah ContentValues, yang berfungsi 
		 * untuk memasangkan data dengan nama-nama 
		 * kolom pada database */
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_MERK, merk);
        values.put(DBHelper.COLUMN_HARGA, harga);
	    
	    /*mengeksekusi perintah SQL insert data 
	     * yang akan mengembalikan sebuah insert ID */
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);
	    
	    /*setelah data dimasukkan, memanggil
	     * perintah SQL Select menggunakan Cursor untuk
	     * melihat apakah data tadi benar2 sudah masuk
	     * dengan menyesuaikan ID = insertID*/
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
	    
	    /*pindah ke data paling pertama */
        cursor.moveToFirst();
	    
	    /*mengubah objek pada kursor pertama tadi
	     * ke dalam objek barang*/
        Barang newBarang = cursorToBarang(cursor);

        //close cursor
        cursor.close();

        //mengembalikan barang baru
        return newBarang;
    }

    public Barang getBarang(long id) {
        Barang barang = new Barang(); //inisialisasi barang
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id =" + id, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek barang
        barang = cursorToBarang(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return barang;
    }

    //update barang yang diedit
    public void updateBarang(Barang b) {
        //ambil id barang
        String strFilter = "_id=" + b.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAME, b.getNama_barang());
        args.put(DBHelper.COLUMN_MERK, b.getMerk_barang());
        args.put(DBHelper.COLUMN_HARGA, b.getHarga_barang());
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    private Barang cursorToBarang(Cursor cursor) {
        // buat objek barang baru
        Barang barang = new Barang();
        // debug LOGCAT
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng " + cursor.getString(1) + "," + cursor.getString(2));
		
		/* Set atribut pada objek barang dengan
		 * data kursor yang diambil dari database*/
        barang.setId(cursor.getLong(0));
        barang.setNama_barang(cursor.getString(1));
        barang.setMerk_barang(cursor.getString(2));
        barang.setHarga_barang(cursor.getString(3));

        //kembalikan sebagai objek barang
        return barang;
    }

    //mengambil semua data barang
    public ArrayList<Barang> getAllBarang() {
        ArrayList<Barang> daftarBarang = new ArrayList<Barang>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            Barang barang = cursorToBarang(cursor);
            daftarBarang.add(barang);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarBarang;
    }

    // delete barang sesuai ID
    public void deleteBarang(long id)
    {
        String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
