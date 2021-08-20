oracle�����ַ���
Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
conn = DriverManager.getConnection(url, "scott", "tiger");

mysql�����ַ���
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost/bbs?user=root&password=root&useSSL=false";
conn=DriverManager.getConnection(url);



��װoracle���ݿ�
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
			conn = DriverManager.getConnection(url, "scott", "tiger");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Statement getStatement(Connection conn){
		Statement st=null;
		try {
			st=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn,String sql){
		PreparedStatement prst=null;
		try {
			prst=conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prst;
	}
	
	public static ResultSet getExcuteQuery(Statement st,String sql){
		ResultSet rs=null;
		try {
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void close(Connection conn){
		if(conn!=null){
		try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static void close(Statement st){
		if(st!=null){
			try {
				st.close();
				st=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
