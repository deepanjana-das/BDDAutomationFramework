package dbconn;

import domain.EmployeeDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {

    public Connection connectDatabase(String dbType, String dbName, String dbUsername, String dbPassword){
        String dbUrl = "";

        if(dbType.equalsIgnoreCase("PostgreSQL")){
          dbUrl = "jdbc:postgresql://localhost:5432/" + dbName;
        }else if(dbType.equalsIgnoreCase("SQLServer")){
            dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=" + dbName;
        }else if(dbType.equalsIgnoreCase("Oracle")){
            dbUrl = "jdbc:oracle:thin:@localhost:1521:" + dbName;
        }

        Connection con = null;
        try{
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return con;
    }

    public List<EmployeeDetails> executeQuery(String dbType, String dbName, String dbUsername, String dbPassword, String query){

        // get the db connection
        Connection con = connectDatabase(dbType, dbName, dbUsername, dbPassword);

        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        if(con != null){
            try {
                Statement statement = con.createStatement();

                ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()){
                   EmployeeDetails employeeDetails = EmployeeDetails.builder()
                           .empId(resultSet.getString("empId"))
                           .empName(resultSet.getString("empName"))
                           .dept(resultSet.getString("dept"))
                           .doj(resultSet.getString("doj"))
                           .build();

                   employeeDetailsList.add(employeeDetails);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return employeeDetailsList;
    }
}
