package application.printing;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import Printing.Print;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

public class PrintPreview implements Initializable
{
	@FXML
	private WebView webView;
	@FXML
	private Button printButton;
	
	private WebEngine engine;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		engine = webView.getEngine();
		String path = System.getProperty("user.dir");  
        path.replace("\\\\", "/");  
        path +=  "/report.html";
    	loadURL("file:///" + path);
    	
    	printButton.setOnMouseClicked(
    	new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				try {
					Print.printPage(false);
					printButton.setOnMouseClicked(null);
					((Stage)printButton.getScene().getWindow()).close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});
	}
	
	public void loadURL(final String url) {
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                String tmp = toURL(url);
 
                if (tmp == null) {
                    tmp = toURL("http://" + url);
                }
 
                engine.load(tmp);
            }
        });
    }
	
	private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
                return null;
        }
    }
}
