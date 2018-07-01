package it.polito.tdp.anagrammi.model;

import java.util.List;
import java.util.Set;
	
public class TestModel {
	
		public static void main(String[] args) {
			
			String parola= "ape";
			Model model= new Model();
			
			Set<String> anagrammi= model.cercaAnagrammi(parola);
			
			for(String s : anagrammi) {
				System.out.println(s);
			}

		}

}
