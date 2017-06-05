package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.ReaderRoleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by timur on 06.06.2017.
 */
public class ReaderRoleMYSQLDAO implements ReaderRoleDAO {

    private static volatile ReaderRoleMYSQLDAO readerRoleMYSQLDAO;


    private final String MAKE_ADMIN="INSERT INTO readers_roles (reader_id,role_id) VALUES (?, 2)";
    private final String UNMAKE_ADMIN="DELETE FROM readers_roles WHERE role_id=2 AND reader_id=?";


    public static ReaderRoleMYSQLDAO getInstance() {
        ReaderRoleMYSQLDAO localInstance = readerRoleMYSQLDAO;
        if (localInstance == null) {
            synchronized (ReaderRoleMYSQLDAO.class) {
                localInstance = readerRoleMYSQLDAO;
                if (localInstance == null) {
                    readerRoleMYSQLDAO = localInstance = new ReaderRoleMYSQLDAO();
                }
            }
        }
        return localInstance;
    }

    private ReaderRoleMYSQLDAO(){}

    @Override
    public void makeAdmin(Integer userId){
       setRole(userId,MAKE_ADMIN);
    }

    @Override
    public void unmakeAdmin(Integer userId){
        setRole(userId,UNMAKE_ADMIN);
    }

    private void setRole(Integer userId,String query){
        try(Connection connection= Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(query)) {
            preparedStatement.setInt(1,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
