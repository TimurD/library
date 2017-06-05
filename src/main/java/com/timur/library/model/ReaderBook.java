package com.timur.library.model;

import java.util.Date;

/**
 * Created by timur on 25.05.2017.
 */
public class ReaderBook {
    private int id;
    private Reader reader;
    private Book book;
    private Date lendDate;
    private Date returnDate;

    public ReaderBook(int id,Reader reader, Book book, Date lendDate, Date returnDate) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public ReaderBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book= book;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
