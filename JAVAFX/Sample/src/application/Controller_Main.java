package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller_Main implements Initializable{

	/*
	 * Method requested by eclipse in order to compile
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	/*
	 * Method that loads new UI and centers it
	 * 
	 */
	public void Login(ActionEvent event) throws IOException {
		System.out.println("Going to Homepage");
		Parent homepage_parent = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
		Scene homepage_scene = new Scene(homepage_parent);
		Stage homepage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		homepage_stage.setScene(homepage_scene);
		homepage_stage.show();
		
		//Putting it to the center
		Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
		homepage_stage.setX((Bounds.getWidth() - homepage_stage.getWidth()) / 2); 
		homepage_stage.setY((Bounds.getHeight() - homepage_stage.getHeight()) / 4);
	}
}
