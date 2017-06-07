package com.timur.library.models;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderBook that = (ReaderBook) o;

        if (reader != null ? !reader.equals(that.reader) : that.reader != null) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (lendDate != null ? !lendDate.equals(that.lendDate) : that.lendDate != null) return false;
        return returnDate != null ? returnDate.equals(that.returnDate) : that.returnDate == null;
    }

    @Override
    public int hashCode() {
        int result = reader != null ? reader.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (lendDate != null ? lendDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }
}
