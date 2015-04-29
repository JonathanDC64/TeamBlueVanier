package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	Stage window;
	TableView <Employee> Table_Employee;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Window Title");
		
		//ID column
		TableColumn <Employee, Integer> Column_ID = new TableColumn<>("ID");
		Column_ID.setMinWidth(50);
		Column_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
		
		//FirstName column
		TableColumn <Employee, String> Column_FirstName = new TableColumn<>("First Name");
		Column_FirstName.setMinWidth(75);
		Column_FirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
		
		//LastName column
		TableColumn <Employee, String> Column_LastName = new TableColumn<>("Last Name");
		Column_LastName.setMinWidth(75);
		Column_LastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName"));
		
		//Address column
		TableColumn <Employee, String> Column_Address = new TableColumn<>("Address");
		Column_Address.setMinWidth(75);
		Column_Address.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
		
		//PostalCode column
		TableColumn <Employee, String> Column_PostalCode = new TableColumn<>("PostalCode");
		Column_PostalCode.setMinWidth(75);
		Column_PostalCode.setCellValueFactory(new PropertyValueFactory<Employee, String>("PostalCode"));

		//Province column
		TableColumn <Employee, String> Column_Province = new TableColumn<>("Province");
		Column_Province.setMinWidth(75);
		Column_Province.setCellValueFactory(new PropertyValueFactory<Employee, String>("Province"));
		
		//Email column
		TableColumn <Employee, String> Column_Email = new TableColumn<>("Email");
		Column_Email.setMinWidth(75);
		Column_Email.setCellValueFactory(new PropertyValueFactory<Employee, String>("Email"));
		
		//HomePhoneNumber column
		TableColumn <Employee, String> Column_HomePhoneNumber = new TableColumn<>("Home #");
		Column_HomePhoneNumber.setMinWidth(75);
		Column_HomePhoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("HomePhoneNumber"));
		
		//CellPhoneNumber column
		TableColumn <Employee, String> Column_CellPhoneNumber = new TableColumn<>("Cell #");
		Column_CellPhoneNumber.setMinWidth(75);
		Column_CellPhoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("CellPhoneNumber"));
		
		//Position column
		TableColumn <Employee, String> Column_Position = new TableColumn<>("Position");
		Column_Position.setMinWidth(75);
		Column_Position.setCellValueFactory(new PropertyValueFactory<Employee, String>("Position"));
		
		//Salary column
		TableColumn <Employee, Integer> Column_Salary = new TableColumn<>("Salary");
		Column_Salary.setMinWidth(75);
		Column_Salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));

		Table_Employee = new TableView<>();
		Table_Employee.setItems(getEmployees());
		Table_Employee.getColumns().addAll(Column_ID,Column_FirstName,Column_LastName,Column_Address,Column_PostalCode,Column_Province,Column_Email,Column_HomePhoneNumber,Column_CellPhoneNumber,Column_Position,Column_Salary);
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.getChildren().addAll(Table_Employee);
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.show();
	}
	
	public ObservableList<Employee> getEmployees(){
		ObservableList<Employee> employees = FXCollections.observableArrayList();
		employees.add(new Employee(1, "Tom", "Jones", "124 ThatRoad", "ABC123", "Quebec", "Email@Email.com", "514-123-4568", "514-135-4566", "Chef", 15.80));
		employees.add(new Employee(2, "Alex", "Jones", "124 ThatRoad", "ABC123", "Quebec", "Email@Email.com", "514-123-4568", "514-135-4566", "Chef", 15.80));
		employees.add(new Employee(3, "Joe", "Jones", "124 ThatRoad", "ABC123", "Quebec", "Email@Email.com", "514-123-4568", "514-135-4566", "Chef", 15.80));
		employees.add(new Employee(4, "Jeff", "Jones", "124 ThatRoad", "ABC123", "Quebec", "Email@Email.com", "514-123-4568", "514-135-4566", "Chef", 15.80));
		employees.add(new Employee(5, "Charles", "Jones", "124 ThatRoad", "ABC123", "Quebec", "Email@Email.com", "514-123-4568", "514-135-4566", "Chef", 15.80));
		return employees;
	}
	
	
}
