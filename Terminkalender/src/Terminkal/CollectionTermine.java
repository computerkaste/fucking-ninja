package Terminkal;

import java.io.*;
import java.util.*;

public class CollectionTermine {
	
	private HashSet<Eintrag> termine;
	
	public CollectionTermine(){
		termine= new HashSet<Eintrag>();
		deserie();
		
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
