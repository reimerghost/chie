package com.usac.arqui2.chie.adapters;


public class list_item {
	private String tv;
	private String rb;
	public list_item(String TextView, String RatingBar){
		tv = TextView;
		rb = RatingBar;
	}
	public String getTextView(){
		return tv;
	}
	public String getRatingBar(){
		return rb;
	}
	public void setListView(String TextView){
		tv = TextView;
	}
	public void setRatingBar(String RatingBar){
		rb = RatingBar;
	}
	
}
