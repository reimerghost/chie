package com.usac.arqui2.chie.webservice;

import java.util.ArrayList;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Vendedor {
	// Propiedades y constructores
	public Integer IdVendedor;
	public String Nombres;

	public Vendedor(Integer idvendedor, String nombres) {
		this.IdVendedor = idvendedor;
		this.Nombres = nombres;
	}

	public Vendedor() {
	}

	/* Metodos */
	// El spinner muestra lo que retorne este método
	public String toString() {
		return Nombres;
	}

	// getId captura el id del item seleccionado en el spinner
	public int getId() {
		return IdVendedor;
	}

	// Devuelve lista que se usa para llenar el spinner vendedor
	public ArrayList<Vendedor> ConsultarVendedores() {
		ArrayList<Vendedor> lista = new ArrayList<Vendedor>();

		// Estos datos los proporciona el webService
		final String NAMESPACE = "http://tempuri.org/";
		final String URL = "http://10.0.2.2/Visitas/WebServiceVisitas.asmx"; // para
																				// pruebas
																				// en
																				// el
																				// AVD
		// final String
		// URL="http://192.168.1.11/Visitas/WebServiceVisitas.asmx"; //para
		// llevar la apk a produccion
		final String METHOD_NAME = "ConsultarVendedores";
		final String SOAP_ACTION = "http://tempuri.org/ConsultarVendedores";

		// Se instancia un objeto soap pasandole el namespace y el metodo del
		// webservice a consumir
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		// Se instancia un objeto envelope y se define que version de soap se
		// usará (10,11,12)
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true; // indica si el web service fue desarrollado en
								// .NET

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

				lista.add(new Vendedor(Integer.parseInt(ic.getProperty(
						"IdVendedor").toString()), ic.getProperty("Nombres")
						.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}