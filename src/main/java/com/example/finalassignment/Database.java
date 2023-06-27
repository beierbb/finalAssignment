package com.example.finalassignment;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private static final String DB_url = "jdbc:derby:database/forum;create=true";
    static Connection conn = null;
    private static Statement stmt = null;
    static Database handler = null;

    //run the created table and connection methods
    public Database() {
        createConnection();
        createUserTable();
        createBookTable();
    }

    private void createUserTable(){
        String TABLE_NAME = "USERINFO";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists");
            } else {
                String statement = "CREATE TABLE " + TABLE_NAME + "("
                        + "name varchar(200) primary key, \n"
                        + "lastName varchar(200), \n"
                        + "pin varchar(200), \n"
                        + "id varchar(200), \n"
                        + "bookTaken varchar(200))";
                System.out.println(statement);
                stmt.execute(statement);
            }
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }

    private void createBookTable(){
        String TABLE_NAME = "BOOK";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dmn = conn.getMetaData();
            ResultSet tables = dmn.getTables(null, null, TABLE_NAME, null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists");
            } else {
                String statement = "CREATE TABLE " + TABLE_NAME + "("
                        + "title varchar(200) primary key, \n"
                        + "author varchar(200), \n"
                        + "genre varchar(200), \n"
                        + "quantity varchar(200), \n"
                        + "idnumber varchar(200))";
                System.out.println(statement);
                stmt.execute(statement);
            }
        } catch (SQLException var5) {
            throw new RuntimeException(var5);
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_url);
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    //insert in information and organize the table
    public boolean execAction (String qu) {
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
            System.out.println("exception at execQuery" + ex.getLocalizedMessage());
            return false;
        }
    }

    //retrieve information and organize the table
    public ResultSet execQuery (String query) {
        ResultSet resultSet;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Exception at Execute query");
            return null;
        }
        return resultSet;
    }

}


