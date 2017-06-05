package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.AuthorDAO;
import com.timur.library.model.Author;
import com.timur.library.model.Book;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public class AuthorMySQLDAO implements AuthorDAO {

    private final static Logger LOGGER = Logger.getLogger(AuthorMySQLDAO.class);


    private final String SELECT_AUTHOR = "SELECT * FROM authors ";
    private final String CREATE_AUTHOR = "INSERT INTO authors (name) VALUES (?)";
    private final String SELECT_AUTHOR_BY_ID = SELECT_AUTHOR + "WHERE id=?";
    private final String SELECT_AUTHOR_BY_NAME = SELECT_AUTHOR + "WHERE name LIKE ?";
    private final String DELETE_AUTHOR = "DELETE FROM authors WHERE id=?";
    private final String SELECT_AUTHOR_FOR_BOOK = "SELECT * FROM authors WHERE id IN(SELECT author_id FROM books_authors WHERE book_id=?)";

    private static volatile AuthorMySQLDAO authorMySQLDAO;

    public static AuthorMySQLDAO getInstance() {
        AuthorMySQLDAO localInstance = authorMySQLDAO;
        if (localInstance == null) {
            synchronized (AuthorMySQLDAO.class) {
                localInstance = authorMySQLDAO;
                if (localInstance == null) {
                    authorMySQLDAO = localInstance = new AuthorMySQLDAO();
                }
            }
        }
        return localInstance;
    }

    private AuthorMySQLDAO(){}

    @Override
    public List<Author> findByName(String name) {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_NAME)) {
            preparedStatement.setString(1, name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setId(resultSet.getInt("id"));
                    author.setName(resultSet.getString("name"));
                    authors.add(author);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return authors;
    }

    @Override
    public Author findById(int id) {
        Author author = new Author();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    author.setId(resultSet.getInt("id"));
                    author.setName(resultSet.getString("name"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return author;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                authors.add(new Author(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return authors;
    }

    @Override
    public Integer create(String authorName) {
        int i = 0;
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUTHOR)) {
            preparedStatement.setString(1, authorName);
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return i;
    }

    @Override
    public List<Author> findBookAuthors(Book book) {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHOR_FOR_BOOK)) {
            preparedStatement.setInt(1, book.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    authors.add(new Author(resultSet.getInt("id"), resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return authors;
    }


}
