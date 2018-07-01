package it.polito.tdp.anagrammi.controller;

/*controller prende valore inserito da input,
 * e lo passa a model fungendo da test
 * 
 * */

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnagrammiController {

	private Model model; //variabile con riferimento al modello
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="bAnagramma"
    private Button bAnagramma; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorretti"
    private TextArea txtCorretti; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrati"
    private TextField txtErrati; // Value injected by FXMLLoader

    @FXML // fx:id="breset"
    private Button breset; // Value injected by FXMLLoader


	public void setModel(Model model) {
		this.model=model;
		
	}
    
    @FXML
    void doCalcolaAnarammi(ActionEvent event) {//funzione che invoca la ricorsiva
    	
    	txtCorretti.clear();
    	txtErrati.clear();
    	
    	//lavoro con i set perchè è una struttura dati che non ha duplicati, equivale a list
    	String parola=txtParola.getText();
    	Set<String> listaAnagrammi=model.cercaAnagrammi(parola);
    	
    	//lista set riemita dalla ricorsiva, vedo quali hanno significato nel dizionario
    	for(String s: listaAnagrammi) {
    		
    		//controller chiama model che chiama dao per verificare se parola sia presente nel DB
    		if(model.isCorrect(s)) 
    		    txtCorretti.appendText(s+"\n");
    		else
    			txtErrati.appendText(s+"\n");
    	}
    	
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtErrati.clear();
    	txtCorretti.clear();
    	txtParola.clear();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert bAnagramma != null : "fx:id=\"bAnagramma\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert breset != null : "fx:id=\"breset\" was not injected: check your FXML file 'Anagrammi.fxml'.";

    }

}


