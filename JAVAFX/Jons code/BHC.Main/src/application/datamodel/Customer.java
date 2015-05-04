package application.datamodel;

public class Customer {


	String CustomerID;
	String FirstName;
	String LastName;
	String Address;
	String PostalCode;
	String Province;
	String Email;
	String HomePhoneNumber;
	String CellPhoneNumber;
	
	public Customer(){
		this.CustomerID = "";
		this.FirstName = "";
		this.LastName = "";
		this.Address = "";
		this.PostalCode = "";
		this.Province = "";
		this.Email = "";
		this.HomePhoneNumber = "";
		this.CellPhoneNumber = "";
	}
	
	public Customer(String ID,	String FirstName, String LastName, String Address, String PostalCode, String Province, String Email, String HomePhoneNumber, String CellPhoneNumber){
		this.CustomerID = ID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address = Address;
		this.PostalCode = PostalCode;
		this.Province = Province;
		this.Email = Email;
		this.HomePhoneNumber = HomePhoneNumber;
		this.CellPhoneNumber = CellPhoneNumber;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String CustomerID) {
		this.CustomerID = CustomerID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getHomePhoneNumber() {
		return HomePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		HomePhoneNumber = homePhoneNumber;
	}

	public String getCellPhoneNumber() {
		return CellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		CellPhoneNumber = cellPhoneNumber;
	}
}
