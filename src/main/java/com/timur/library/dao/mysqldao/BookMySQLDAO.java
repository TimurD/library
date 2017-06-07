package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.BookDAO;

import com.timur.library.models.Author;
import com.timur.library.models.Book;
import com.timur.library.models.Genre;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by timur on 26.05.2017.
 */
public class BookMySQLDAO implements BookDAO {

    private final static Logger LOGGER = Logger.getLogger(BookMySQLDAO.class);


    private final int COLUMN_BOOK_ID = 1;
    private final int COLUMN_BOOK_NAME = 1;
    private final String SELECT_BOOKS = "SELECT g.name,g.id,b.name,b.id,b.amount,a.name,a.id FROM books b JOIN genres g ON b.genre_id=g.id JOIN books_authors ba ON ba.book_id=b.id JOIN authors a ON a.id=ba.author_id ";
    private final String CREATE_BOOK = "INSERT INTO books (name,genre_id,amount) VALUES (?,?,?)";
    private final String SELECT_BOOK_BY_ID = SELECT_BOOKS + "WHERE b.id=?";
    private final String SELECT_BOOKS_BY_NAME = SELECT_BOOKS + "WHERE b.name like ?";
    private final String UPDATE_BOOK_AMOUNT_BY_ID="UPDATE books SET amount=? WHERE id=?";
    private final String SELECT_BOOK_OF_AUTHOR_BY_NAME = SELECT_BOOKS+"WHERE b.id in (SELECT book_id FROM books_authors WHERE author_id in (SELECT id FROM authors WHERE name like ?)) ";
    private final String SELECT_BOOKS_BY_GENRE=SELECT_BOOKS+"WHERE genre_id=?";
    private final String SELECT_BOOK_OF_AUTHOR_BY_ID = SELECT_BOOKS+"WHERE b.id in (SELECT book_id FROM books_authors WHERE author_id=?) ";
    private final String DELETE_BOOK="DELETE FROM books WHERE id=?";


    private static volatile BookMySQLDAO bookMySQLDAO;

    public static BookMySQLDAO getInstance() {
        BookMySQLDAO localInstance = bookMySQLDAO;
        if (localInstance == null) {
            synchronized (BookMySQLDAO.class) {
                localInstance = bookMySQLDAO;
                if (localInstance == null) {
                    bookMySQLDAO = localInstance = new BookMySQLDAO();
                }
            }
        }
        return localInstance;
    }
    private BookMySQLDAO(){

    }



    @Override
    public List<Book> findByName(String name) {
        List<Book> books = new ArrayList<>();
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_NAME)) {
            preparedStatement.setString(COLUMN_BOOK_NAME, name+"%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    books=fillUpBook(resultSet);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByGenre(Integer genreId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_GENRE)) {
            preparedStatement.setInt(COLUMN_BOOK_NAME, genreId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                books=fillUpBook(resultSet);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return books;
    }

    @Override
    public Book findById(Integer bookId) {
        Book book = new Book();
        try (Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(COLUMN_BOOK_ID, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    book=fillUpBook(resultSet).get(0);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             books= fillUpBook(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return books;
    }


    @Override
    public List<Book> findAllBooksOfAuthor(String authorName) {
        List<Book> books = new ArrayList<>();
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_OF_AUTHOR_BY_NAME)){
            preparedStatement.setString(1,authorName+"%");
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books=fillUpBook(resultSet);
            }
        }catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findAllBooksOfAuthor(Integer authorId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_OF_AUTHOR_BY_ID)){
            preparedStatement.setInt(1,authorId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books=fillUpBook(resultSet);
            }
        }catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return books;
    }

    @Override
    public void setBookAmount(Integer id, Integer amount) {
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_BOOK_AMOUNT_BY_ID)){
            preparedStatement.setInt(1,amount);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Integer create(String bookName,Integer genreId,Integer amount) {
        Integer id=0;
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOK,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bookName);
            preparedStatement.setInt(2,genreId);
            preparedStatement.setInt(3,amount);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet=preparedStatement.getGeneratedKeys()){
                if(resultSet.next())
                    id=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Integer bookId){
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setInt(1,bookId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    private List<Book> fillUpBook(ResultSet resultSet) throws SQLException {
        Map<Integer,Book> books= new HashMap<>();
       while (resultSet.next()){
           int id=resultSet.getInt("b.id");
           if(books.containsKey(id)){
               Author author=new Author();
               author.setId(resultSet.getInt("a.id"));
               author.setName(resultSet.getString("a.name"));
               books.get(id).addAuthor(author);
           } else
           {
              Book book= new Book();
              Genre genre=new Genre();
              genre.setId(resultSet.getInt("g.id"));
              genre.setName(resultSet.getString("g.name"));
              book.setGenre(genre);
              book.setId(id);
              book.setAmount(resultSet.getInt("b.amount"));
              book.setName(resultSet.getString("b.name"));
              book.addAuthor(new Author(resultSet.getInt("a.id"),resultSet.getString("a.name")));
              books.put(id,book);
           }
       }
           return new ArrayList<Book>(books.values());
    }
}


