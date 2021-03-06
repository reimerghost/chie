package com.usac.arqui2.chie;

import com.usac.arqui2.chie.webservice.Solicitud;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuQR extends ActionBarActivity {
	private String codigo;
	private TextView txview;
	private Button eval;
	private Button vere;
	private String id_servicio;
	private String id_unidad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_qr);
		txview = (TextView)findViewById(R.id.qrResumen);
		eval = (Button) findViewById(R.id.btEvaluar);
		vere = (Button) findViewById(R.id.btVerEvaluaciones);
		codigo = getIntent().getStringExtra("string-codigo");
		String[] separados = codigo.split(":");
		id_servicio = separados[0];
		id_unidad = separados[1];
		/*
		 * TODO: Consumir WebService para obtener el Resumen
		 * 
		 */
		//this.setTitle("Informacion de " + id_unidad + " " + id_servicio);
		Spanned contenido = Html.fromHtml("<p><b>RESUMEN: </b></p>" +
				"<p>abc</p>");
		txview.setText(contenido);
		eval.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(MenuQR.this, MenuComentar.class);
				intent.putExtra("string-codigo", codigo);
				startActivity(intent);
			}
		});
		vere.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuQR.this, EvaluacionesAnteriores.class);
				intent.putExtra("string-codigo", codigo);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_qr, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(MenuQR.this, MainActivity.class);
		startActivity(intent);
		return;
	}
}
