package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DButilPractice {


    @Test
    public void test1(){

        //create connection to db that you put information inside the method
        DBUtils.createConnection();

        int rownum = 6;
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <" + rownum;

        // DBUtils.getQueryResultMap(query)
        // —> returns list of maps, useful when you are getting multiple rows of result.
        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection
        DBUtils.destroy();
    }


    @Test
    public void test2(){
        //create connection
        DBUtils.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <2";

        // DBUtil.getRowMap(query)
        // —> returns maps of string of object useful when we have only one result.
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());

        //close the connection
        DBUtils.destroy();

    }





}
