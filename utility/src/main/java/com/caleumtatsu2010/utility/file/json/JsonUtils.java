package com.caleumtatsu2010.utility.file.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonUtils {

    public JsonUtils() {
    }

    public static JSONArray resultSetToJson(ResultSet resultSet) throws Exception {
        ResultSetMetaData md = resultSet.getMetaData();
        int numCols = md.getColumnCount();
        List<String> colNames = IntStream.range(0, numCols)
                .mapToObj(i -> {
                    try {
                        return md.getColumnName(i + 1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return "?";
                    }
                })
                .collect(Collectors.toList());

        JSONArray result = new JSONArray();
        while (resultSet.next()) {
            JSONObject row = new JSONObject();
            colNames.forEach(cn -> {
                try {
                    row.put(cn, resultSet.getObject(cn));
                } catch (JSONException | SQLException e) {
                    e.printStackTrace();
                }
            });
            result.put(row);
        }
        return result;
    }

//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            // STEP 1: Register JDBC driver
//            Class.forName("org.h2.Driver");
//
//            //STEP 2: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
//
//            //STEP 3: Execute a query
//            System.out.println("Creating table in given database...");
//            stmt = conn.createStatement();
//
//            String query = "create schema TEST";
//            stmt.execute(query);
//
//            String query2 = "CREATE TABLE if not exists words (\n" +
//                    "                                Id INT NOT NULL,\n" +
//                    "                               Username VARCHAR(50) NOT NULL,\n" +
//                    "                               Firstname VARCHAR(50) NOT NULL,\n" +
//                    "                               Lastname VARCHAR(50) NOT NULL\n" +
//                    ")";
//            stmt.execute(query2);
//
//
//            String queryins1 = "insert into words (Id, Username, Firstname, Lastname) VALUES ( 7173, 'doel1', 'John', 'Doe')";
//            String queryins2 = "insert into words (Id, Username, Firstname, Lastname) VALUES ( 3722, 'smith3', 'Dana', 'Smith' )";
//            String queryins3 = "insert into words (Id, Username, Firstname, Lastname) VALUES ( 5490, 'john22', 'John', 'Wang' )";
//            stmt.execute(queryins1);
//            stmt.execute(queryins2);
//            stmt.execute(queryins3);
//
//
//            String query4 = "select *  from words";
//            rs = stmt.executeQuery(query4);
//
//            JSONArray jsonresult = ResultSetToJson(rs);
//            System.out.println(jsonresult);
//            // STEP 4: Clean-up environment
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//            } // nothing we can do
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            } //end finally try
//        } //end try
//        System.out.println("Goodbye!");
//    }

}
