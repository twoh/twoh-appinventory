package id.web.twoh.appinventory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.web.twoh.appinventory.R;

/**
 * Created by Hafizh Herdi on 1/29/2017.
 */

public class ViewSingleData extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_data);

        TextView tvNama = (TextView) findViewById(R.id.tv_nama_barang);
        TextView tvMerk = (TextView) findViewById(R.id.tv_merk_barang);
        TextView tvHarga = (TextView) findViewById(R.id.tv_harga_barang);

        System.out.println("APPINVENT "+getIntent().getExtras().getString("nama"));
        tvNama.setText("Barang "+getIntent().getExtras().getString("nama"));
        tvMerk.setText("Merk "+getIntent().getExtras().getString("merk"));
        tvHarga.setText("Harga "+getIntent().getExtras().getString("harga"));

        Button buttonOK = (Button) findViewById(R.id.bt_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
