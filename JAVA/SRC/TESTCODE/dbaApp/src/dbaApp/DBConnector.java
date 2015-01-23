package dbaApp;
import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support
public class DBConnector {

	public DBConnector()
	{
		
		
		
		
		
	}
	
	public static String report()
	{
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
			
			//String SQL = "insert into student values(1321164,'Joe','Dow')";
			Statement stmt = null;
			
			stmt = conn.createStatement( );
			//stmt.execute(SQL);
			ResultSet rs;
			
			rs = stmt.executeQuery("select * from student");
			
			//rs.next();
			
			//System.out.println(rs.getString(2));
			
			//rs.next();
			
			//System.out.println(rs.getString(2));
			
			
			String text = "";
			

			while(rs.next())
			{
		         //Retrieve by column name
		         int id  = rs.getInt("studentid");
		         String first = rs.getString("fname");
		         String last = rs.getString("lname");
		         text += id + " " + first + " " + last + "\n" ;
		    }

			
			
			conn.close();
			
			return text;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		
		
		
	}

}
