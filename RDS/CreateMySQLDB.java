package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateMySQLDB {

    public static void main(String[] args) {

        String dbInstanceEndpoint = "database-1.copgwuyeumn9.us-east-1.rds.amazonaws.com";
        String masterUsername = "admin";
        String masterUserPassword = "admin123";
        String databaseName = "mydatabase2";

        String jdbcUrl = "jdbc:mysql://" + dbInstanceEndpoint + ":3306/mydatabase";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, masterUsername, masterUserPassword)) {
            String createDatabaseSQL = "CREATE DATABASE " + databaseName;

            try (Statement statement = connection.createStatement()){
                statement.executeUpdate(createDatabaseSQL);
                System.out.println("Database " + databaseName + "Created Successfully");
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }





    }


}
 