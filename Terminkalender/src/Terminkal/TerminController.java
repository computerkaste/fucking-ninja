package Terminkal;

import java.util.Iterator;

public class TerminController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		CollectionTermine terminplaner= new CollectionTermine();
		
		Termin amt= new Termin("amt", "Pass auf dem Amt erneuern lassen", "17.03.2014");
		Termin gerd= amt;
		Termin petra= new Termin("Petra","mit ihr Kaffee trinken","17.03.2014" );
		Geburtstag peta= new Geburtstag("petra", "18.04.1991");
		
		System.out.println(terminplaner.hinzuf�gen(amt));
		System.out.println(terminplaner.hinzuf�gen(gerd));
		System.out.println(terminplaner.hinzuf�gen(petra));
		System.out.println(terminplaner.hinzuf�gen(peta));
		System.out.println("collection hinzugef�gt");
		
		terminplaner.serie();
		
		Iterator<Eintrag> iter=terminplaner.getIterator();
		while (iter.hasNext()){
			System.out.println(iter.next().toString());
		}
		

	}
	
	
	

}
