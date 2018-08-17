package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	
	protected final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();		
	}
	
	public String createSelectAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());	
		return sb.toString();		
	}
	
	
	public List<T> findAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String query = createSelectAllQuery();
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = ConnectionFactory.getStatement(connection);			
			resultSet = statement.executeQuery(query);
			
			return createObjects(resultSet);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:findAll " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	
	private String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		String aux;
		sb.append("INSERT INTO ");		
		sb.append(type.getSimpleName());
		sb.append("(");
		int n = type.getDeclaredFields().length;
		for (Field field : type.getDeclaredFields()) {
			field.setAccessible(true);
			sb.append(field.getName() + ",");
			
		}
		aux = sb.substring(0, sb.length()-1);
		sb.delete(0, sb.length());
		sb.append(aux);
		
		sb.append(")");
		
		sb.append(" Values (");
		
		for (int i=0;i<n;i++) {
			sb.append("?,");
		}
		aux = sb.substring(0, sb.length()-1);
		sb.delete(0, sb.length());
		sb.append(aux+")");
		
		
		return sb.toString();
	}
	
	private String createUpdateQuery(String idUpdate,int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		
		int n = type.getDeclaredFields().length;
		for (Field field : type.getDeclaredFields()) {
			field.setAccessible(true);
			sb.append(field.getName() + "=?,");			
		}
		
		String aux = sb.substring(0,sb.toString().length()-1);
		sb.delete(0,sb.toString().length());
		sb.append(aux);
		
		sb.append(" WHERE " + idUpdate + "=" + id);
		
		return sb.toString();
		
	}
	
	public T updateT(Object object,int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		
		String query = createUpdateQuery("id"+type.getSimpleName(),id);
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();			
			statement = ConnectionFactory.prepareStatement(query,connection);
			int count=1;
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				
				StringBuilder sb1 = new StringBuilder();
				sb1.append("get");
				String aux1 = field.getName();
				String name1 = aux1.substring(0, 1).toUpperCase() + aux1.substring(1);
				sb1.append(name1);
				//System.out.println(sb1.toString());
				Method method1 = object.getClass().getDeclaredMethod(sb1.toString());
				

				StringBuilder sb = new StringBuilder();
				sb.append("set");
				String aux = field.getName();
				String name = aux.substring(0, 1).toUpperCase() + aux.substring(1);
				sb.append(name);
				//System.out.println(sb.toString());
				Method method = object.getClass().getDeclaredMethod(sb.toString(),field.getType());
				
				if (field.getType().isPrimitive()) {
					int n = (Integer)method1.invoke(object);					
					statement.setInt(count,n);
					count++;
				}
				else {
					statement.setString(count,String.valueOf(method1.invoke(object)));	
					count++;
				}
			}
		
			
			statement.executeUpdate();
			
		}catch(SQLException | SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:UpdateT " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		
		return null;
	}
	
	public T insertIntoT(Object object) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		
		String query = createInsertQuery();		
		try {
			connection = ConnectionFactory.getConnection();			
			statement = ConnectionFactory.prepareStatement(query,connection);
			int count=1;
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				
			
				StringBuilder sb = new StringBuilder();
				sb.append("get");
				String aux = field.getName();
				String name = aux.substring(0, 1).toUpperCase() + aux.substring(1);
				sb.append(name);
				
				Method method = object.getClass().getDeclaredMethod(sb.toString());
				
				statement.setString(count,String.valueOf(method.invoke(object)));
				count++;
				
			}
			
			statement.execute();
			
		}catch(SQLException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:insertIntoT " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		
		return null;
	}
	
	public List<T> findById (int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		
		String query = createSelectQuery("id"+type.getSimpleName());
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = ConnectionFactory.prepareStatement(query,connection);
			statement.setInt(1,id);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet);
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:findById " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	private String createDeleteQuery(String id) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE ");
		sb.append("id"+type.getSimpleName());
		sb.append("=?");
		return sb.toString();
		
	}
	
	public T deleteByIdT(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		
		String query = createDeleteQuery("id"+type.getSimpleName());		
		//System.out.println(query);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = ConnectionFactory.prepareStatement(query,connection);	
			statement.setInt(1,id);
			statement.execute();		
			
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:findAll " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		
		return null;
	}
	
	private String createOrderQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append(" ORDER BY ");
		sb.append("id"+type.getSimpleName());
		sb.append(" ASC");
		return sb.toString();
		
	}
	
	public T orderByIdT() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		String query = createOrderQuery();		
		System.out.println(query);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = ConnectionFactory.getStatement(connection);
			statement.executeQuery(query);		
			
		}catch(SQLException e) {
			LOGGER.log(Level.WARNING, type.getName()+"DAO:orderById " + e.getMessage());
		}finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		
		return null;
	}
	
	
	
	protected List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		
		try {
			while(resultSet.next()) {
				T instance = type.newInstance();
				for (Field field: type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance,value);					
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	
	
	
	
	
	
}	
