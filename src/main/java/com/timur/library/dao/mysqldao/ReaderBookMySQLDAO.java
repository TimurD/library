package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.entities.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timur on 25.05.2017.
 */
public class ReaderBookMySQLDAO {

    private final static Logger LOGGER = Logger.getLogger(ReaderBookMySQLDAO.class);

    private static volatile ReaderBookMySQLDAO readerBookMySQLDAO;

    private final String SELECT_READERS_FOR_BOOK = "SELECT rb.id,rb.lend_date,rb.return_date,r.name,r.id,r.email FROM readers_books rb JOIN readers r ON rb.reader_id=r.id WHERE book_id=? AND active=b'1'";
    private final String SELECT_BOOKS_FOR_READER = "SELECT  rb.id,rb.lend_date,rb.return_date,a.id,a.name,b.id,b.name FROM readers_books rb JOIN books b ON rb.book_id=b.id JOIN books_authors ba ON ba.book_id=b.id JOIN authors a ON ba.author_id=a.id WHERE  rb.reader_id=? ";
    private final String SELECT_READER_BOOKS_FOR_ADMIN = SELECT_BOOKS_FOR_READER + "AND rb.active=b'1'";
    private final String READER_SEND_QUERY_ON_BOOK = "INSERT INTO readers_books(reader_id,book_id,active) VALUES(?,?,?)";
    private final String READER_RETURN_BOOK = "DELETE FROM readers_books WHERE id=?";
    private final String SELECT_READERS_QUERIES_ON_BOOKS = "SELECT  rb.id,r.email,r.id,rb.lend_date,rb.return_date,a.id,a.name,b.id,b.name FROM readers_books rb JOIN readers r ON rb.reader_id=r.id JOIN books b ON rb.book_id=b.id JOIN books_authors ba ON ba.book_id=b.id JOIN authors a ON ba.author_id=a.id WHERE rb.active=b'0'";
    private final String GET_BOOK_TO_READER = "UPDATE readers_books SET lend_date=?,return_date=?,active=b'1' WHERE id=?";
    private final String SELECT_BOOKS_FOR_READING_ROOM = "SELECT  rb.id,a.id,a.name,b.id,b.name FROM readers_books rb JOIN books b ON rb.book_id=b.id JOIN books_authors ba on ba.book_id=b.id JOIN authors a ON ba.author_id=a.id WHERE rb.reader_id IN (SELECT reader_id FROM readers_roles WHERE role_id=2)";
    private final String SELECT_COUNT_BOOK_LENDERS = "SELECT COUNT(id) AS count FROM readers_books WHERE book_id=?";
    private final Long daysInMillis = 1000L * 60L * 60L * 24L;


    private ReaderBookMySQLDAO() {

    }

    public static ReaderBookMySQLDAO getInstance() {
        ReaderBookMySQLDAO localInstance = readerBookMySQLDAO;
        if (localInstance == null) {
            synchronized (ReaderBookMySQLDAO.class) {
                localInstance = readerBookMySQLDAO;
                if (localInstance == null) {
                    readerBookMySQLDAO = localInstance = new ReaderBookMySQLDAO();
                }
            }
        }
        return localInstance;
    }

    public Integer readerTakeBook(Integer readerId, Integer bookId, Boolean isAdmin) {
        Integer i = 0;
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READER_SEND_QUERY_ON_BOOK)) {
            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setBoolean(3, isAdmin);
            i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return i;
    }

    public void getBookToReader(Integer id, Integer days) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_TO_READER)) {
            Timestamp lendDate = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(1, lendDate);
            Timestamp returnDate = new Timestamp(System.currentTimeMillis() + (days * daysInMillis));
            preparedStatement.setTimestamp(2, returnDate);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public void readerReturnBook(Integer id) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READER_RETURN_BOOK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }


    public Boolean isBookOrdered(Integer bookId) {
        Boolean ordered = false;
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_BOOK_LENDERS)) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ordered = resultSet.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ordered;
    }

    public List<ReaderBook> findReadersForBook(Integer bookId) {
        List<ReaderBook> readers = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_READERS_FOR_BOOK)) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ReaderBook readerBook = new ReaderBook();
                    Reader reader = new Reader();
                    reader.setId(resultSet.getInt("r.id"));
                    reader.setName(resultSet.getString("r.name"));
                    reader.setEmail(resultSet.getString("r.email"));
                    readerBook.setLendDate(resultSet.getTimestamp("rb.lend_date"));
                    readerBook.setReturnDate(resultSet.getTimestamp("rb.return_date"));
                    readerBook.setId(resultSet.getInt("rb.id"));
                    readerBook.setReader(reader);
                    readers.add(readerBook);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return readers;
    }

    public List<ReaderBook> findReaderBooksForAdmins(Integer readerId) {
        return findReaderBooks(readerId, SELECT_READER_BOOKS_FOR_ADMIN);
    }

    public List<ReaderBook> findReaderBooksForReader(Integer readerId) {
        return findReaderBooks(readerId, SELECT_BOOKS_FOR_READER);
    }


    private List<ReaderBook> findReaderBooks(Integer readerId, String query) {
        List<ReaderBook> books = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, readerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                books = fillUpReaderBook(resultSet, false, false);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return books;
    }


    public List<ReaderBook> findBooksForReadingRoom() {
        List<ReaderBook> books = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_FOR_READING_ROOM);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            books = fillUpReaderBook(resultSet, false, true);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return books;
    }

    public List<ReaderBook> findBookOrders() {
        List<ReaderBook> books = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_READERS_QUERIES_ON_BOOKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            books = fillUpReaderBook(resultSet, true, false);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return books;
    }


    private List<ReaderBook> fillUpReaderBook(ResultSet resultSet, Boolean includeReaders, Boolean readingRoom) throws SQLException {
        Map<Integer, ReaderBook> readerBookHashMap = new HashMap<Integer, ReaderBook>();
        while (resultSet.next()) {
            int id = resultSet.getInt("rb.id");
            if (readerBookHashMap.containsKey(id)) {
                Author author = new Author();
                author.setId(resultSet.getInt("a.id"));
                author.setName(resultSet.getString("a.name"));
                readerBookHashMap.get(id).getBook().addAuthor(author);
            } else {
                ReaderBook readerBook = new ReaderBook();
                readerBook.setId(id);
                if (!readingRoom) {
                    readerBook.setReturnDate(resultSet.getDate("rb.return_date"));
                    readerBook.setLendDate(resultSet.getDate("rb.lend_date"));
                }
                Book book = new Book();
                book.setId(resultSet.getInt("b.id"));
                book.setName(resultSet.getString("b.name"));
                book.addAuthor(new Author(resultSet.getInt("a.id"), resultSet.getString("a.name")));
                if (includeReaders) {
                    Reader reader = new Reader();
                    reader.setId(resultSet.getInt("r.id"));
                    reader.setEmail(resultSet.getString("r.email"));
                    readerBook.setReader(reader);
                }
                readerBook.setBook(book);
                readerBookHashMap.put(id, readerBook);
            }
        }
        return new ArrayList<ReaderBook>(readerBookHashMap.values());
    }
}
