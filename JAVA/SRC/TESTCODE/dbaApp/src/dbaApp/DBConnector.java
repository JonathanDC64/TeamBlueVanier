package dbaApp;
import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support
public class DBConnector {

	public static void main(String[] args) {
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}
		String URL = "jdbc:oracle:thin:@localhost:1521:BlueTeam";
		String USER = "btv";
		String PASS = "btv";
		Connection conn;
		
		
		
		try 
		{
			conn = DriverManager.getConnection(URL, USER, PASS);
			
			
			PreparedStatement pstmt = null;
			
			String SQL = "insert into student values(1321164,'Joe','Dow')";
			Statement stmt = null;
			
			stmt = conn.createStatement( );
			stmt.execute(SQL);
			
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}