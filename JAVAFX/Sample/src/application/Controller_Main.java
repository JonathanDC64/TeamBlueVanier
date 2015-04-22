package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class Controller_Main implements Initializable{
	
	LayoutLoader layoutloader = new LayoutLoader();

	public void Login(ActionEvent event) throws IOException {
		//If Home button is clicked		
		layoutloader.loadTabParent(0,event);
	}
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}