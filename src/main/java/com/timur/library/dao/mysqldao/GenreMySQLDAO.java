package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.GenreDAO;
import com.timur.library.models.Genre;
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
public class GenreMySQLDAO implements GenreDAO {

    private final static Logger LOGGER = Logger.getLogger(GenreMySQLDAO.class);


    private static volatile GenreMySQLDAO genreMySQLDAO;

    private final int COLUMN_GENRE_ID=1;
    private final int COLUMN_GENRE_NAME=1;
    private final String SELECT_GENRE="SELECT * FROM genres ";
    private final String CREATE_GENRE="INSERT INTO genres (name) VALUES (?)";
    private final String SELECT_GENRE_BY_ID=SELECT_GENRE+"WHERE id=?";



    private GenreMySQLDAO(){

    }

    public static GenreMySQLDAO getInstance() {
        GenreMySQLDAO localInstance = genreMySQLDAO;
        if (localInstance == null) {
            synchronized (GenreMySQLDAO.class) {
                localInstance = genreMySQLDAO;
                if (localInstance == null) {
                    genreMySQLDAO = localInstance = new GenreMySQLDAO();
                }
            }
        }
        return localInstance;
    }



    @Override
    public Genre findById(int id) {
        Genre genre=new Genre();
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_GENRE_BY_ID)) {
            preparedStatement.setInt(COLUMN_GENRE_ID,id);
            try( ResultSet resultSet=preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    genre.setId(resultSet.getInt("id"));
                    genre.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        List<Genre>genres=new ArrayList<>();
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_GENRE);
            ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                genres.add(new Genre(resultSet.getInt("id"),resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return genres;
    }

    @Override
    public Integer create(String genreName) {
        Integer i=0;
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(CREATE_GENRE)) {
            preparedStatement.setString(1,genreName);
            i= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return i;
    }

}
