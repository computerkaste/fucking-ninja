package Terminkal;


public class Termin extends Eintrag {
	
	
	private int dauermin;
	private String beschreibung;
	private String Ansprechperson;
	private String Telefonnr;
	
	public Termin(String nam,  String dat){
		
		name=nam;
		
		datum=dat;
		
	}
	
	
	
	public Termin(String nam, String beschrei, String dat){
		
		name=nam;
		this.beschreibung=beschrei;
		datum=dat;
		
	}
	
	
	
	

	
	
	
	
	@Override
	public String toString(){
				
		return "Am "+datum+ "haben Sie den Termin:"+name;
	}

}
