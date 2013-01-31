package id.twooh.appinventory;




import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class ViewData extends ListActivity {
	
	//inisialisasi kontroller
	private DBDataSource dataSource;
	
	//inisialisasi arraylist
	private ArrayList<Barang> values;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.viewdata);
	      
	      dataSource = new DBDataSource(this);
	      // buka kontroller
	      dataSource.open();
	      
	      // ambil semua data barang
	      values = dataSource.getAllBarang();
	      
	      // masukkan data barang ke array adapter
	      ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this,
	    	        android.R.layout.simple_list_item_1, values);
	      
	      // set adapter pada list
	      setListAdapter(adapter);
	}
}
