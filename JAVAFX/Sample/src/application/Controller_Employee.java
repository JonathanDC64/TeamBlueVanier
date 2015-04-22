package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Controller_Employee implements Initializable{

	LayoutLoader layoutloader = new LayoutLoader();

	public void Home(ActionEvent event) throws IOException {
		//If Home button is clicked		
		layoutloader.loadTabParent(0,event);
	}
	
	public void Employee(ActionEvent event) throws IOException {
		//If employee button is clicked
		layoutloader.loadTabParent(1,event);
	}
	
	public void Contact(ActionEvent event) throws IOException {
		//If employee button is clicked
		layoutloader.loadTabParent(3,event);
	}
	
	public void Profile(ActionEvent event) throws IOException {
		//If Add button is clicked
		//Also for Edit,Delete,Find - However only after the query for the person was successful
		System.out.println("Going to Employee Profile");		
		
		layoutloader.loadLayout("Employee_3.fxml",event);
	}
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
