package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LayoutLoader {
	
	/*
	 * Method that loads new UI and centers it
	 * 
	 */	
	
	public void loadLayout(String Layout,ActionEvent event) throws IOException {
		Parent layout_parent = FXMLLoader.load(getClass().getResource(Layout));
		Scene layout_scene = new Scene(layout_parent);
		Stage layout_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		layout_stage.setScene(layout_scene);
		layout_stage.show();
		
		Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
		layout_stage.setX((Bounds.getWidth() - layout_stage.getWidth()) / 2); 
		layout_stage.setY((Bounds.getHeight() - layout_stage.getHeight()) / 4);
	}
	
	/*
	 * The 5 Top layouts used by all sub layouts
	 */
	
	public void loadTabParent(int tab,ActionEvent event) throws IOException{
		switch(tab){
		case 0:
			System.out.println("Going to Homepage");
			loadLayout("Homepage.fxml",event);
			break;
		case 1:
			System.out.println("Going to Employee");
			loadLayout("Employee_1.fxml",event);
			break;
		case 2:
			System.out.println("Going to Billing");
			loadLayout(".fxml",event);
			break;
		case 3:
			System.out.println("Going to Contact");
			loadLayout("Contact_1.fxml",event);
			break;
		case 4:
			System.out.println("Going to Event");
			loadLayout("Events_3.fxml",event);
			break;
		case 5:
			System.out.println("Going to Inventory");
			loadLayout(".fxml",event);
			break;
		}
	}
}
