package blueTeam.catering.model;

/**
 * Model class for a Employee
 * @author Tung
 *
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee 
{
	private IntegerProperty employeeId;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty address;
	private StringProperty postalCode;
	private StringProperty province;
	private StringProperty email;
	private StringProperty homePhone;
	private StringProperty cellPhone;
	private StringProperty position;
	private StringProperty pay;

	public Employee()
	{
		employeeId = null;
		firstName = null;
		lastName = null;
		address = null;
		postalCode = null;
		province = null;
		email = null;
		homePhone = null;
		cellPhone = null;
		position = null;
		pay = null;
	}
	
	public Employee(String firstName, String lastName)
	{
		this.employeeId = new SimpleIntegerProperty(1);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		//some random data for the rest
		this.address = new SimpleStringProperty("Some street");
		this.postalCode = new SimpleStringProperty("H4N 3T4");
		this.province = new SimpleStringProperty("Qc");
		this.email = new SimpleStringProperty("someone@gmail.comt");
		this.homePhone = new SimpleStringProperty("450-555-8888");
		this.cellPhone = new SimpleStringProperty("514-555-4444");
		this.position = new SimpleStringProperty("Waiter");
		this.pay = new SimpleStringProperty("12/h");
	}
	
	
	public IntegerProperty getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(IntegerProperty employeeId) {
		this.employeeId = employeeId;
	}

	public StringProperty getFirstName() {
		return firstName;
	}
	public StringProperty getLastName() {
		return lastName;
	}
	public StringProperty getAddress() {
		return address;
	}
	public StringProperty getPostalCode() {
		return postalCode;
	}
	public StringProperty getProvince() {
		return province;
	}
	public StringProperty getEmail() {
		return email;
	}
	public StringProperty getHomePhone() {
		return homePhone;
	}
	public StringProperty getCellPhone() {
		return cellPhone;
	}
	public StringProperty getPosition() {
		return position;
	}
	public StringProperty getPay() {
		return pay;
	}

	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}

	public void setAddress(StringProperty address) {
		this.address = address;
	}

	public void setPostalCode(StringProperty postalCode) {
		this.postalCode = postalCode;
	}

	public void setProvince(StringProperty province) {
		this.province = province;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public void setHomePhone(StringProperty homePhone) {
		this.homePhone = homePhone;
	}

	public void setCellPhone(StringProperty cellPhone) {
		this.cellPhone = cellPhone;
	}

	public void setPosition(StringProperty position) {
		this.position = position;
	}

	public void setPay(StringProperty pay) {
		this.pay = pay;
	}
}
