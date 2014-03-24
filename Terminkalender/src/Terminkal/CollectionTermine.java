package Terminkal;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import Terminkal.JCalender.Day;

public class CollectionTermine {
	
	//view für Calender
	private JCalender kalender;
	
	private HashSet<Eintrag> termine;
	
	public CollectionTermine(){
		kalender=new JCalender();
		termine= new HashSet<Eintrag>();
		deserie();
		
	}
	
	
	
	public JCalender getKalender() {
		return kalender;
	}



	public void setKalender(JCalender kalender) {
		this.kalender = kalender;
	}



	public HashSet<Eintrag> getTermine() {
		return termine;
	}



	public void setTermine(HashSet<Eintrag> termine) {
		this.termine = termine;
	}



	public void drawTermine(){
		Iterator<Eintrag> iter=termine.iterator();
		while (iter.hasNext()){
			Eintrag ein= iter.next();
			JButton tmp=kalender.makeButton(ein);
			
			String[]tempa=ein.datum.split("\\.");
			
			
			
			Calendar date= (Calendar) kalender.calendar.clone();
			
			
			
						
			if ((Integer.parseInt(tempa[1])==(date.MONTH+1))&&
				 ((Integer.parseInt(tempa[2]))==(date.get(Calendar.YEAR)))){
				
				int firstDayOfWeek = date.getFirstDayOfWeek();
				date.set(date.DAY_OF_MONTH, 1);

				int firstDay = date.get(date.DAY_OF_WEEK) - firstDayOfWeek;

				if (firstDay < 0) {
					firstDay += 7;
				}


				
				
				
				int i= Integer.parseInt(tempa[0])+firstDay+6;
				kalender.days[i].add(tmp);
				
				date=(Calendar) kalender.calendar.clone();
				
				
			}
		
			
			
			
		}
		
		
		
		kalender.dayPanel.repaint();
		
	}
	
	
	public boolean serie(){
		
			boolean complete;
		
				//Output
				try{
				//erzeugen des Outputstreams
				ObjectOutputStream os =new ObjectOutputStream(new FileOutputStream("docs/Klaus.ser"));
				//serialisieren
				os.writeObject(termine);
				complete=true;
				os.close();
				}catch (IOException e){
					System.out.println("Fehler beim Serialisieren der termine.");
					e.printStackTrace();
					complete=false;
				}
					return complete;
		
	}
	

	public boolean deserie(){
		boolean complete;
		
				//Input
				try{
					//erzeugen des Inputstreams
				FileInputStream fs = new FileInputStream("docs/klaus.ser");
				ObjectInputStream os = new ObjectInputStream(fs);
				
				//deserialisieren
				termine= (HashSet<Eintrag>)os.readObject();
				os.close();
				complete=true;
				
				}catch (IOException e){
					System.out.println("Fehler beim deserialisieren der termine");
					e.printStackTrace();
					complete=false;

				} catch (ClassNotFoundException cfe) {
					System.out.println("Fehler beim deserialisieren der termine");
					cfe.printStackTrace();
					complete=false;
				}catch (ClassCastException ce){
					System.out.println("Fehler beim deserialisieren der termine");
					ce.printStackTrace();
					complete=false;
				}
				
				return complete;
		
	}
	
	
	
	public boolean hinzufügen(Eintrag ein){
		
		return termine.add(ein);
	}
	
	public Iterator<Eintrag> getIterator(){
		return termine.iterator();
	}
	
	public void testen(){
		
	}
	
	
	

}
