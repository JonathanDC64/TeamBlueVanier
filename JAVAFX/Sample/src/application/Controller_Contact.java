package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Controller_Contact implements Initializable{
	
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
	
	public void showCustomerProfile(ActionEvent event) throws IOException {
		//If Add button is clicked at Contact Page, or if Customer radio button is clicked during the Add session (Supplier must be visible first tho)
		//Also for Edit,Delete,Find - However only after the query for the person was successful
		System.out.println("Going to Contact Profile");		
		
		layoutloader.loadLayout("Contact_3.fxml",event);
	}
	
	public void showSupplierProfile(ActionEvent event) throws IOException {
		//If Supplier button is clicked during the Add session
		System.out.println("Going to Supplier Profile");		
		
		layoutloader.loadLayout("Contact_4.fxml",event);
	}
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
