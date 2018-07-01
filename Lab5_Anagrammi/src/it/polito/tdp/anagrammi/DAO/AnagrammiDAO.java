package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*DAO tipicamente è no per ogni tabella, qui è una sola quindi anagramma
 * sta volta rispetto alla 4 esercitazione, non ho due classi dao javabean (tipo corso e studente),
 * in pratica la connessione avviene una sola volta, quindi faccio tutto direttaente qui in modo compatto,
 */


public class AnagrammiDAO {

	//controllo se anagramma esiste nel dizionario
	public boolean isCorrect(String anagramma) {
	
	//SQL permette di verificare se l’anagramma calcolato è presente nel dizionario.
	final String sql="SELECT nome " + 
			"FROM parola " + 
			"WHERE nome= ?";	
		
		
	//per implementare iscorrect mi collego al database		
	 final String jdbcUrl = "jdbc:mysql://localhost/dizionario?user=root&password=gualtieri95";
		
	//provo a collegarmi	
		 try {
			Connection conn= DriverManager.getConnection(jdbcUrl);
			
			//se collegato esegui SQL
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet res=st.executeQuery();
			
			//parte che varia da caso a caso
			if(res.next())//se risultato ha elementi
				return true;
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	 
	return false;
	}

	

}
