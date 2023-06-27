package com.example.finalassignment;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.finalassignment.Database.handler;


public class NewWindow {
    static Database handler;
    public TextField textName;
    public TextField textLastName;
    public TextField textPin;
    public Label lblId;
//This is the signup window.
    public static void add(String name, String lastName, String pin, String id, String bookTaken) {
        boolean flag = name.isEmpty() || lastName.isEmpty() || pin.isEmpty() || id.isEmpty();
        if (flag) {
            System.out.println("Empty.");
            return;
        }
        String st = "INSERT INTO USERINFO VALUES (" +
                "'" + name + "'," +
                "'" + lastName + "'," +
                "'" + pin + "'," +
                "'" + id + "'," +
                "'" + bookTaken + "')";
        if (handler.execAction(st)) {
            System.out.println("info entered");
        } else {
            System.out.println("info not entered");
        }
    }

    public void addUser(ActionEvent actionEvent) throws SQLException {
        handler = new Database();
        String qu = "SELECT * FROM USERINFO ORDER BY id DESC";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String id  = rs.getString (4);
            int newId = Integer.parseInt(id)+1;
            add(textName.getText(), textLastName.getText(), textPin.getText(), String.valueOf(newId), "");
            lblId.setText("This is your ID: " + newId);
        }
    }
}
