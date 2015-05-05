package application.controller;

import java.sql.SQLException;

import dbconnector.database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.database.DatabaseUtils;
import application.datamodel.Employee;

public class EmployeeEditDialogController {
	
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalcodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField provinceField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField homephonenumberField;
    @FXML
    private TextField cellphonenumberField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField salaryField;
    @FXML
    private Button ButtonOK;
    @FXML
    private Button ButtonCancel;
    
    private Employee employee;
    
    DBConnector database = new DBConnector();
    
    public void setEmployee(Employee employee) {
        this.employee = employee;

        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        addressField.setText(employee.getAddress());
        postalcodeField.setText(employee.getPostalCode());
        cityField.setText(employee.getCity());
        provinceField.setText(employee.getProvince());
        emailField.setText(employee.getEmail());
        homephonenumberField.setText(employee.getHomePhoneNumber());
        cellphonenumberField.setText(employee.getCellPhoneNumber());
        positionField.setText(employee.getPosition());
        salaryField.setText(employee.getSalary());//parse
    }
    
    @FXML
    private void HandleCancel() {
    	Stage stage = (Stage) ButtonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void HandleOk() {
    	String[][] NewLocationID = null;
    	String[][] NewPersonID = null;
    	String[][] NewEmployeeID = null;
    	int nextLocationId;
    	int nextPersonId;
    	int nextEmployeeId;
    	
    	try {
    		NewLocationID = database.select("select LocationID from Location orderby LocationID desc");
    		NewPersonID = database.select("select PersonID from Person orderby PersonID desc");
    		NewEmployeeID = database.select("select EmployeeID from Employee orderby EmployeeID desc");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		if(NewLocationID != null){
			nextLocationId = Integer.parseInt(NewLocationID[0][0]) + 1;
		}
		else{
			nextLocationId = 0;
		}
		
		if(NewPersonID != null){
			nextPersonId = Integer.parseInt(NewPersonID[0][0]) + 1;
		}
		else{
			nextPersonId = 0;
		}
		
		if(NewEmployeeID != null){
			nextEmployeeId = Integer.parseInt(NewEmployeeID[0][0]) + 1;
		}
		else{
			nextEmployeeId = 0;
		}
    	
    	String FirstName = firstNameField.getText();
    	String LastName = lastNameField.getText();
    	String Address = addressField.getText();
    	String PostalCode = postalcodeField.getText();
    	String Province = provinceField.getText();
    	String City = cityField.getText();
    	String Email = emailField.getText();
    	String HomePhoneNumber = homephonenumberField.getText();
    	String CellPhoneNumber = cellphonenumberField.getText();
    	String Position = positionField.getText();
    	String Salary = salaryField.getText();
    	
    	DatabaseUtils.insertIntoLocation(database, Integer.toString(nextLocationId),City,Province,Address,PostalCode);
    	DatabaseUtils.insertIntoPerson(database, Integer.toString(nextPersonId),FirstName,LastName,HomePhoneNumber,CellPhoneNumber,Email,Integer.toString(nextLocationId));
    	DatabaseUtils.insertIntoEmployee(database,Integer.toString(nextEmployeeId), Integer.toString(nextPersonId),Position,Salary);
    	
    	Stage stage = (Stage) ButtonCancel.getScene().getWindow();
        stage.close();
        
        //refreshData();
        
        //Validate input!
    }
}
