package main;
import java.sql.Connection;
import java.sql.Statement; //sql quiereis
import java.sql.DriverManager; //conections
import java.lang.ClassNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Connection1 {

	public static void main (String[] args){
		 
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		try{
			//Utilize the driver 
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connect the driver to the database
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/youtube","root","orelole1");
			System.out.println("Connection Successful");
			
			statement = connection.createStatement();
			//statement.execute("CREATE TABLE books2(id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), autor VARCHAR(100), publisher VARCHAR(100))");
			//statement.execute("INSERT INTO books2 (name,autor,publisher) VALUES ('phpMyAdmin for dummies','Viktor','Kaplan')");
			
			addBook(connection,preparedStatement,"The Matrix","Tim Barton","Apple");
			
			resultSet= statement.executeQuery("Select * FROM books2");
			//read line by one 
			while(resultSet.next()){
				long id= resultSet.getLong("id");
				String name = resultSet.getString("name");
				System.out.println(id+ "  "+ name);
			}
			
		}catch(ClassNotFoundException err){
			System.out.println("Error"+ err.getMessage());
		}catch(SQLException error){
			System.out.println("Error"+ error.getMessage());
		}finally{
			if(connection != null) try{connection.close();}catch(SQLException ingone){}
			if(statement != null) try{statement.close();}catch(SQLException ingone){}
			if(resultSet != null) try{resultSet.close();}catch(SQLException ingone){}
		}
	}

	private static void addBook(Connection connection, PreparedStatement preparedStatement, String name, String author,
			String publisher) throws SQLException {
		preparedStatement= connection.prepareStatement("INSERT INTO books2(name,autor,publisher) VALUES (?,?,?)");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2,author);
		preparedStatement.setString(3,publisher);
		preparedStatement.execute();
	}
}
