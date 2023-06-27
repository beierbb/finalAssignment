package com.example.finalassignment;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import static com.example.finalassignment.Database.handler;

public class AddBookWindow {
    public TextField textTitle;
    public TextField textAuthor;
    public TextField textGenre;
    public TextField textQuantity;
    public TextField textIdNumber;

    // add a book in to the BOOK table/database
    public void addBook(ActionEvent actionEvent) {
        add(textTitle.getText(), textAuthor.getText(), textGenre.getText(), textQuantity.getText(), textIdNumber.getText());
        textTitle.clear();
        textAuthor.clear();
        textGenre.clear();
        textQuantity.clear();
        textIdNumber.clear();
    }

    // add the user input into the database
    public static void add(String title, String author, String genre, String quantity, String idnumber) {
        boolean flag = title.isEmpty() || author.isEmpty() || genre.isEmpty() || quantity.isEmpty() || idnumber.isEmpty();
        if (flag) {
            System.out.println("Empty.");
            return;
        }
        String st = "INSERT INTO BOOK VALUES (" +
                "'" + title + "'," +
                "'" + author + "'," +
                "'" + genre + "'," +
                "'" + quantity + "'," +
                "'" + idnumber + "')";
        if (handler.execAction(st)) {
            System.out.println("info entered");
        } else {
            System.out.println("info not entered");
        }
    }
}
