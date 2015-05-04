package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

import application.Value;
import Printing.Print;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
	
	String[][] searchData;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initialize()
	{	
		TableColumn[] tableColumns = new TableColumn[leftTableColumns.length];
		
		for(int i = 0 ; i < tableColumns.length; i++)
		{
			TableColumn column = new TableColumn<T, String>(leftTableColumns[i]);
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
		//setupListeners();
	}
	
	private ChangeListener<String> searchListener;
	private EventHandler<MouseEvent> displayMoreDataListener;
	private EventHandler<MouseEvent> addListener;
	private EventHandler<MouseEvent> editListener;
	private EventHandler<MouseEvent> deleteListener;
	private EventHandler<MouseEvent> printListener;
	public void setupListeners()
	{
		
		displayMoreDataListener = new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
		        {
		        	displayMoreData();
		        }
		    }
		};
		
		leftTable.setOnMouseClicked(displayMoreDataListener);
		
		
		
		
		
		addListener = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				add();
				
			}
	
		};
		addButton.setOnMouseClicked(addListener);
		
		
		
		editListener = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				edit();
				
			}
	
		};
		
		editButton.setOnMouseClicked(editListener);
		
		
		deleteListener = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				delete();
			}
	
		}; 
		
		deleteButton.setOnMouseClicked(deleteListener);
		
		printListener = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0) 
			{
				print();
			}
	
		};
		printButton.setOnMouseClicked(printListener);
		
		searchListener = new ChangeListener<String>() 
		{

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				//System.out.println(employeeSearchField.getText());
				searchData = search(searchField.getText());
				ObservableList<T> values = arrayToObservableList(searchData);
				
				leftTable.setItems(values);
			}
		};
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
	
/*	public void dereference()
	{
		this.database = null;
		this.fullSQL = null;
		this.partialSQL = null;
		this.leftTable = null;
		this.leftTableColumns = null;
		this.rightTable = null;
		this.addButton = null;
		this.addButtonText = null;
		this.editButton = null;
		this.editButtonText = null;
		this.deleteButton = null;
		this.deleteButtonText = null;
		this.printButton = null;
		this.printButtonText = null;
		this.searchField = null;
		this.data = null;
		this.searchData = null;
	}*/
	
	
	
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
		data = database.select(fullSQL);
		leftTable.setItems(arrayToObservableList(data));
	}
	
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
	
	public void printPreview()
	{
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/PrintPreview.fxml"));
			Stage stage = new Stage();
		    stage.setTitle("Print Preview");
		    stage.setMinWidth(1024);
		    stage.setMinHeight(600);
		    stage.setScene(new Scene(root, 1024, 600));
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public abstract void displayMoreData();
	public abstract void add();
	public abstract void edit();
	public abstract void delete();
	public abstract void print();
}
