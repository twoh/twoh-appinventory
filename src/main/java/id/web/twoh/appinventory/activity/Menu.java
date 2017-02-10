package id.web.twoh.appinventory.activity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import id.web.twoh.appinventory.R;


public class Menu extends BaseAdsActivity implements OnClickListener {

    private Button bTambah;
    private Button bLihat;
    private Button btTutorial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        loadAdsRequest();
        bTambah = (Button) findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        bLihat = (Button) findViewById(R.id.button_view);
        bLihat.setOnClickListener(this);
        btTutorial = (Button) findViewById(R.id.button_about);
        btTutorial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        decideToDisplay();
        switch (v.getId()) {
            case R.id.button_tambah:
                Intent i = new Intent(this, CreateData.class);
                startActivity(i);
                break;
            case R.id.button_view:
                Intent i2 = new Intent(this, ViewData.class);
                startActivity(i2);
                break;
            case R.id.button_about:
                readTheTutorial("https://www.twoh.co/2013/01/12/tutorial-membuat-aplikasi-database-sqlite-android/");
                break;
        }
    }
}
