package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.dao.factory.MySQLDAO;
import com.timur.library.entities.Reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by timur on 27.05.2017.
 */
public class AuthorizationService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String  VALID_NAME_REGEX="[A-Za-z]{3,30}";
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";

    private DAOFactory mySQLDAO= DAOFactory.getDAOFactory(DAOTypes.MySQL);
    private static volatile AuthorizationService authorizationService;


    private AuthorizationService(){

    }

    public static AuthorizationService getInstance() {
        AuthorizationService localInstance = authorizationService;
        if (localInstance == null) {
            synchronized (AuthorizationService.class) {
                localInstance = authorizationService;
                if (localInstance == null) {
                    authorizationService = localInstance = new AuthorizationService();
                }
            }
        }
        return localInstance;
    }


    public Reader login(String email, String login){
       Reader reader= mySQLDAO.getReaderMySQLDAO().login(email,login);
       if(reader!=null){
           reader.setRoles(mySQLDAO.getRoleMySQLDAO().findRolesForReader(reader));
       }
       return reader;
    }

    public void signUp(String name,String password,String email){
        Reader reader=new Reader();
        reader.setName(name);
        reader.setEmail(email);
        reader.setPassword(password);
        mySQLDAO.getReaderMySQLDAO().create(reader);
    }

    public boolean checkName(String name){
        return name.matches(VALID_NAME_REGEX);
    }

    public boolean checkPassword(String password){
        return password.matches(VALID_PASSWORD_REGEX);
    }

    public boolean checkEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
}
