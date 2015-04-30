package application;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;





import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SystemController implements Initializable{
	@FXML 
	private AnchorPane employeePane;
	
	TableView Table_Employee ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(employeePane.getScene() == null);
				Table_Employee = (TableView) employeePane
						.getScene()
						.lookup("#employee_table");
				Table_Employee.setEditable(true);
				
				
				
				TableColumn<Employee, Integer> Column_ID = new TableColumn<Employee, Integer>("ID");
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
				
				/*//Address column
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
				Column_Salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));*/

				
				Table_Employee.setItems(getEmployees());
				Table_Employee.getColumns().addAll(Column_ID,Column_FirstName,Column_LastName/*,Column_Address,Column_PostalCode,Column_Province,Column_Email,Column_HomePhoneNumber,Column_CellPhoneNumber,Column_Position,Column_Salary*/);
				ScrollPane employeeScrollPane = (ScrollPane) employeePane.getScene().lookup("#employee_scrollpane");
				final AnchorPane employeeInformation = (AnchorPane) employeePane.getScene().lookup("#employee_information");
				Table_Employee.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
				    @Override
				    public void handle(MouseEvent mouseEvent)
				    {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
				        {
				        	employeeInformation.getChildren().remove(0, employeeInformation.getChildren().size());
				            
				            Employee employee = (Employee) Table_Employee.getSelectionModel().getSelectedItem();
				            
				            String[][] data = {
				            		{"Employee ID",Integer.toString(employee.getID())},
				            		{"First Name",employee.getFirstName()},
				            		{"Last Name",employee.getLastName()},
				            		{"Address",employee.getAddress()},
				            		{"Postal Code", employee.getPostalCode()},
				            		{"Province",employee.getProvince()},
				            		{"Email",employee.getEmail()},
				            		{"Home Phone Number",employee.getHomePhoneNumber()},
				            		{"Cell Phone Number",employee.getCellPhoneNumber()},
				            		{"Position",employee.getPosition()},
				            		{"Salary",Double.toString(employee.getSalary())}
				            		
				            };
				            
				            TableView Employee_Table_Info = new TableView();
				            TableColumn Column_Field_Name = new TableColumn("Field Name");
				            Column_Field_Name.setMinWidth(employeeInformation.getWidth()/2);
				            Column_Field_Name.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("FieldName"));
							
							//FirstName column
							TableColumn Column_Value = new TableColumn("Value");
							Column_Value.setMinWidth(employeeInformation.getWidth()/2);
							Column_Value.setCellValueFactory(new PropertyValueFactory<Employee, String>("Value"));
							
							Employee_Table_Info.getColumns().addAll(Column_Field_Name,Column_Value);
							Employee_Table_Info.setItems(getEmployeeValues(employee));
							employeeInformation.getChildren().add(Employee_Table_Info);
							
				           /* VBox v1 = new VBox();
				            VBox v2 = new VBox();
				            for(int i = 0 ;i < data.length ; i++)
				            {
				            	Label l1 = new Label();
				            	//l1.setStyle("-fx-pref-width: 100px;-fx-border-color:red; -fx-background-color: blue;");
				            	l1.setFont(new Font("Arial", 14));
				            	l1.setText(data[i][0] + ": ");
				            	v1.getChildren().add(l1);
				            	v1.getChildren().add(new Label());
				            	
				            	Label l2 = new Label();
				            	l2.setFont(new Font("Arial", 14));
				            	l2.setText(data[i][1]);
				            	v2.getChildren().add(l2);
				            	v2.getChildren().add(new Label());
				            }
				            HBox h = new HBox();
				            h.getChildren().add(v1);
				            h.getChildren().add(v2);
				            employeeInformation.getChildren().add(h);*/
				            
				             //System.out.println(Table_Employee.getSelectionModel().getSelectedItem());
				            
				        }
				    }
				});
			}
			
		});

		
	}
	
	
	public ObservableList<Value> getEmployeeValues(Employee employee)
	{
		ObservableList<Value> value = FXCollections.observableArrayList();
		value.add(new Value("Employee ID",Integer.toString(employee.getID())));
		value.add(new Value("First Name",employee.getFirstName()));
		value.add(new Value("Last Name",employee.getLastName()));
		value.add(new Value("Address",employee.getAddress()));
		value.add(new Value("Postal Code", employee.getPostalCode()));
		value.add(new Value("Province",employee.getProvince()));
		value.add(new Value("Email",employee.getEmail()));
		value.add(new Value("Home Phone Number",employee.getHomePhoneNumber()));
		value.add(new Value("Cell Phone Number",employee.getCellPhoneNumber()));
		value.add(new Value("Position",employee.getPosition()));
		value.add(new Value("Salary",Double.toString(employee.getSalary())));
		return value;
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
