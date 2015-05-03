package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import dbconnector.database.DBConnector;

public class SystemController implements Initializable{
	DBConnector database;
	
	//Employee
	@FXML
	private AnchorPane employeePane;
	@FXML 
	private TableView<Value> employeeInformationTable;
	@FXML 
	private TableView<Employee> Table_Employee;
	@FXML
	private Button addEmployee;
	@FXML
	private Button editEmployee;
	@FXML
	private Button deleteEmployee;
	@FXML
	private Button printEmployee;
	@FXML
	private TextField employeeSearchField;
	
	
	
	@FXML
	private Slider fontSizeSlider;
	
	//TableView Table_Employee ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
		Platform.runLater(new Runnable()
		{

			@Override
			public void run() {
				
				
				// TODO Auto-generated method stub
				database = new DBConnector();
				
				//Employee
				Platform.runLater(
				new EmployeeController(database, employeeInformationTable,Table_Employee,addEmployee,editEmployee,deleteEmployee,printEmployee,employeeSearchField));
				
				
				@SuppressWarnings("rawtypes")
				final TableView[] tables = {Table_Employee,employeeInformationTable};
				fontSizeSlider.valueProperty().addListener(new ChangeListener<Number>() 
				{
		            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) 
		            {
		            	for(int i = 0 ; i < tables.length ; i++)
		            		tables[i].setStyle("-fx-font-size:" + new_val);
		            }

		        });
			}
			
		});
		

		
	}
}
