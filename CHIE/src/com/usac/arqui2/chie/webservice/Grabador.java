package com.usac.arqui2.chie.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class Grabador {
	
	// Estos datos los proporciona el webService
	final String NAMESPACE = "http://ws-chie.rhcloud.com/webservice/Servicios.php?wsdl";
	final String URL = "http://ws-chie.rhcloud.com/webservice/Servicios.php";
	String METHOD_NAME = "";
	String SOAP_ACTION = "";
	public Grabador() {		
	}

	
	
	public void insertarUsuario(String user) {	
		
		String METHOD_NAME = "insertarUsuario";
		String SOAP_ACTION = "http://ws-chie.rhcloud.com/webservice/Servicios.php/insertarUsuario";
		
		// Se instancia un objeto soap pasandole el namespace y el metodo del
		// webservice a consumir
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		
		
		PropertyInfo GameId = new PropertyInfo();
		GameId.setName("gameid");
		GameId.setValue(user);
		request.addProperty(GameId);
		
		// Se instancia un objeto envelope y se define que version de soap se
		// usará (10,11,12)
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		// envelope.dotNet = true;

		// establece la comunicacion por http
		// recordar habilitar permisos de internet
		HttpTransportSE transporte = new HttpTransportSE(URL);

		try {
			transporte.call(SOAP_ACTION, envelope); // ejecuta la llamada
//			 SoapObject resSoap = (SoapObject) envelope.getResponse(); //
			// obtiene respuesta
			SoapObject resSoap = (SoapObject) envelope.bodyIn;

			// lo siguiente es para recorrer, leer y guardar la respuesta en una
			// lista
			int nPropiedades = resSoap.getPropertyCount();
			for (int i = 0; i < nPropiedades; i++) {
				String tipo = resSoap.getProperty(i).toString();
				System.out.println(tipo);
				Log.i("WEBSER", tipo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Error Webs", e.toString());
		}
	}
}
