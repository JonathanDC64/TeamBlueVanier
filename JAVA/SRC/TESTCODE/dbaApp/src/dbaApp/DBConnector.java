package dbaApp;
import java.sql.Connection;  // for standard JDBC programs
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
// for BigDecimal and BigInteger support
public class DBConnector {

	public DBConnector()
	{
		
		
		
		
		
	}
	
	public static String[][] report()
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
			
			rs = stmt.executeQuery("select * from student order by studentid");
			
			//rs.next();
			
			//System.out.println(rs.getString(2));
			
			//rs.next();
			
			//System.out.println(rs.getString(2));
			
			
			ArrayList<ArrayList<String>> text = new ArrayList<ArrayList<String>>();
			int rows = 0;
			while(rs.next())
			{
		         //Retrieve by column name
		         String id  = Integer.toString(rs.getInt("studentid"));
		         String first = rs.getString("fname");
		         String last = rs.getString("lname");
		         ArrayList<String> t = new ArrayList<String>();
		         t.add(id);t.add(first);t.add(last);
		         text.add(t);
		         rows++;
		    }

			String[][] fText = new String[rows][text.get(0).size()];
			
			for(int i = 0 ; i < text.size() ; i++)
			{
				for(int j = 0 ; j < text.get(0).size() ; j++)
				{
					fText[i][j] = text.get(i).get(j);
				}
			}
			
			conn.close();
			
			return fText;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void studentInsert(String id, String fname, String lname)
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
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		PreparedStatement pstmt = null;
		
		//String SQL = "insert into student values(1321164,'Joe','Dow')";
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement( );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//stmt.execute(SQL);
		ResultSet rs;
		try {
			rs = stmt.executeQuery("insert into student values(" + Integer.parseInt(id) + ",'" + fname + "','" + lname + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
