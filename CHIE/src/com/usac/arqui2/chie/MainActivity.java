package com.usac.arqui2.chie;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button scan;
	private TextView cont;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scan = (Button) findViewById(R.id.bLeerQR);
		cont = (TextView) findViewById(R.id.tvContadorEvaluaciones);
		set_contador(cont);
		scan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Camara.class);
				MainActivity.this.startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void set_contador(TextView tv){
		//LLAMAR METODO QUE DEVULEVE JSON DE LOS NOMBRES DE LAS METRICAS
		//Asignar el resultado a la variable JSON
		String JSON = "[{\"cont\":\"10\"}]";
		try{
			JSONArray jsa = new JSONArray(JSON);
			JSONObject JSO = jsa.getJSONObject(0);
			tv.setText(JSO.getString("cont"));
		}catch(Exception e){
			Log.w("erro", e.toString());
			tv.setText("0");
		}
	}
}