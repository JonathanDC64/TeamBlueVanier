package dbaApp;
import java.sql.Connection;  // for standard JDBC programs
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
// for BigDecimal and BigInteger support
public class DBConnector {

	private static String URL, USER, PASS;
	private static Connection conn;
	
	public DBConnector()
	{
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
				
				setLoginInfo("jdbc:oracle:thin:@localhost:1521:BlueTeam","btv","btv");
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			} 
	}
	
	public void setLoginInfo(String URL, String USER, String PASS)
	{
		this.URL = URL;
		this.USER = USER;
		this.PASS = PASS;
	}
	
	public void connect()
	{
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getColumnNames(String tableName)
	{
		try {
			Statement stmt = null;
			stmt = conn.createStatement( );
			ResultSet rs;
						
			rs = stmt.executeQuery("Select COLUMN_NAME from user_tab_columns where table_name='" + tableName + "'");
	
			ArrayList<String> text = new ArrayList<String>();
			while(rs.next())
			{
		         text.add(rs.getString(0));
		    }
			return (String[])text.toArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
	public String[][] getQueryRows()
	{
		try 
		{
			PreparedStatement pstmt = null;
			

			Statement stmt = null;
			
			stmt = conn.createStatement( );
			ResultSet rs;
			
			
			
			rs = stmt.executeQuery("select * from student order by studentid");
			
			
			
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
			
			//conn.close();
			
			return fText;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(String tableName, String[] values)
	{
		Statement stmt = null;
		try {
			stmt = conn.createStatement( );
			String sql = "insert into " + tableName +" values(";
			for(int i = 0 ; i < values.length ; i++)
			{
				if(i == values.length-1)
				{
					sql += "'" + values[i] + "'";
				}
				else
				{
					sql += "'" + values[i] + "',";
				}
			}
			sql += ")";
			System.out.println(sql);
			stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

}
