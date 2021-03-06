package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.models.Reader;
import com.timur.library.models.Role;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by timur on 27.05.2017.
 */
public class AuthorizationService {

    private final String HASH_CHANGER = "MD5";

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String  VALID_NAME_REGEX="[A-Za-z]{3,30}";
    private static final String VALID_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$";
    private static final Logger LOGGER=Logger.getLogger(AuthorizationService.class);

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

    /**
     *
     * @param email
     * @param password
     * @return return reader by email and password and switch roles for him
     */
    public Reader login(String email, String password){
       Reader reader= mySQLDAO.getReaderDAO().login(email,hashPassword(password));
       if(reader!=null){
           reader.setRoles(mySQLDAO.getRoleDAO().findRolesForReader(reader));
           reader.setHost(isHost(reader.getRoles()));
           reader.setAdmin(isAdmin(reader.getRoles()));
       }
       return reader;
    }

    /**
     * create new user
     * @param name
     * @param password
     * @param email
     */
    public void signUp(String name,String password,String email){
        Reader reader=new Reader();
        reader.setName(name);
        reader.setEmail(email);
        reader.setPassword(hashPassword(password));
        mySQLDAO.getReaderDAO().create(reader);
    }

    /**
     *
     * @param roles
     * @return is user admin
     */
    public boolean isAdmin(List<Role> roles) {
        for(Role role:roles){
            if(role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param roles
     * @return is user host
     */
    private boolean isHost(List<Role> roles) {
        for(Role role:roles){
            if(role.getName().equals("ROLE_HOST")) {
                return true;
            }
        }
        return false;
    }


    /**
     * check is name valid
     * @param name
     * @return is name valid
     */
    public boolean checkName(String name){
        return name.matches(VALID_NAME_REGEX);
    }

    /**
     * check is password valid
     * @param password
     * @return is password valid
     */
    public boolean checkPassword(String password){
        return password.matches(VALID_PASSWORD_REGEX);
    }

    /**
     * check is email valid
     * @param email
     * @return is email valid
     */
    public boolean checkEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    /**
     *
     * @param password
     * @return encrypted password
     */
    private String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(HASH_CHANGER);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        md.reset();
        md.update(password.getBytes());
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }


}
