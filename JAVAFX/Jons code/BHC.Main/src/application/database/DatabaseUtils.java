package application.database;

import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import dbconnector.database.DBConnector;

public class DatabaseUtils 
{
	public static void insertIntoLocation(DBConnector database, String... values)
	{
		insert(database, "Location", new String[]{"LocationID", "City", "Province", "Address","PostalCode"}, values);
	}
	
	public static void insertIntoPerson(DBConnector database, String... values)
	{
		insert(database, "Person", new String[]{"PersonID", "FirstName", "LastName", "HomePhoneNumber","CellPhoneNumber","Email","LocationID"}, values);
	}
	
	public static void insertIntoEmployee(DBConnector database, String... values)
	{
		insert(database, "Employee", new String[]{"EmployeeID", "PersonID", "Position", "Wage"}, values);
	}
	
	private static void insert(DBConnector database, String tableName, String[] tableColumns, String[] values)
	{
		try
		{
			database.insert(tableName, values, tableColumns);
		}
		catch(SQLException e)
		{
			System.out.println(2314214);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("There was an error while adding");
			alert.setContentText("The record might already exist");

			alert.showAndWait();
		}
		
	}
}
