package application.datamodel;

public class Employee {
	@Override
	public String toString() {
		return "Employee [ID=" + EmployeeID + ", FirstName=" + FirstName
				+ ", LastName=" + LastName + ", Address=" + Address
				+ ", PostalCode=" + PostalCode + ", City=" + City + ", Province=" + Province
				+ ", Email=" + Email + ", HomePhoneNumber=" + HomePhoneNumber
				+ ", CellPhoneNumber=" + CellPhoneNumber + ", Position="
				+ Position + ", Salary=" + Salary + "]";
	}

	String EmployeeID;
	String FirstName;
	String LastName;
	String Address;
	String PostalCode;
	String City;
	String Province;
	String Email;
	String HomePhoneNumber;
	String CellPhoneNumber;
	String Position;
	String Salary;
	
	public Employee(){
		this.EmployeeID = "";
		this.FirstName = "";
		this.LastName = "";
		this.Address = "";
		this.PostalCode = "";
		this.City = "";
		this.Province = "";
		this.Email = "";
		this.HomePhoneNumber = "";
		this.CellPhoneNumber = "";
		this.Position = "";
		this.Salary = "";
	}
	
	public Employee(String ID,	String FirstName, String LastName, String Address, String PostalCode, String City, String Province, String Email, String HomePhoneNumber, String CellPhoneNumber, String Position, String Salary){
		this.EmployeeID = ID;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address = Address;
		this.PostalCode = PostalCode;
		this.City = City;
		this.Province = Province;
		this.Email = Email;
		this.HomePhoneNumber = HomePhoneNumber;
		this.CellPhoneNumber = CellPhoneNumber;
		this.Position = Position;
		this.Salary = Salary;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String EmployeeID) {
		this.EmployeeID = EmployeeID;
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

/*	@Override
	public String toString() {
		return    String.format("EmployeeID: %50d", ID) 
				+ String.format("%nFirstName: %50s", FirstName)
				+ String.format("%nLastName: %50s", LastName) 
				+ String.format("%nAddress: %50s", Address)
				+ String.format("%nPostalCode: %50s", PostalCode )
				+ String.format("%nProvince: %50s", Province)
				+ String.format("%nEmail: %50s", Email )
				+ String.format("%nHomePhoneNumber: %50s", HomePhoneNumber)
				+ String.format("%nCellPhoneNumber: %50s", CellPhoneNumber) 
				+ String.format("%nPosition: %50s", Position )
				+ String.format("%nSalary: %50f",Salary);
	}*/

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

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
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

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}
}