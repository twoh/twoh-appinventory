package id.web.twoh.appinventory.activity;


import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import id.web.twoh.appinventory.Barang;
import id.web.twoh.appinventory.DBDataSource;
import id.web.twoh.appinventory.R;


public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {

    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Barang> values;
    private Button editButton;
    private Button delButton;

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

        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barang barang = (Barang) getListAdapter().getItem(position);
                switchToGetData(barang.getId());
            }
        });
    }

    //apabila ada long click
    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos,
                                   final long id) {

        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Barang b = (Barang) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data);

        //apabila tombol edit diklik
        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );

        //apabila tombol delete di klik
        delButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete barang
                        dataSource.deleteBarang(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );

        return true;
    }

    //method untuk edit data
    public void switchToEdit(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    //method untuk get single data
    public void switchToGetData(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(this, ViewSingleData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }

    //method yang dipanggil ketika edit data selesai
    public void finale() {
        ViewData.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

}
