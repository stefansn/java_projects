package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



import bll.ClientBll;

public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL,USER,PASS);
			System.out.println("Connected to the database.");	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"Eroare la conectarea cu baza de date.");
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	

	public static void close(ResultSet resultSet) {
		// TODO Auto-generated method stub
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING,"Eroare la close() resultSet.");
				e.printStackTrace();
			}
		}
		
	}

	public static void close(PreparedStatement statement) {
		// TODO Auto-generated method stub
		if (statement!=null) {
			try {
				statement.close();
				System.out.println("Connection closed.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING,"Eroare la close() statement.");
				e.printStackTrace();
			}
		}
		
	}
	
	public static void close(Statement statement) {
		// TODO Auto-generated method stub
		if (statement!=null) {
			try {
				statement.close();
				System.out.println("Connection closed.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING,"Eroare la close() statement.");
				e.printStackTrace();
			}
		}
		
	}

	public static void close(Connection connection) {
		// TODO Auto-generated method stub
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.WARNING,"Eroare la close() connection.");
				e.printStackTrace();
			}
		}
	}
	
	//methods for creating a connection,getting an active connection, closing connection
	//a statement or resultset
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = singleInstance.getConnection();
		
	}

	public static PreparedStatement prepareStatement(String query,Connection conn) {
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(query);
			
			return stm;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Statement getStatement(Connection conn) {
		Statement stm;
		try {
			stm = conn.createStatement();
			
			return stm;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
}
