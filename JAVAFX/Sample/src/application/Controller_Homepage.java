package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Controller_Homepage implements Initializable{
	
	LayoutLoader layoutloader = new LayoutLoader();

	public void Employee(ActionEvent event) throws IOException {
		//If Employee button is clicked
		layoutloader.loadTabParent(1,event);
	}
	
	public void Contact(ActionEvent event) throws IOException {
		//If Contact button is clicked
		layoutloader.loadTabParent(3,event);
	}
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}
