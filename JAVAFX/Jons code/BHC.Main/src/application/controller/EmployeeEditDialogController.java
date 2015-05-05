package application.controller;

import java.sql.SQLException;

import dbconnector.database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private void HandleOk() throws SQLException {
    	
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
	    	
	    	String[][] NewLocationID = database.select("select LocationID from Location orderby LocationID desc");
	    	String[][] NewPersonID = database.select("select PersonID from Person orderby PersonID desc");
	    	String[][] NewEmployeeID = database.select("select EmployeeID from Employee orderby EmployeeID desc");
	    	
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
	    	
	    	Stage stage = (Stage) ButtonCancel.getScene().getWindow();
	        stage.close();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Empty Fields");
			alert.showAndWait();
		}
        
        //refreshData();
        
        //Validate input!
    }
}
