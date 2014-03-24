package Terminkal;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JFrame;

public class TerminController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		CollectionTermine terminplaner= new CollectionTermine();
		
		terminplaner.hinzufügen(new Termin("Peter", "18.03.2014"));
		terminplaner.hinzufügen(new Termin("Steffie", "18.03.2014"));
		terminplaner.hinzufügen(new Geburtstag("Gottfried", "18.03.2014"));
		terminplaner.hinzufügen(new Termin("Petra", "18.03.2014"));
		
		
		terminplaner.serie();
		
		Iterator<Eintrag> iter=terminplaner.getIterator();
		while (iter.hasNext()){
			System.out.println(iter.next().toString());
		}
		
		
		
		
		
		
		JFrame frame = new JFrame("Terminkalender by computerkaste");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,480);		
		frame.getContentPane().add(terminplaner.getKalender(),BorderLayout.CENTER);
		//frame.pack();
		frame.setVisible(true);
		
		terminplaner.drawTermine();
		
		
		

	}
	
	
	

}
