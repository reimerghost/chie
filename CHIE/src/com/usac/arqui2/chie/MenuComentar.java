package com.usac.arqui2.chie;

import org.json.JSONArray;
import org.json.JSONObject;

import com.usac.arqui2.chie.webservice.Solicitud;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class MenuComentar extends ActionBarActivity {
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private TextView tv4;
	private TextView tv5;
	
	private RatingBar rb1;
	private RatingBar rb2;
	private RatingBar rb3;
	private RatingBar rb4;
	private RatingBar rb5;
	
	private EditText et1;
	
	private Button b1;
	
	private String codigo;
	
	private String id_servicio;
	private String id_unidad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_comentar);
		tv1 = (TextView)findViewById(R.id.tvEvMetrica1);
		tv2 = (TextView)findViewById(R.id.tvEvMetrica2);
		tv3 = (TextView)findViewById(R.id.tvEvMetrica3);
		tv4 = (TextView)findViewById(R.id.tvEvMetrica4);
		tv5 = (TextView)findViewById(R.id.tvEvMetrica5);
		rb1 = (RatingBar)findViewById(R.id.rbEvValor1);
		rb2 = (RatingBar)findViewById(R.id.rbEvValor2);
		rb3 = (RatingBar)findViewById(R.id.rbEvValor3);
		rb4 = (RatingBar)findViewById(R.id.rbEvValor4);
		rb5 = (RatingBar)findViewById(R.id.rbEvValor5);
		et1 = (EditText)findViewById(R.id.etEvComentario);
		b1 = (Button)findViewById(R.id.btEvPublicar);
		codigo = getIntent().getStringExtra("string-codigo");
		String[] separados = codigo.split(":");
		id_servicio = separados[0];
		id_unidad = separados[1];
		
		nombres_metricas(tv1, tv2, tv3, tv4, tv5);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				almacenar(String.valueOf(rb1.getRating()), String.valueOf(rb2.getRating()), String.valueOf(rb3.getRating()), String.valueOf(rb4.getRating()), String.valueOf(rb5.getRating()), et1.getText().toString());
				Intent intent = new Intent(MenuComentar.this, MenuQR.class);
				intent.putExtra("string-codigo", codigo);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_comentar, menu);
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
	public void almacenar(String v1, String v2, String v3, String v4, String v5, String c){
		//Usar el webservice de publicar
		Log.w("Datos", v1 + "-" + v2 + "-" + v3 + "-" + v4 + "-" + v5 + "-" + c + "-");
	}
	public void nombres_metricas(TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5){
		//LLAMAR METODO QUE DEVULEVE JSON DE LOS NOMBRES DE LAS METRICAS
		//Asignar el resultado a la variable JSON
		Solicitud s = new Solicitud();
		String JSON = s.getDatos(id_servicio); 
//		String JSON = "[{\"id_metrica\":\"1\",\"nombre\":\"Estado del bus\"},{\"id_metrica\":\"2\",\"nombre\":\"Puntualidad\"},{\"id_metrica\":\"3\",\"nombre\":\"Capacidad del bus\"},{\"id_metrica\":\"4\",\"nombre\":\"Actitud del conductor\"},{\"id_metrica\":\"5\",\"nombre\":\"Habilidad del conductor\"}]";
		try{
			JSONArray jsa = new JSONArray(JSON);
			JSONObject JSO = jsa.getJSONObject(0);
        	tv1.setText(JSO.getString("nombre"));
        	JSO = jsa.getJSONObject(1);
        	tv2.setText(JSO.getString("nombre"));
        	JSO = jsa.getJSONObject(2);
        	tv3.setText(JSO.getString("nombre"));
        	JSO = jsa.getJSONObject(3);
        	tv4.setText(JSO.getString("nombre"));
        	JSO = jsa.getJSONObject(4);
        	tv5.setText(JSO.getString("nombre"));
		}catch(Exception e){
        	Log.w("erro", e.toString());
        }
		
	}
}
