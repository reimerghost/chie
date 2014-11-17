package com.usac.arqui2.chie.adapters;


public class list_item {
	private String tv;
	private String rb;
	private boolean is;
	public list_item(String TextView, String RatingBar, boolean EsTexto){
		tv = TextView;
		rb = RatingBar;
		is = EsTexto;
	}
	public String getTextView(){
		return tv;
	}
	public String getRatingBar(){
		return rb;
	}
	public boolean esTexto(){
		return is;
	}
	public void setListView(String TextView){
		tv = TextView;
	}
	public void setRatingBar(String RatingBar){
		rb = RatingBar;
	}
	public void setEsTexto(boolean EsTexto){
		is = EsTexto;
	}
	
}
