package it.polito.tdp.anagrammi.controller;


import it.polito.tdp.anagrammi.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Anagrammi.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
		
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						

			//main prima di far partire scena deve creare istanza del modello
			Model model=new Model();
			//modello creato dal main e passato in gestione al controller
			((AnagrammiController)loader.getController()).setModel(model);
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
