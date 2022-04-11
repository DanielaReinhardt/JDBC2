import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.nimbus.State;

public class JDBC{
    public static void main(String [] args) throws SQLException{

        String username = "Daniela";
        String password = "dani.0730";
       
        final String DRIVER ="com.mysql.cj.jdbc.Driver"; 
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
            e.printStackTrace();
        }
    
        String URL = "jdbc:mysql://localhost:3306/wild_db_quest";
        Connection connection = DriverManager.getConnection(URL, username, password);


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM persons");
      
        // String query = "INSERT INTO persons (firstname, lastname, age) VALUES ('Daniela', 'Reinhardt', '29')";
        // Statement statement2 = connection.createStatement();
        // statement.execute(query);
      

        int columns = resultSet.getMetaData().getColumnCount();
        for(int i = 1; i<=columns; i++)
        System.out.print(String.format("%-15s", resultSet.getMetaData().getColumnLabel(i)));   
        System.out.println();
        

        while(resultSet.next()){
            for(int i=1; i<=columns; i++)
            System.out.print(String.format("%-15s", resultSet.getString(i)));
            System.out.println();

        }
        Statement statement3 = connection.createStatement();
        String delete = "DELETE FROM persons WHERE firstname ='Daniela'";
        int rowsAffected = statement3.executeUpdate(delete);
        System.out.println("Rows affected delete: " + rowsAffected);

        Statement statement4 = connection.createStatement();
        String update = "UPDATE persons SET age=35 WHERE lastname='connor'";
        int rowsAffected2 = statement4.executeUpdate(update);
        System.out.println("Rows affected update: " + rowsAffected2);

        
            //To avoid SQL injection, the example should be refactored to something like    
            // preparedStatement = connection.prepareStatement( "INSERT INTO persons ( VORNAME, NACHNAME, AGE) VALUES ( ?, ?, ? )");
            // preparedStatement.setString(1, "Paul" );
            // preparedStatement.setString(2, "Paulsen" );
            // preparedStatement.setInt(3, 42 );


            // preparedStatement.execute();

           resultSet.close();
           statement.close();
        //    statement2.close();
           statement3.close();
           statement4.close();
           connection.close();
           

        }

        
        
    }
