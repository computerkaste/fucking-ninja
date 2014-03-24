package Terminkal;



public class Geburtstag extends Eintrag {

	private int alter;
	
	public Geburtstag(String nam, String dat){
		
		name=nam;
		datum=dat;
		
		
	}
	
	
	
	
	public String toString(){
		
		
		return "Am "+datum + "hat "+name+" Geburtstag.";
	}
	
	
	
}
