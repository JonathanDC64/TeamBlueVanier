package application;
	


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application implements Initializable{
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,280,439);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Back of house Catering");
			primaryStage.setResizable(false);
			primaryStage.show();
			
			//Putting it to the center
			Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((Bounds.getWidth() - primaryStage.getWidth()) / 2); 
			primaryStage.setY((Bounds.getHeight() - primaryStage.getHeight()) / 4);
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
