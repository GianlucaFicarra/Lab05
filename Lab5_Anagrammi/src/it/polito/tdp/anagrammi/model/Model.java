package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.AnagrammiDAO;


public class Model {

	/*mi serve accedere al modello presente in anagrammaDAO dal controller
	 * ma controller parla solo con il model, cosi in model creo oggetto anagrammaDAO
	 * con questo usando le query nella classe DAO interrogo il database*/
	AnagrammiDAO anagrammaDAO =new AnagrammiDAO();
	
	
	//creo funzione che avvia la ricorsione richiamata dal test, e restituisce la soluzione
	public Set<String> cercaAnagrammi(String parola){
		
		//scelgo come struttura dati della soluzione SET: lista di strighe senza ripetizioni
		Set<String> anagrammi = new HashSet<String>();
		String parziale="";
		int livello = 0;
	
		
		//richiamare funzione ricorsiva
		componiParola(parziale, parola, livello, anagrammi);
		
		return anagrammi;
	}

	
	//analizzo la ricorsiva:
	private void componiParola(String parziale, String parola, int livello, Set<String> anagrammi) {
		
		int contParziale=0;
		int contParola=0;
		
		/*A-condizione di terminazione:
		 * anagramma è la combinazione delle lettere per fare nuova parola
		 * quindi la parola parziale è anche completa se la sua dimensione 
		 * è uguale ala dimensione della parola passata*/
		
		if(livello==parola.length()) {
			anagrammi.add(parziale);
			return;
		}
		
		//B-generare soluzione: aggiungo la prima lettera
		
		/*ES CIAO
		 * soluzione= 0;
		 * entro nell if e soluzione=C;
		 * avvio ricorsione esplorando:
		 * CC non è corretta perche in CIAO la C compare solo una volta, scarto
		 * prosegio con le altre combinazioni: CI CA CO ecc..
		 * mentre esploro questo ramo di soluzioni con la ricorsione, 
		 * elimino la casella corrente C per esplorare gli altri casi
		 * tipo parole inizianti per I --> IC IA IO ecc
		 * */
		
		for(int i=0; i<parola.length(); i++) {
			
			 /*   MODO PER EVITARE CHE IN UNA SOLUZIONI UNA LETTERA 
				  COMPAIA PIù VOLTE DI QUANTE NE COMPARIVA NELL ORIGINALE:
			 * presa lettera iesima conto quante volte compare nella parola passata
			 * e quante nella soluz parziale, se ancora il numero di volte è minore rispetto
			 * a quante volte compare nella parola passata, allora la aggiungo, se la lettera compare già lo stesso 
			 * numero di volte non la aggiungo più, e vado avanti con nuova lettera.
			 * questo perchè ad ogni ricorsione ricomincio l'analissi della parola dalla prima lettera*/
			
			contParziale=conteggio(parziale, parola.charAt(i));
			contParola=conteggio(parola, parola.charAt(i));
			
			//C-ricorsiva
			if(contParziale<contParola){
				parziale+=parola.charAt(i);
				
				//se aggiungo la lettera inizio la ricorsione ed aggiungo le altre
				componiParola(parziale, parola, livello+1, anagrammi);
				
				//backtracking, eliminio la ltera appena aggiunta per eplorare le altre possibilità
				parziale=parziale.substring(0, parziale.length()-1);
			} 
			
			
			}
	
	
	}

	
	private int conteggio(String parziale, char C) {
		int cont=0;
		//conto quante volte il char compare nella parola
		for(int i=0; i<parziale.length(); i++) {
			if(parziale.charAt(i)==C)
				cont++;
		}
		return cont;
	}




	/*funzione con cui interrogo il DB tramite la classe dao
	per essere corretto anagramma deve essere contenuto nel dizionario*/
	public boolean isCorrect(String anagramma) {
		return anagrammaDAO.isCorrect(anagramma);
	
	}
	

	
	
}
