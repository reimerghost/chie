package com.usac.arqui2.chie;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;

import com.usac.arqui2.chie.adapters.ExpandableListAdapter;
import com.usac.arqui2.chie.adapters.list_item;

import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

public class EvaluacionesAnteriores extends ActionBarActivity {
	private ExpandableListAdapter listAdapter;
	private ExpandableListView evas;
	private List<String> listDataHeader;
	private HashMap<String, List<list_item>> listDataChild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluaciones_anteriores);
		evas = (ExpandableListView)findViewById(R.id.elvEvaluaciones);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
       
        if(listDataHeader.size() > 0){
        	evas.setAdapter(listAdapter);
        }
        else{
        	setTitle("No hay Evaluaciones Anteriores");
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.evaluaciones_anteriores, menu);
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
	/*
     * Preparing the list data
     */
    private void prepareListData() {
    	
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<list_item>>();
        //LLAMAR METODO QUE DEVULEVE JSON DE LOS NOMBRES DE LAS METRICAS
    	//Asignar el resultado a la variable JSON
        String JSON = "[{\"id_metrica\":\"1\",\"nombre\":\"Estado del bus\"},{\"id_metrica\":\"2\",\"nombre\":\"Puntualidad\"},{\"id_metrica\":\"3\",\"nombre\":\"Capacidad del bus\"},{\"id_metrica\":\"4\",\"nombre\":\"Actitud del conductor\"},{\"id_metrica\":\"5\",\"nombre\":\"Habilidad del conductor\"}]";
        
        try{
        	JSONArray jsa = new JSONArray(JSON);
	        List<String> metricas = new ArrayList<String>();
	        for(int i = 0; i < jsa.length(); i++){
	        	JSONObject JSO = jsa.getJSONObject(i);
	        	String nombre = JSO.getString("nombre");
	        	metricas.add(nombre);
	        }
	        //LLAMAR METODO QUE DEVULEVE JSON DE LAS EVALUACIONES
	    	//Asignar el resultado a la variable JSON
	        JSON = "[{\"id\":\"1\",\"fecha\":\"11/11/1111\",\"metrica1\":\"2.0\",\"metrica2\":\"3.0\",\"metrica3\":\"4.0\",\"metrica4\":\"5.0\",\"metrica5\":\"0.0\",\"comentario\":\"cOMENAJFD ASDLF JASDJBHBKKBJFAS\"},{\"id\":\"2\",\"fecha\":\"22/22/2222\",\"metrica1\":\"2.0\",\"metrica2\":\"3.0\",\"metrica3\":\"4.0\",\"metrica4\":\"5.0\",\"metrica5\":\"0.0\",\"comentario\":\"cOMENAJFD ASDLF JASDJBHBKKBJFAS\"},{\"id\":\"3\",\"fecha\":\"33/33/1111\",\"metrica1\":\"2.0\",\"metrica2\":\"3.0\",\"metrica3\":\"4.0\",\"metrica4\":\"5.0\",\"metrica5\":\"0.0\",\"comentario\":\"cOMENAJFD ASDLF JASDJBHBKKBJFAS\"},{\"id\":\"4\",\"fecha\":\"55/55/5555\",\"metrica1\":\"2.0\",\"metrica2\":\"3.0\",\"metrica3\":\"4.0\",\"metrica4\":\"5.0\",\"metrica5\":\"0.0\",\"comentario\":\"cOMENAJFD ASDLF JASDJBHBKKBJFAS\"}]";
	        jsa = new JSONArray(JSON);
	        
	        for(int i = 0; i < jsa.length(); i++){
	        	JSONObject JSO = jsa.getJSONObject(i);
	        	String id = JSO.getString("id");
	        	String fe = JSO.getString("fecha");
	        	String m1 = JSO.getString("metrica1");
	        	String m2 = JSO.getString("metrica2");
	        	String m3 = JSO.getString("metrica3");
	        	String m4 = JSO.getString("metrica4");
	        	String m5 = JSO.getString("metrica5");
	        	String co = JSO.getString("comentario");
	        	listDataHeader.add("Evaluacion " + id + ": " + fe);
	        	List<list_item> valores = new ArrayList<list_item>();
		        valores.add(new list_item(metricas.get(0),m1, false));
		        valores.add(new list_item(metricas.get(1),m2, false));
		        valores.add(new list_item(metricas.get(2),m3, false));
		        valores.add(new list_item(metricas.get(3),m4, false));
		        valores.add(new list_item(metricas.get(4),m5, false));
		        valores.add(new list_item("Comentario",co, true));
		        listDataChild.put(listDataHeader.get(i), valores);
	        }
        }catch(Exception e){
        	Log.w("erro", e.toString());
        }
    }
}

