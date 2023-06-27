package com.example.finalassignment;

public class Book {
    // set up the basic information of a book
    String title;
    String author;
    int quantity;
    String genre;
    String idNumber;

    Book(String t, String a, String g, int q, String i){
        title = t;
        author = a;
        genre = g;
        quantity = q;
        idNumber = i;
    }

//    getters and setters

    public void returnBook(){
        quantity ++;
    }
    public int getQuantity() {
        return quantity;
    }

    public void borrow(){
        quantity--;
    }

    // will display the book title in the listview
    public String toString(){
        return title;
    }
}
