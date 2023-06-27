package com.example.finalassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static com.example.finalassignment.Database.conn;
import static com.example.finalassignment.Database.handler;

public class BookWindow {
    public TextField textEnterId;
    public Label lblTitle;
    public Label lblAuthor;
    public Label lblQuantity;
    public Label lblGenre;
    public Label lblIdNumber;
    public ListView<Book> bookList = new ListView<>();
    public Label lblBookCheck;

    // displays the book information when select it in the listview
    public void displayBooks(MouseEvent mouseEvent) throws SQLException {
        handler = new Database();
        String qu = "SELECT * FROM BOOK";
        ResultSet rs = handler.execQuery(qu);
        while (rs.next()) {
            Book temp = new Book(rs.getString("title"), rs.getString("author"),
                    rs.getString("genre"), Integer.parseInt(rs.getString("quantity")),
                    rs.getString("idnumber"));
            bookList.getItems().add(temp);
            temp = bookList.getSelectionModel().getSelectedItem();
            lblAuthor.setText(temp.author);
            lblTitle.setText(temp.title);
            lblGenre.setText(temp.genre);
            lblQuantity.setText(String.valueOf(temp.quantity));
            lblIdNumber.setText(temp.idNumber);
        }
    }

    // borrow a book and store the book title into the userinfo table
    public void borrowBook(ActionEvent actionEvent) throws SQLException {
        Book temp;
        temp = bookList.getSelectionModel().getSelectedItem();
        temp.borrow();
        String name = temp.title;
        Statement stmt = conn.createStatement();
        String statement = "UPDATE USERINFO SET bookTaken =" + "'" + name + "'" + "WHERE bookTaken = ' '";
        stmt.execute(statement);
        lblBookCheck.setText(temp.title + "taken successfully.");
    }

    // return a book and empty the book title in the userinfo table
    public void returnBook(ActionEvent actionEvent) throws SQLException {
        Book temp;
        temp = bookList.getSelectionModel().getSelectedItem();
        temp.returnBook();
        String name = temp.title;
        Statement stmt = conn.createStatement();
        String statement = "UPDATE USERINFO SET bookTaken = ' '" + "WHERE book = " + "'" + name + "'";
        stmt.execute(statement);
        lblBookCheck.setText(temp.title + "returned successfully.");
    }

    // goes to the add book window with the two methods below
    public void addBookWindow(ActionEvent actionEvent) {
        loadWindow("add-book-window.fxml", "Add A Book");
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
}
