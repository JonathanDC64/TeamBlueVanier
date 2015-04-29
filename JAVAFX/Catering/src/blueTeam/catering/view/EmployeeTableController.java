package blueTeam.catering.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import blueTeam.catering.model.Employee;

public class EmployeeTableController implements Initializable {

	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private TableColumn<Employee, String> employeeIdColumn;
	@FXML
	private TableColumn<Employee, String> firstNameColumn;
	@FXML
	private TableColumn<Employee, String> lastNameColumn;
	@FXML
	private TableColumn<Employee, String> addressColumn;
	@FXML
	private TableColumn<Employee, String> postalCodeColumn;
	@FXML
	private TableColumn<Employee, String> provinceColumn;
	@FXML
	private TableColumn<Employee, String> emailColumn;
	@FXML
	private TableColumn<Employee, String> homePhoneColumn;
	@FXML
	private TableColumn<Employee, String> cellPhoneColumn;
	@FXML
	private TableColumn<Employee, String> positionColumn;
	@FXML
	private TableColumn<Employee, String> payColumn;

	private ObservableList<Employee> employeeData = FXCollections
			.observableArrayList();

	public EmployeeTableController() {

		employeeData.add(new Employee("Tung", "Doa"));
		employeeData.add(new Employee("Jonathan", "Del"));
		employeeData.add(new Employee("Karan", "Sin"));
		employeeData.add(new Employee("Julie", "Man"));
		employeeData.add(new Employee("David", "Men"));
		employeeData.add(new Employee("Tung", "Doa"));
		employeeData.add(new Employee("Jonathan", "Del"));
		employeeData.add(new Employee("Karan", "Sin"));
		employeeData.add(new Employee("Julie", "Man"));
		employeeData.add(new Employee("David", "Men"));
		employeeData.add(new Employee("Tung", "Doa"));
		employeeData.add(new Employee("Jonathan", "Del"));
		employeeData.add(new Employee("Karan", "Sin"));
		employeeData.add(new Employee("Julie", "Man"));
		employeeData.add(new Employee("David", "Men"));
		employeeData.add(new Employee("Tung", "Doa"));
		employeeData.add(new Employee("Jonathan", "Del"));
		employeeData.add(new Employee("Karan", "Sin"));
		employeeData.add(new Employee("Julie", "Man"));
		employeeData.add(new Employee("David", "Men"));
	}

	public ObservableList<Employee> getEmployeeData() {
		return employeeData;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getFirstName());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getLastName());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getAddress());
		postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getPostalCode());
		provinceColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getProvince());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getEmail());
		homePhoneColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getHomePhone());
		cellPhoneColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getCellPhone());
		positionColumn.setCellValueFactory(cellData -> cellData.getValue()
				.getPosition());
		payColumn.setCellValueFactory(cellData -> cellData.getValue().getPay());

		employeeTable.setItems(getEmployeeData());
	}

}
