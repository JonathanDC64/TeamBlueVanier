package application;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.BaseController;
import application.controller.CustomerController;
import application.controller.EmployeeController;
import application.datamodel.Employee;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dbconnector.database.DBConnector;

public class SystemController implements Initializable{
	DBConnector database;
	
	//@FXML
	//public static AnchorPane employeePane;
	@FXML 
	private TableView<Value> rightTable;
	@SuppressWarnings("rawtypes")
	@FXML 
	private TableView leftTable;
	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button printButton;
	@FXML
	private TextField searchField;
	
	@FXML
	private TabPane tabPane;
	
	
	
	@FXML
	private Slider fontSizeSlider;
	
	
	BaseController currentTab;
	
	//TableView Table_Employee ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
		Platform.runLater(new Runnable()
		{

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run() {
				
				
				// TODO Auto-generated method stub
				database = new DBConnector();
				
				TableColumn Column_Field_Name = new TableColumn("Field Name");
		        //Column_Field_Name.setMinWidth(Math.floor(employeeInformationTable.getWidth()/2.0)-1);
		        Column_Field_Name.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("FieldName"));
		        Column_Field_Name.setStyle("-fx-font-weight	: bold");
		        
		        
				//FirstName column
				TableColumn Column_Value = new TableColumn("Value");
				//Column_Value.setMinWidth(Math.floor(employeeInformationTable.getWidth()/2.0)-1);
				Column_Value.setCellValueFactory(new PropertyValueFactory<Employee, String>("Value"));
				
				rightTable.getColumns().addAll(Column_Field_Name,Column_Value);
				rightTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
				
				final BaseController employeeController = new EmployeeController(database, leftTable, rightTable, addButton, editButton, deleteButton, printButton,searchField);
				final BaseController customerController = new CustomerController(database, leftTable, rightTable, addButton, editButton, deleteButton, printButton,searchField);
				
				
				
				currentTab = employeeController;
				currentTab.initialize();
				currentTab.setupListeners();
				tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
					
					
					int employeeTab = 0, billingTab = 1, customerTab = 2,
						supplierTab = 3,	eventTab = 4,inventoryTab = 5;
					ObservableList<Tab> tabs = tabPane.getTabs();
		            @Override
		            public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab selectedTab) 
		            {
		            	
		            	//System.out.println("size before: \t\t" + Runtime.getRuntime().freeMemory());
		            	leftTable.getItems().clear();
		            	leftTable.getColumns().clear();
		            	rightTable.getItems().clear();
		            	currentTab.removeListeners();
		            	
		            	System.gc();
		            	//System.out.println("size middle: \t\t" + Runtime.getRuntime().freeMemory());
		            	
	                    if(selectedTab == tabPane.getTabs().get(employeeTab)) 
	                    {
	                    	currentTab = employeeController;
	                    	currentTab.initialize();
	                    	currentTab.setupListeners();
	                    }
	                    else if(selectedTab == tabPane.getTabs().get(billingTab)) 
	                    {
	                    	System.out.println(billingTab);
	                    	
	                    }
	                    else if(selectedTab == tabPane.getTabs().get(customerTab)) 
	                    {
	                    	currentTab = customerController;
	                    	currentTab.initialize();
	                    	currentTab.setupListeners();
	                    }
	                    else if(selectedTab == tabPane.getTabs().get(supplierTab)) 
	                    {
	                    	System.out.println(supplierTab);
	                    }
	                    else if(selectedTab == tabPane.getTabs().get(eventTab)) 
	                    {
	                    	System.out.println(eventTab);
	                    }
	                    else if(selectedTab == tabPane.getTabs().get(inventoryTab)) 
	                    {
	                    	System.out.println(inventoryTab);
	                    }
	                   
	                    
	                    //System.out.println("size after:  \t\t" + Runtime.getRuntime().freeMemory());
	                   // Platform.runLater((Runnable)currentTab);
			        }
				});
				
				
				
				
				
				
				
				
				@SuppressWarnings("rawtypes")
				final TableView[] tables = {leftTable,rightTable};
				fontSizeSlider.valueProperty().addListener( new  ChangeListener<Number>() 
				{
		            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) 
		            {
		            	for(int i = 0 ; i < tables.length ; i++)
		            	{
		            		
		            		tables[i].setStyle("-fx-font-size:" + new_val);
		            	}
		            }

		        });
			}
			
		});
		

		
	}
}
