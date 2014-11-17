package com.usac.arqui2.chie;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 
        // Adding child data
        listDataHeader.add("Evaluacion 1: 11/11/2014");
        listDataHeader.add("Evaluacion 2: 12/11/2014");
        listDataHeader.add("Evaluacion 3: 13/11/2014");
 
        // Adding child data
        List<String> metricas = new ArrayList<String>();
        metricas.add("Metrica 1");
        metricas.add("Metrica 2");
        metricas.add("Metrica 3");
        metricas.add("Metrica 4");
        metricas.add("Metrica 5");
        for(int i = 0; i < listDataHeader.size(); i++){
        	List<list_item> valores = new ArrayList<list_item>();
	        valores.add(new list_item(metricas.get(0),String.valueOf((float)i), false));
	        valores.add(new list_item(metricas.get(1),String.valueOf((float)i), false));
	        valores.add(new list_item(metricas.get(2),String.valueOf((float)i), false));
	        valores.add(new list_item(metricas.get(3),String.valueOf((float)i), false));
	        valores.add(new list_item(metricas.get(4),String.valueOf((float)i), false));
	        valores.add(new list_item("Comentario","Comentario fjaldksfjk lasdj fñlkasdj fñasdjf ñlasdjfñl asdjfasdñlfjkl ns", true));
	        listDataChild.put(listDataHeader.get(i), valores); // Header, Child data
        }
       /* valores.clear();
        valores = new ArrayList<list_item>();
        valores.add(new list_item(metricas.get(0),"1.5", false));
        valores.add(new list_item(metricas.get(1),"2.5", false));
        valores.add(new list_item(metricas.get(2),"3.5", false));
        valores.add(new list_item(metricas.get(3),"4.5", false));
        valores.add(new list_item(metricas.get(4),"5.0", false));
        listDataChild.put(listDataHeader.get(1), valores);
        listDataChild.put(listDataHeader.get(2), valores);*/
    }
}

