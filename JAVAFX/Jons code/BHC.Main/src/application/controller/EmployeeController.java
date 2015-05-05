package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Printing.Print;
import application.Main;
import application.Value;
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
		//refreshData();
		AnchorPane root = (AnchorPane)FXMLLoader.load(Main.class.getResource("EmployeeEditDialog.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
	}

	@Override
	public void displayMoreData() 
	{
		try
    	{
	        Employee employee = (Employee) leftTable.getSelectionModel().getSelectedItem();
	        String[] employeeData = database.select(fullSQL + " and employee.EmployeeID=" + employee.getEmployeeID())[0];
	        
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
	public void edit() 
	{
		// TODO Auto-generated method stub
		
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


