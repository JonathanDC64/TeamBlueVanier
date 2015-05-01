package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	public void Login(ActionEvent event) throws IOException {
		Parent layout_parent = FXMLLoader.load(getClass().getResource("System.fxml"));
		Scene layout_scene = new Scene(layout_parent);
		Stage layout_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		layout_stage.setScene(layout_scene);
		layout_stage.show();
		layout_stage.setResizable(true);
		Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
		layout_stage.setX((Bounds.getWidth() - layout_stage.getWidth()) / 2); 
		layout_stage.setY((Bounds.getHeight() - layout_stage.getHeight()) / 4);
	}
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
