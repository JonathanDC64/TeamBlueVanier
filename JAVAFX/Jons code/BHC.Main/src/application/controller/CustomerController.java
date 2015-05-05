package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import application.Value;
import application.datamodel.Customer;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;
import Printing.Print;
import dbconnector.database.DBConnector;

public class CustomerController extends BaseController<Customer> implements Runnable
{
	private TableView<Customer> leftTable; 
	private TableView<Value> rightTable; 
	private Button addButton; 
	private Button editButton; 
	private Button deleteButton; 
	private Button printButton;
	
	
	public CustomerController(DBConnector database, TableView<Customer> leftTable,TableView<Value> rightTable, Button addButton, Button editButton, Button deleteButton,Button printButton, TextField searchField) 
	{
		super(
				database,
				fSQL,
				"",
				leftTable,
				new String[]{"Customer ID", "First Name", "Last Name"},
				rightTable,
				addButton,
				"Add Customer",
				editButton,
				"Edit Customer",
				deleteButton,
				"Delete Customer",
				printButton,
				"Print Customer Info",
				searchField);
		this.leftTable = leftTable;
		this.rightTable = rightTable;
		this.addButton = addButton;
		this.editButton = editButton;
		this.deleteButton = deleteButton;
		this.printButton = printButton;
		
		columnNames = new String[]{
				"Customer ID","First Name","Last Name", 
				"Address","Postal Code", "City","Province",
				"Email","Home Phone Number","Cell Phone Number"
				};
	}

	private static String fSQL = 
			"SELECT Customer.CustomerID, Person.FirstName, Person.LastName, Location.Address, "
          + "Location.PostalCode, Location.City, Location.Province, Person.Email, Person.HomePhoneNumber, Person.CellPhoneNumber " 
          + "FROM Customer, Person, Location "
          + "WHERE Person.PersonID = Customer.PersonID and Location.LocationID = Person.LocationID";
	
	
	
	@Override
	public void run() {}

	@Override
	public ObservableList<Customer> arrayToObservableList(String[][] data) {
		if(data == null) return null;
		for(int i = 0 ; i < data.length ; i++)
		{
			
			values.add(new Customer(
					data[i][0],
					data[i][1],
					data[i][2],
					data[i][3],
					data[i][4],
					data[i][5],
					data[i][6],
					data[i][7],
					data[i][8],
					data[i][9]));
			
		}
		return values;
		
	}

	@Override
	public void add() 
	{
		
	}

	@Override
	public void displayMoreData() 
	{
		try
    	{
			Customer customer = (Customer) leftTable.getSelectionModel().getSelectedItem();
	        String[] customerData = database.select(fullSQL + " and customer.CustomerID=" + customer.getCustomerID())[0];
	        
	        
	        rightTable.setItems(getValues(customerData));
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
			Customer customer = (Customer) leftTable.getSelectionModel().getSelectedItem();
			try {
				database.delete("Customer", "CustomerID", customer.getCustomerID());
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
		Parent root;
		try {
			Print.generateReport(searchData, columnNames, "Customer Report");
		    printPreview();

		} catch (IOException e) {
		    e.printStackTrace();
		}
	}	
}


