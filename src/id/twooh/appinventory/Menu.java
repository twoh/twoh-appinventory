package id.twooh.appinventory;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Menu extends Activity implements OnClickListener{

	private Button bTambah;
	private Button bLihat;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.menu);
	      
	      bTambah = (Button) findViewById(R.id.button_tambah);
	      bTambah.setOnClickListener(this);
	      bLihat = (Button) findViewById(R.id.button_view);
	      bLihat.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.button_tambah :
				Intent i = new Intent(this, CreateData.class);
				startActivity(i);
				break;
			case R.id.button_view :
				Intent i2 = new Intent(this, ViewData.class);
				startActivity(i2);
				break;
		}
	}
}
