package Terminkal;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Termin implements Serializable {
	
	private GregorianCalendar datum = new GregorianCalendar();
	private GregorianCalendar ende = new GregorianCalendar();
	private String name;
	private int dauer;
	private String beschreibung;
	private String Ansprechperson;
	private String Telefonnr;
	
	

}
