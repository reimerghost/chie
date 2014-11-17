package com.usac.arqui2.chie.webservice;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class Solicitud {
	
	public Solicitud(){
		getDatos();	
	}

	// Estos datos los proporciona el webService
	final String NAMESPACE = "http://ws-chie.rhcloud.com/webservice/Servicios.php";
	final String URL = "http://10.0.2.2/Visitas/WebServiceVisitas.asmx";

	final String METHOD_NAME = "getMetrica";
	final String SOAP_ACTION = "http://ws-chie.rhcloud.com/webservice/Servicios.php/getMetrica";

	public void getDatos() {
		// Se instancia un objeto soap pasandole el namespace y el metodo del
		// webservice a consumir
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		// Se instancia un objeto envelope y se define que version de soap se
		// usará (10,11,12)
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		// establece la comunicacion por http
		// recordar habilitar permisos de internet
		HttpTransportSE transporte = new HttpTransportSE(URL);

		try {
			transporte.call(SOAP_ACTION, envelope); // ejecuta la llamada

			SoapObject resSoap = (SoapObject) envelope.getResponse(); // obtiene
																		// respuesta

			// lo siguiente es para recorrer, leer y guardar la respuesta en una
			// lista
			int nPropiedades = resSoap.getPropertyCount();
			for (int i = 0; i < nPropiedades; i++) {
				SoapObject ic = (SoapObject) resSoap.getProperty(i);
				String tipo = ic.getProperty("tipo").toString();
				System.out.println(tipo);
				Log.i("WEBSER", tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Error Webs", e.getMessage());
		}
	}
}
