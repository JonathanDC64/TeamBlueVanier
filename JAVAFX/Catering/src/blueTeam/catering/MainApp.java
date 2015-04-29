package blueTeam.catering;

import java.io.IOException;

import blueTeam.catering.model.Employee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane loginLayout;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Back of The House Catering");

		initLoginLayout();
	}

	public void initLoginLayout()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginLayout.fxml"));
			loginLayout = (BorderPane) loader.load();

			Scene scene = new Scene(loginLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

	public MainApp()
	{
		// Some random data
		employeeData.add(new Employee("Tung", "Doa"));
		employeeData.add(new Employee("Jonathan", "Del"));
		employeeData.add(new Employee("Karan", "Sin"));
		employeeData.add(new Employee("Julie", "Man"));
		employeeData.add(new Employee("David", "Men"));
	}


	public ObservableList<Employee> getEmployeeData() 
	{
		return employeeData;
	}
}

