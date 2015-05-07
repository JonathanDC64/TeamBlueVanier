package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Printing.Print;
import application.Value;
import application.database.DatabaseUtils;
import application.datamodel.Employee;
import dbconnector.database.DBConnector;

@SuppressWarnings("unused")
public class EmployeeController extends BaseController<Employee> implements Runnable
{
	private TableView<Employee> leftTable; 
	private TableView<Value> rightTable; 
	
	private Button addButton; 
	private Button editButton; 
	
	private Button deleteButton; 
	private Button printButton;
	
	
	public EmployeeController(DBConnector database, TableView<Employee> leftTable,TableView<Value> rightTable, Button addButton, Button editButton, Button deleteButton,Button printButton, TextField searchField) 
	{
		super(
				database,
				fSQL,
				"",
				leftTable,
				new String[]{"Employee ID", "First Name", "Last Name"},
				rightTable,
				addButton,
				"Add Employee",
				editButton,
				"Edit Employee",
				deleteButton,
				"Delete Employee",
				printButton,
				"Print Employee Info",
				searchField);
		this.leftTable = leftTable;
		this.rightTable = rightTable;
		this.addButton = addButton;
		this.editButton = editButton;
		this.deleteButton = deleteButton;
		this.printButton = printButton;
		
		columnNames = new String[]{
				"Employee ID","First Name","Last Name", 
				"Address","Postal Code","City","Province",
				"Email","Home Phone Number","Cell Phone Number",
				"Position","Salary"
				};
	}

	private static String fSQL = 
			"SELECT employee.EmployeeID, Person.FirstName, Person.LastName, Location.Address, "
          + "Location.PostalCode, Location.City, Location.Province, Person.Email, Person.HomePhoneNumber, Person.CellPhoneNumber, employee.Position, employee.Wage " 
          + "FROM Employee, Person, Location "
          + "WHERE Person.PersonID = employee.PersonID and Location.LocationID = Person.LocationID";
	
	
	
	@Override
	public void run() {}

	
	
	@Override
	public ObservableList<Employee> arrayToObservableList(String[][] data) {
		
		
		values.clear();
		if(data == null) return null;
		for(int i = 0 ; i < data.length ; i++)
		{
			
			values.add(new Employee(
					data[i][0],
					data[i][1],
					data[i][2],
					data[i][3],
					data[i][4],
					data[i][5],
					data[i][6],
					data[i][7],
					data[i][8],
					data[i][9],
					data[i][10],
					data[i][11]));
			
		}
		return values;
		
	}

	@Override
	public void add() throws IOException 
	{
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("application/EmployeeEditDialog.fxml"));
        final Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
        
        final TextField firstNameField = (TextField) root.getScene().lookup("#firstNameField");
        final TextField lastNameField = (TextField) root.getScene().lookup("#lastNameField");
        final TextField addressField = (TextField) root.getScene().lookup("#addressField");
        final TextField postalcodeField = (TextField) root.getScene().lookup("#postalcodeField");
        final TextField cityField = (TextField) root.getScene().lookup("#cityField");
    	final TextField provinceField = (TextField) root.getScene().lookup("#provinceField");
    	final TextField emailField = (TextField) root.getScene().lookup("#emailField");
    	final TextField homephonenumberField = (TextField) root.getScene().lookup("#homephonenumberField");
    	final TextField cellphonenumberField = (TextField) root.getScene().lookup("#cellphonenumberField");
    	final TextField positionField = (TextField) root.getScene().lookup("#positionField");
    	final TextField salaryField = (TextField) root.getScene().lookup("#salaryField");
    	
    	Button ButtonOK = new Button ("Ok");
    	Button ButtonCancel = new Button ("Cancel");

    	HBox hbox = new HBox();
    	hbox.getChildren().add(ButtonOK);
    	hbox.getChildren().add(ButtonCancel);
    	hbox.setLayoutX((root.getWidth()/2)-50);
    	hbox.setLayoutY((root.getHeight())-25);
    	
    	root.getChildren().add(hbox);
    	
    	ButtonOK.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(		!cityField.getText().isEmpty()&&
						!provinceField.getText().isEmpty()&&
						!addressField.getText().isEmpty()&&
						!postalcodeField.getText().isEmpty()&&
						
						!firstNameField.getText().isEmpty()&&
						!lastNameField.getText().isEmpty()&&
						!homephonenumberField.getText().isEmpty()&&
						!cellphonenumberField.getText().isEmpty()&&
						!emailField.getText().isEmpty()&&
						
						!positionField.getText().isEmpty()&&
						!salaryField.getText().isEmpty()
					){
						if(cityField.getText().length() > 15){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("City is greater than 15 characters");
							alert.showAndWait();
						}
						if(provinceField.getText().length() > 15){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Province is greater than 15 characters");
							alert.showAndWait();
						}
						if(addressField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Address is greater than 30 characters");
							alert.showAndWait();
						}
						if(postalcodeField.getText().length() > 6){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Postal Code is greater than 6 characters");
							alert.showAndWait();
						}
						if(firstNameField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("First Name is greater than 30 characters");
							alert.showAndWait();
						}
						if(lastNameField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Last Name is greater than 30 characters");
							alert.showAndWait();
						}
						if(homephonenumberField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Home Phone Number is greater than 10 characters");
							alert.showAndWait();
						}
						if(cellphonenumberField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Cell Phone Number is greater than 10 characters");
							alert.showAndWait();
						}
						if(emailField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Email is greater than 30 characters");
							alert.showAndWait();
						}
						if(positionField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Position is greater than 10 characters");
							alert.showAndWait();
						}
						if(salaryField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Salary is greater than 10 characters");
							alert.showAndWait();
						}
						else{
							//Location Table
							String City = cityField.getText();
							String Province = provinceField.getText();
							String Address = addressField.getText();
					    	String PostalCode = postalcodeField.getText();
					    	
					    	//Person Table
					    	String FirstName = firstNameField.getText();
					    	String LastName = lastNameField.getText();
					    	String HomePhoneNumber = homephonenumberField.getText();
					    	String CellPhoneNumber = cellphonenumberField.getText();    	
					    	String Email = emailField.getText();
					
					    	//Employee Table
					    	String Position = positionField.getText();
					    	String Salary = salaryField.getText();
					    	
					    	String[][] NewLocationID = null;
					    	String[][] NewPersonID = null;
					    	String[][] NewEmployeeID = null;
							try {
								NewLocationID = database.select("select LocationID from Location order by LocationID desc");
						    	NewPersonID = database.select("select PersonID from Person order by PersonID desc");
						    	NewEmployeeID = database.select("select EmployeeID from Employee order by EmployeeID desc");
							} catch (SQLException e) {
								e.printStackTrace();
							}
					    	
					    	int nextLocationId = 0;
					    	int nextPersonId = 0;
					    	int nextEmployeeId = 0;
					    	
							if(NewLocationID != null){
								
								nextLocationId = Integer.parseInt(NewLocationID[0][0]) + 1;
							}
							
							if(NewPersonID != null){
								nextPersonId = Integer.parseInt(NewPersonID[0][0]) + 1;
							}
							
							if(NewEmployeeID != null){
								nextEmployeeId = Integer.parseInt(NewEmployeeID[0][0]) + 1;
							}
					    	
					    	DatabaseUtils.insertIntoLocation(database, Integer.toString(nextLocationId),City,Province,Address,PostalCode);
					    	DatabaseUtils.insertIntoPerson(database, Integer.toString(nextPersonId),FirstName,LastName,HomePhoneNumber,CellPhoneNumber,Email,Integer.toString(nextLocationId));
					    	DatabaseUtils.insertIntoEmployee(database,Integer.toString(nextEmployeeId), Integer.toString(nextPersonId),Position,Salary);
					    	
					        stage.close();
					        refreshData();
						}
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Empty Field(s)");
				alert.showAndWait();
			}
		}});
    	
    	ButtonCancel.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				stage.close();
			}
    	});
	}
	
	Employee CurrentlySelectedEmployee;//Made it global
	
	@Override
	public void displayMoreData() 
	{
		try
    	{
	        //Employee employee = (Employee) leftTable.getSelectionModel().getSelectedItem();
	        //String[] employeeData = database.select(fullSQL + " and employee.EmployeeID=" + employee.getEmployeeID())[0];
	        
			CurrentlySelectedEmployee = (Employee) leftTable.getSelectionModel().getSelectedItem();
	        String[] employeeData = database.select(fullSQL + " and employee.EmployeeID=" + CurrentlySelectedEmployee.getEmployeeID())[0];
			
	        rightTable.getItems().clear();
	        rightTable.setItems(getValues(employeeData));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    	}
	}

	@Override
	public void edit() throws IOException 
	{
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("application/EmployeeEditDialog.fxml"));
        final Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
        
        final TextField firstNameField = (TextField) root.getScene().lookup("#firstNameField");
        final TextField lastNameField = (TextField) root.getScene().lookup("#lastNameField");
        final TextField addressField = (TextField) root.getScene().lookup("#addressField");
        final TextField postalcodeField = (TextField) root.getScene().lookup("#postalcodeField");
        final TextField cityField = (TextField) root.getScene().lookup("#cityField");
    	final TextField provinceField = (TextField) root.getScene().lookup("#provinceField");
    	final TextField emailField = (TextField) root.getScene().lookup("#emailField");
    	final TextField homephonenumberField = (TextField) root.getScene().lookup("#homephonenumberField");
    	final TextField cellphonenumberField = (TextField) root.getScene().lookup("#cellphonenumberField");
    	final TextField positionField = (TextField) root.getScene().lookup("#positionField");
    	final TextField salaryField = (TextField) root.getScene().lookup("#salaryField");
    	
    	firstNameField.setText(CurrentlySelectedEmployee.getFirstName());
    	lastNameField.setText(CurrentlySelectedEmployee.getLastName());
    	addressField.setText(CurrentlySelectedEmployee.getAddress());
    	postalcodeField.setText(CurrentlySelectedEmployee.getPostalCode());
    	cityField.setText(CurrentlySelectedEmployee.getCity());
    	provinceField.setText(CurrentlySelectedEmployee.getProvince());
    	emailField.setText(CurrentlySelectedEmployee.getEmail());
    	homephonenumberField.setText(CurrentlySelectedEmployee.getHomePhoneNumber());
    	cellphonenumberField.setText(CurrentlySelectedEmployee.getCellPhoneNumber());
    	positionField.setText(CurrentlySelectedEmployee.getPosition());
    	salaryField.setText(CurrentlySelectedEmployee.getSalary());
    	
    	Button ButtonOK = new Button ("Save");
    	Button ButtonCancel = new Button ("Cancel");

    	HBox hbox = new HBox();
    	hbox.getChildren().add(ButtonOK);
    	hbox.getChildren().add(ButtonCancel);
    	hbox.setLayoutX((root.getWidth()/2)-50);
    	hbox.setLayoutY((root.getHeight())-25);
    	
    	root.getChildren().add(hbox);
    	
    	ButtonOK.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				if(	!cityField.getText().isEmpty()&&
						!provinceField.getText().isEmpty()&&
						!addressField.getText().isEmpty()&&
						!postalcodeField.getText().isEmpty()&&
						
						!firstNameField.getText().isEmpty()&&
						!lastNameField.getText().isEmpty()&&
						!homephonenumberField.getText().isEmpty()&&
						!cellphonenumberField.getText().isEmpty()&&
						!emailField.getText().isEmpty()&&
						
						!positionField.getText().isEmpty()&&
						!salaryField.getText().isEmpty()
					){
						if(cityField.getText().length() > 15){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("City is greater than 15 characters");
							alert.showAndWait();
						}
						if(provinceField.getText().length() > 15){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Province is greater than 15 characters");
							alert.showAndWait();
						}
						if(addressField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Address is greater than 30 characters");
							alert.showAndWait();
						}
						if(postalcodeField.getText().length() > 6){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Postal Code is greater than 6 characters");
							alert.showAndWait();
						}
						if(firstNameField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("First Name is greater than 30 characters");
							alert.showAndWait();
						}
						if(lastNameField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Last Name is greater than 30 characters");
							alert.showAndWait();
						}
						if(homephonenumberField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Home Phone Number is greater than 10 characters");
							alert.showAndWait();
						}
						if(cellphonenumberField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Cell Phone Number is greater than 10 characters");
							alert.showAndWait();
						}
						if(emailField.getText().length() > 30){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Email is greater than 30 characters");
							alert.showAndWait();
						}
						if(positionField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Position is greater than 10 characters");
							alert.showAndWait();
						}
						if(salaryField.getText().length() > 10){
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText("Salary is greater than 10 characters");
							alert.showAndWait();
						}
						else{	
							//Location Table
							String City = cityField.getText();
							String Province = provinceField.getText();
							String Address = addressField.getText();
					    	String PostalCode = postalcodeField.getText();
					    	
					    	//Person Table
					    	String FirstName = firstNameField.getText();
					    	String LastName = lastNameField.getText();
					    	String HomePhoneNumber = homephonenumberField.getText();
					    	String CellPhoneNumber = cellphonenumberField.getText();    	
					    	String Email = emailField.getText();
					
					    	//Employee Table
					    	String Position = positionField.getText();
					    	String Salary = salaryField.getText();
					    	
					    	String[][] NewLocationID = null;
					    	String[][] NewPersonID = null;
							try {
								NewLocationID = database.select("select LocationID from Location order by LocationID desc");
						    	NewPersonID = database.select("select PersonID from Person order by PersonID desc");
							} catch (SQLException e) {
								e.printStackTrace();
							}
					    	
					    	int nextLocationId = 0;
					    	int nextPersonId = 0;
					    	
							if(NewLocationID != null){
								nextLocationId = Integer.parseInt(NewLocationID[0][0] + 1);
							}
							
							if(NewPersonID != null){
								nextPersonId = Integer.parseInt(NewPersonID[0][0] + 1);
							}
	
					    	String CurrentlySelectedEmployeeID = CurrentlySelectedEmployee.getEmployeeID();
							
							try {
								database.delete("Employee", "EmployeeID", CurrentlySelectedEmployee.getEmployeeID());
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
					    	DatabaseUtils.insertIntoLocation(database, Integer.toString(nextLocationId),City,Province,Address,PostalCode);
					    	DatabaseUtils.insertIntoPerson(database, Integer.toString(nextPersonId),FirstName,LastName,HomePhoneNumber,CellPhoneNumber,Email,Integer.toString(nextLocationId));
					    	DatabaseUtils.insertIntoEmployee(database,CurrentlySelectedEmployeeID, Integer.toString(nextPersonId),Position,Salary);
					    	
					        stage.close();
					        refreshData();
						}
			}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Empty Field(s)");
				alert.showAndWait();
			}
		}});
    	
    	ButtonCancel.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				stage.close();
			}
    	});
	}

	@Override
	public void delete()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Are you sure you want to delete this record?");
		//alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne)
		{
			Employee employee = (Employee) leftTable.getSelectionModel().getSelectedItem();
			try {
				database.delete("Employee", "EmployeeID", employee.getEmployeeID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			refreshData();
	        rightTable.getItems().clear();
		} 
		else if (result.get() == buttonTypeTwo) 
		{
		    // ... user chose "Two"
		}
		
	}

	@Override
	public void print() 
	{
		//Print.generateAndPrintReport(searchData, columnNames, "Employee Report", true);
		Parent root;
		try {
			Print.generateReport(searchData, columnNames, "Employee Report");
		    printPreview();

		} catch (IOException e) {
		    e.printStackTrace();
		}	
	}
}


