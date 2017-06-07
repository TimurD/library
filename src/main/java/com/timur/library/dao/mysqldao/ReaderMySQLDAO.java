package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.ReaderDAO;
import com.timur.library.models.Reader;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by timur on 22.05.2017.
 */
public class ReaderMySQLDAO implements ReaderDAO {

    private final static Logger LOGGER = Logger.getLogger(ReaderMySQLDAO.class);


    private static volatile ReaderMySQLDAO readerMySQLDAO;

    private final String GROUP_BY = "group by r.id";
    private final String SELECT_READERS = "Select r.id,r.name,r.email , IFNULL(count(rb.reader_id),0) as debt from readers r join readers_roles rr on r.id=rr.reader_id left  join readers_books rb on r.id=rb.reader_id and  rb.active=b'1' where r.id not in(select reader_id from readers_roles where role_id=2) ";
    private final String SELECT_ALL_READERS = SELECT_READERS + GROUP_BY;
    private final String SELECT_READER_BY_ID = SELECT_READERS + "AND r.id=? ";
    private final String SELECT_READER_BY_NAME = SELECT_READERS + "AND name LIKE ? " + GROUP_BY;
    private final String SELECT_READER_BY_EMAIL = SELECT_READERS + "AND email LIKE ? " + GROUP_BY;
    private final String SELECT_READER_BY_LOGIN_INFORMATION = "SELECT r.id,r.name,r.email FROM readers r WHERE r.email=? and r.password=? ";
    private final String INSERT_READER = "INSERT INTO readers (name,password,email) VALUES (?,?,?)";
    private final String SELECT_READERS_FOR_HOST = "SELECT id,name,email,true as admin from readers where id in (select reader_id from readers_roles where role_id=2) union SELECT id,name,email,false as admin from readers where id not in (select reader_id from readers_roles where role_id=2)";



    public static ReaderMySQLDAO getInstance() {
        ReaderMySQLDAO localInstance = readerMySQLDAO;
        if (localInstance == null) {
            synchronized (ReaderMySQLDAO.class) {
                localInstance = readerMySQLDAO;
                if (localInstance == null) {
                    readerMySQLDAO = localInstance = new ReaderMySQLDAO();
                }
            }
        }
        return localInstance;
    }


    private ReaderMySQLDAO() {

    }


    @Override
    public List<Reader> findAll() {
        return findByDynamicSelect(SELECT_ALL_READERS, null);
    }

    @Override
    public Reader findById(Integer id) {
        List<Reader> readers = findByDynamicSelect(SELECT_READER_BY_ID, new Object[]{id});
        return readers.isEmpty() ? null : readers.get(0);
    }


    @Override
    public List<Reader> findByName(String name) {
        return findByDynamicSelect(SELECT_READER_BY_NAME, new Object[]{name + "%"});
    }



    @Override
    public List<Reader> findByEmail(String email) {
        return findByDynamicSelect(SELECT_READER_BY_EMAIL, new Object[]{email + "%"});
    }

    @Override
    public List<Reader> findUsersForHost() {
        List<Reader> readers = new ArrayList<>();
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_READERS_FOR_HOST);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setEmail(resultSet.getString("email"));
                reader.setName(resultSet.getString("name"));
                reader.setId(resultSet.getInt("id"));
                reader.setAdmin(resultSet.getBoolean("admin"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return readers;
    }


    @Override
    public Reader login(String email, String password) {
        Reader reader = null;
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_READER_BY_LOGIN_INFORMATION)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reader = new Reader();
                    reader.setEmail(resultSet.getString("r.email"));
                    reader.setName(resultSet.getString("r.name"));
                    reader.setId(resultSet.getInt("r.id"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return reader;
    }

    @Override
    public void create(Reader reader) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_READER)) {
            preparedStatement.setString(1, reader.getName());
            preparedStatement.setString(2, reader.getPassword());
            preparedStatement.setString(3, reader.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }



    private List<Reader> findByDynamicSelect(String sql, Object[] sqlParams) {
        try (Connection connection = Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                preparedStatement.setObject(i + 1, sqlParams[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return fetchMultiResults(resultSet);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    private List<Reader> fetchMultiResults(ResultSet rs) throws SQLException {
        List<Reader> resultList = new ArrayList();
        while (rs.next()) {
            Reader reader = new Reader();
            populateDto(reader, rs);
            resultList.add(reader);
        }
        return resultList;
    }


    private void populateDto(Reader reader, ResultSet resultSet) throws SQLException {
        reader.setId(resultSet.getInt("r.id"));
        reader.setName(resultSet.getString("r.name"));
        reader.setEmail(resultSet.getString("r.email"));
        reader.setDebt(resultSet.getInt("debt"));
    }
}
