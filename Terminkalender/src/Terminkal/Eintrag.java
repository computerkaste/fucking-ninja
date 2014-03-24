package Terminkal;

import java.io.Serializable;
import java.util.*;

public class Eintrag implements Serializable{
	
	protected String name;
	protected String datum;
	
	
	
	@Override
	public int hashCode(){
		
		return name.hashCode() +datum.hashCode();
	}
	
	@Override
	 public boolean equals(Object o){
		if(this==o)return true;
		
		if(o instanceof Eintrag){
			if(o!=null){
				if((this.name.equals(((Eintrag)o).name))&&(this.datum.equals(((Eintrag)o).datum))){
					return true;
			}}
		}
		return false;
		 
	 }
	

}
