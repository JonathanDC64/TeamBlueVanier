package application;

import java.awt.Container;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import Printing.Print;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import dbconnector.database.DBConnector;

public class EmployeeController implements Runnable
{

	private DBConnector database;
	private TableView<Value> employeeInformationTable;
	private TableView<Employee> Table_Employee;
	private Button addEmployee;
	private Button editEmployee;
	private Button deleteEmployee;
	private Button printEmployee;
	private TextField employeeSearchField;
	
	String fullSQL = 
			"SELECT employee.EmployeeID, Person.FirstName, Person.LastName, Location.Address, "
            + "Location.PostalCode, Location.Province, Person.Email, Person.HomePhoneNumber, Person.CellPhoneNumber, employee.Position, employee.Wage " 
            + "FROM Employee, Person, Location "
            + "WHERE Person.PersonID = employee.PersonID and Location.LocationID = Person.LocationID";
	
	String employeeData[][];
	String searchData[][];
	
	public EmployeeController(DBConnector database,
			TableView<Value> employeeInformationTable,
			TableView<Employee> table_Employee, Button addEmployee,
			Button editEmployee, Button deleteEmployee, Button printEmployee,
			TextField employeeSearchField) {
		super();
		this.database = database;
		this.employeeInformationTable = employeeInformationTable;
		Table_Employee = table_Employee;
		this.addEmployee = addEmployee;
		this.editEmployee = editEmployee;
		this.deleteEmployee = deleteEmployee;
		this.printEmployee = printEmployee;
		this.employeeSearchField = employeeSearchField;
	}

	

	
	
	@Override
	public void run() 
	{
		TableColumn<Employee, Integer> Column_ID = new TableColumn<Employee, Integer>("ID");
		//Column_ID.setMinWidth(Math.floor(Table_Employee.getWidth()/3.0));
		Column_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
		
		//FirstName column
		TableColumn <Employee, String> Column_FirstName = new TableColumn<>("First Name");
		//Column_FirstName.setMinWidth(Math.floor(Table_Employee.getWidth()/3.0));
		Column_FirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
		
		//LastName column
		TableColumn <Employee, String> Column_LastName = new TableColumn<>("Last Name");
		//Column_LastName.setMinWidth(Math.floor(Table_Employee.getWidth()/3.0));
		Column_LastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName"));

		
		
		/*String[][] employeeTableData = database.select(		"SELECT Employee.EmployeeID, Person.FirstName, Person.LastName "
														+ 	"FROM Employee, Person where Person.PersonID = Employee.PersonID;");
		ObservableList<Employee> values = FXCollections.observableArrayList();
		for(int i = 0 ; i < employeeTableData.length ; i++)
		{
			values.add(new Employee(Integer.parseInt(employeeTableData[i][0]),employeeTableData[i][1],employeeTableData[i][2],null,null,null,null,null,null,null,0.0));
		}
		*/
		
		Table_Employee.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
		//Table_Employee.setItems(values);
		
		
		
		refreshData();//SETS THE ITEMS
		searchData = employeeData;
		
		Table_Employee.getColumns().addAll(Column_ID,Column_FirstName,Column_LastName/*,Column_Address,Column_PostalCode,Column_Province,Column_Email,Column_HomePhoneNumber,Column_CellPhoneNumber,Column_Position,Column_Salary*/);

        TableColumn Column_Field_Name = new TableColumn("Field Name");
        //Column_Field_Name.setMinWidth(Math.floor(employeeInformationTable.getWidth()/2.0)-1);
        Column_Field_Name.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("FieldName"));
		
		//FirstName column
		TableColumn Column_Value = new TableColumn("Value");
		//Column_Value.setMinWidth(Math.floor(employeeInformationTable.getWidth()/2.0)-1);
		Column_Value.setCellValueFactory(new PropertyValueFactory<Employee, String>("Value"));
		
		employeeInformationTable.getColumns().addAll(Column_Field_Name,Column_Value);
		employeeInformationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
		Table_Employee.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
		        {
		        	try
		        	{
		            Employee employee = (Employee) Table_Employee.getSelectionModel().getSelectedItem();
		            String[] employeeData = database.select(fullSQL + " and employee.EmployeeID=" + employee.getID())[0];
		            
		            
		            employeeInformationTable.setItems(getEmployeeValues(employeeData));
		        	}
		        	catch(Exception e)
		        	{
		        		System.out.println(e.getMessage());
		        	}
		        }
		    }
		});
		
		
		addEmployee.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				
				
			}
	
		});
		
		editEmployee.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				
				
			}
	
		});
		
		deleteEmployee.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				
				
			}
	
		});
		
		printEmployee.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				try {
					Print.generateAndPrintReport(searchData, columnNames, "Employee Report", true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});
		
		employeeSearchField.textProperty().addListener(new ChangeListener<String>() 
		{

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				//System.out.println(employeeSearchField.getText());
				searchData = search(employeeSearchField.getText());
				ObservableList<Employee> values = arrayToEmployeeObservableList(searchData);
				
				Table_Employee.setItems(values);
			}
		});
		
		
	}
	
	private String[][] search(String value)
	{
		if(employeeData == null) 
			return null;
		System.out.println();
		System.out.println("searching: ");
		System.out.println();
		ArrayList<ArrayList<String>> searchResult = new ArrayList<ArrayList<String>>();
		for(int i = 0 ; i < employeeData.length ; i++)
		{
			boolean found = false;
			for(int j = 0 ; j < employeeData[0].length; j++)
			{
				if(employeeData[i][j] != null && employeeData[i][j].toLowerCase().contains(value.toLowerCase()))
				{
					//System.out.println("found");
					found = true;
					break;
				}
			}
			
			if(found)
			{
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0 ; j < employeeData[0].length; j++)
				{
					temp.add(employeeData[i][j]);
				}
				
				searchResult.add(temp);
			}
		}
		
		final String[][] result = new String[searchResult.size()][];
		int i = 0;
		for (ArrayList<String> l : searchResult) 
		  result[i++] = l.toArray(new String[l.size()]);
		return result;
	}
	
	private void refreshData()
	{
		employeeData = database.select(fullSQL);
		Table_Employee.setItems(arrayToEmployeeObservableList(employeeData));
	}
	
	public ObservableList<Employee> arrayToEmployeeObservableList(String[][] data)
	{
		ObservableList<Employee> values = FXCollections.observableArrayList();
		
		if(data == null) return null;
		for(int i = 0 ; i < data.length ; i++)
		{
			
			values.add(new Employee(
					Integer.parseInt(data[i][0]),
					data[i][1],
					data[i][2],
					data[i][3],
					data[i][4],
					data[i][5],
					data[i][6],
					data[i][7],
					data[i][8],
					data[i][9],
					Double.parseDouble(data[i][10])));
			
		}
		//System.out.println(Arrays.toString(values.toArray()));
		return values;
	}
	
	String[] columnNames = {
							"Employee ID","First Name","Last Name", 
							"Address","Postal Code","Province",
							"Email","Home Phone Number","Cell Phone Number",
							"Position","Salary"
							};
	
	public ObservableList<Value> getEmployeeValues(String[] values)
	{
		ObservableList<Value> value = FXCollections.observableArrayList();
		value.add(new Value(columnNames[0],values[0]));
		value.add(new Value(columnNames[1],values[1]));
		value.add(new Value(columnNames[2],values[2]));
		value.add(new Value(columnNames[3],values[3]));
		value.add(new Value(columnNames[4], values[4]));
		value.add(new Value(columnNames[5],values[5]));
		value.add(new Value(columnNames[6],values[6]));
		value.add(new Value(columnNames[7],values[7]));
		value.add(new Value(columnNames[8],values[8]));
		value.add(new Value(columnNames[9],values[9]));
		value.add(new Value(columnNames[10],values[10]));
		return value;
	}
		
}


