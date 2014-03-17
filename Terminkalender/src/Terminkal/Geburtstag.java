package Terminkal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


public class Geburtstag extends Eintrag {

	private int alter;
	
	public Geburtstag(String nam, String dat){
		
		name=nam;
		
		String[]	pups=	dat.split("\\.", 3);
		datum.set(Integer.parseInt(pups[2]), Integer.parseInt(pups[1]), Integer.parseInt(pups[0]));
		
	}
	
	
	public String toString(){
		SimpleDateFormat sdf= new SimpleDateFormat("dd.MM");
		
		return "Geburtstag:"+name+" hat am "+sdf.format(datum.getTime()) ;
	}
	
}
