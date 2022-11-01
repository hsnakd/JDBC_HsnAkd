package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {
    String dbURL = "jdbc:oracle:thin:@3.86.235.137:1521:xe";
    String dbUsername ="hr";
    String dbPassword ="hr";

    @Test
    public void test1() throws SQLException {

    // creating list for keeping all the rows maps
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary","24000");
        row1.put("job_id","AD_PRES");

        System.out.println("row1 = " + row1.toString());

        Map<String, Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary","17000");
        row2.put("job_id","AD_VP");

        System.out.println("row2 = " + row2.toString());

    // adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

    // get the Steven lastname directly from the list
        System.out.println(queryData.get(0).get("last_name"));
    // get the Neena salary directly from the list
        System.out.println(queryData.get(1).get("salary"));
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY\n" +
                "FROM EMPLOYEES\n" +  "WHERE ROWNUM < 6");


    // in order to get column names we need resultSetMetaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    // move to first row
        resultSet.next();

    // creating list for keeping all the rows maps
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();

        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println("row1 = " + row1.toString());

    // move to second row
        resultSet.next();

        Map<String, Object> row2 = new HashMap<>();

        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println("row2 = " + row2.toString());


    // adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);


        // CLOSE CONNECTIONS
        resultSet.close();
        statement.close();
        connection.close();

    }

}
