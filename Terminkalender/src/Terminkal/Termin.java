package Terminkal;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Termin extends Eintrag {
	
	
	private int dauermin;
	private String beschreibung;
	private String Ansprechperson;
	private String Telefonnr;
	
	
	
	public Termin(String nam, String beschrei, String dat){
		
		name=nam;
		this.beschreibung=beschrei;
		
		String[]	pups=	dat.split("\\.", 3);
		datum.set(Integer.parseInt(pups[2]), Integer.parseInt(pups[1]), Integer.parseInt(pups[0]));
		
	}
	
	
	
	
	
	
	@Override
	 public boolean equals(Object o){
		if(this==o)return true;
		if(o instanceof Termin){
			if(this.datum==((Termin)o).datum){
				return true;
			}
		}
		return false;
		 
	 }
	
	@Override
	public String toString(){
		SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
		
		return "Termin:"+name+" startet am "+sdf.format(datum.getTime()) ;
	}

}
