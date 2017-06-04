/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.manager;

import java.util.ResourceBundle;

/**
 * Created by timur on 28.05.2017.
 */
public class Message {


    public static final String INPUT_NAME ="INPUT_NAME" ;
    public static final String BOOK_ORDERED ="BOOK_ORDERED" ;
    public static final String BOOK_DELETED ="BOOK_DELETED";
    public static final String CANT_DELETE_BOOK ="CANT_DELETE_BOOK" ;
    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXECPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String EMAIL_ERROR="EMAIL_ERROR";
    public static final String NAME_ERROR="NAME_ERROR";
    public static final String PASSWORD_ERROR="PASSWORD_ERROR";
    public static final String AUTHOR_ALREADY_EXIST_ERROR ="AUTHOR_ALREADY_EXIST_ERROR";
    public static final String ADD_BOOK_ERROR ="ADD_BOOK_ERROR";
    public static final String GENRE_ALREADY_EXIST_ERROR = "GENRE_ALREADY_EXIST_ERROR";
    public static final String BOOK_IS_NOT_AVAILABLE ="BOOK_IS_NOT_AVAILABLE" ;
    public static final String GENRE_ADDED="GENRE_ADDED";
    public static final String AUTHOR_ADDED ="AUTHOR_ADDED";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
