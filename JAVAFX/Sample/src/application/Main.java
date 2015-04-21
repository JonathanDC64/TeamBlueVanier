package application;
	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;


public class Main extends Application {
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
