package com.example.finalassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.finalassignment.Database.handler;

public class HelloController {
    public TextField textEnterId;
    public Label lblCheck;
    public TextField textPin;

    // go to the sign up window
    public void opensWindow(ActionEvent actionEvent) {
        loadWindow("new-window.fxml", "Sign Up");
    }
    private void loadWindow(String location, String title) {
        try{
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // go to the borrow or return book window only if the id is found and pin is matched
    public void signIn(ActionEvent actionEvent) throws SQLException {
        handler = new Database();
        String qu = "SELECT * FROM USERINFO";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            String pinCheck = rs.getString("pin");
            if (Objects.equals(rs.getString("id"), textEnterId.getText()) &&
                    Objects.equals(pinCheck, textPin.getText())){
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
                loadWindow("book-window.fxml", "Borrow or Return a Book");
            } else {
                lblCheck.setText("ID not exist. Please check again or sign up.");
            }
        }
    }
}