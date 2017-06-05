package com.timur.library.dao.mysqldao;

import com.timur.library.dao.factory.Connector;
import com.timur.library.dao.interfaces.RoleDAO;
import com.timur.library.model.Reader;
import com.timur.library.model.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 28.05.2017.
 */
public class RoleMySQLDAO implements RoleDAO{

    private final static Logger LOGGER = Logger.getLogger(RoleMySQLDAO.class);


    private final String SELECT_ROLES_FOR_USER="SELECT * FROM roles WHERE id IN(SELECT role_id FROM readers_roles WHERE reader_id=?)";

    private static volatile RoleMySQLDAO roleMySQLDAO;

    private RoleMySQLDAO(){

    }

    public static RoleMySQLDAO getInstance() {
        RoleMySQLDAO localInstance = roleMySQLDAO;
        if (localInstance == null) {
            synchronized (RoleMySQLDAO.class) {
                localInstance = roleMySQLDAO;
                if (localInstance == null) {
                    roleMySQLDAO = localInstance = new RoleMySQLDAO();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Role>findRolesForReader(Reader reader){
        List<Role>roles=new ArrayList<>();
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ROLES_FOR_USER)) {
            preparedStatement.setInt(1,reader.getId());
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Role role=new Role();
                    role.setId(resultSet.getInt("id"));
                    role.setName(resultSet.getString("name"));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return roles;
    }
}
