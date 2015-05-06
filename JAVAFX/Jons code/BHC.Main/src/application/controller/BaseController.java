package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import application.Value;
import application.datamodel.Employee;
import dbconnector.database.DBConnector;

public abstract class BaseController<T>
{
	public DBConnector database;
	public String fullSQL; 
	private String partialSQL; 
	
	private TableView<T> leftTable;
	private String[] leftTableColumns;
	
	private TableView<Value> rightTable;
	
	private Button addButton;
	private String addButtonText;
	
	private Button editButton;
	private String editButtonText; 
	
	private Button deleteButton;
	private String deleteButtonText; 
	
	private Button printButton;
	private String printButtonText;
	
	protected TextField searchField;
	
	public String[] columnNames;
	
	public BaseController(DBConnector database, String fullSQL, String partialSQL,
			TableView<T> leftTable, String[] leftTableColumns,
			TableView<Value> rightTable, Button addButton, String addButtonText,
			Button editButton, String editButtonText, Button deleteButton,
			String deleteButtonText, Button printButton, String printButtonText, TextField searchField) {
		super();
		this.database = database;
		this.fullSQL = fullSQL;
		this.partialSQL = partialSQL;
		this.leftTable = leftTable;
		this.leftTableColumns = leftTableColumns;
		this.rightTable = rightTable;
		this.addButton = addButton;
		this.addButtonText = addButtonText;
		this.editButton = editButton;
		this.editButtonText = editButtonText;
		this.deleteButton = deleteButton;
		this.deleteButtonText = deleteButtonText;
		this.printButton = printButton;
		this.printButtonText = printButtonText;
		this.searchField = searchField;
		
	}

	private String[][] data;
	
	public String[][] searchData;
	
	
	public void initialize()
	{	
		TableColumn<T, String>[] tableColumns = new TableColumn[leftTableColumns.length];
		for(int i = 0 ; i < tableColumns.length; i++)
		{
			TableColumn<T, String> column = new TableColumn<T, String>(leftTableColumns[i]);
			column.setCellValueFactory(new PropertyValueFactory<T, String>(leftTableColumns[i].replaceAll("\\s+","")));
			tableColumns[i] = column;
		}
		
		refreshData();//SETS THE ITEMS
		searchData = data;
		
		leftTable.getColumns().addAll(tableColumns);
		leftTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
		
		addButton.setText(addButtonText);
		editButton.setText(editButtonText);
		deleteButton.setText(deleteButtonText);
		printButton.setText(printButtonText);
	}
	
	
	
	private EventHandler<MouseEvent> displayMoreDataListener = new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent mouseEvent)
	    {
	        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
	        {
	        	displayMoreData();
	        }
	    }
	};;
	private EventHandler<MouseEvent> addListener = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent arg0) 
		{
			try {
				add();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	};
	private EventHandler<MouseEvent> editListener = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent arg0) 
		{
			try {
				edit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	};
	private EventHandler<MouseEvent> deleteListener = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent arg0) 
		{
			delete();
		}

	}; 
	
	private EventHandler<MouseEvent> printListener = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent arg0) 
		{
			print();
		}

	};
	
	private ChangeListener<String> searchListener = new ChangeListener<String>() 
	{

		@Override
		public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
			//System.out.println(employeeSearchField.getText());
			searchData = search(searchField.getText());
			ObservableList<T> values = arrayToObservableList(searchData);
			
			leftTable.setItems(values);
		}
	};
	
	public void setupListeners()
	{
		leftTable.setOnMouseClicked(displayMoreDataListener);
		
		addButton.setOnMouseClicked(addListener);
		
		editButton.setOnMouseClicked(editListener);
		
		deleteButton.setOnMouseClicked(deleteListener);
		
		printButton.setOnMouseClicked(printListener);
		
		searchField.textProperty().addListener(searchListener);
	}
	
	public void removeListeners()
	{
		leftTable.setOnMouseClicked(null);
		
		addButton.setOnMouseClicked(null);
		
		editButton.setOnMouseClicked(null);
		
		deleteButton.setOnMouseClicked(null);
		
		printButton.setOnMouseClicked(null);
		
		searchField.textProperty().removeListener(searchListener);
		
	}
	
	public String[][] search(String value)
	{
		if(data == null) 
			return null;
		ArrayList<ArrayList<String>> searchResult = new ArrayList<ArrayList<String>>();
		for(int i = 0 ; i < data.length ; i++)
		{
			boolean found = false;
			for(int j = 0 ; j < data[0].length; j++)
			{
				if(data[i][j] != null && data[i][j].toLowerCase().contains(value.toLowerCase()))
				{
					found = true;
					break;
				}
			}
			
			if(found)
			{
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0 ; j < data[0].length; j++)
				{
					temp.add(data[i][j]);
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
	
	public void refreshData()
	{
		try {
			data = database.select(fullSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leftTable.getItems().removeAll(values);
		leftTable.getItems().clear();
		leftTable.setItems(arrayToObservableList(data));
	}
	
	
	public ObservableList<T> values = FXCollections.observableArrayList();
	public abstract ObservableList<T> arrayToObservableList(String[][] data);
	
	
	
	public ObservableList<Value> getValues(String[] values)
	{
		if(values == null)
			return null;
		ObservableList<Value> value = FXCollections.observableArrayList();
		for(int i = 0 ; i < values.length ; i++)
			value.add(new Value(columnNames[i], values[i]));
		

		return value;
	}
	
	
	private Stage printStage = new Stage();
	public void printPreview()
	{
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/PrintPreview.fxml"));
		    printStage.setTitle("Print Preview");
		    printStage.setMinWidth(1024);
		    printStage.setMinHeight(600);
		    printStage.setScene(new Scene(root, 1024, 600));
		    printStage.show();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public abstract void displayMoreData();
	public abstract void add() throws IOException;
	public abstract void edit() throws IOException;
	public abstract void delete();
	public abstract void print();
}
