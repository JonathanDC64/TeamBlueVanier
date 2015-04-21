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

public class Controller_Homepage implements Initializable{

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
	public void Employee(ActionEvent event) throws IOException {
		System.out.println("Going to Employee");
		Parent employeepage_parent = FXMLLoader.load(getClass().getResource("Employee_1.fxml"));
		Scene employeepage_scene = new Scene(employeepage_parent);
		Stage employeepage_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		employeepage_stage.setScene(employeepage_scene);
		employeepage_stage.show();
		
		//Putting it to the center
		Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
		employeepage_stage.setX((Bounds.getWidth() - employeepage_stage.getWidth()) / 2); 
		employeepage_stage.setY((Bounds.getHeight() - employeepage_stage.getHeight()) / 4);
	}
}
