/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.managers;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by timur on 28.05.2017.
 */
public class Message {


    private static final String BUNDLE_NAME = "locales.locale";
    private static final String EN = "en_US";
    private static final String RU  ="ru_RU";
    private static final ResourceBundle MESSAGE_RU = ResourceBundle.getBundle(BUNDLE_NAME+"_"+RU);
    private static final ResourceBundle MESSAGE_EN = ResourceBundle.getBundle(BUNDLE_NAME+"_"+EN);

    public static final String SERVLET_EXECPTION = "message.servletException";
    public static final String IO_EXCEPTION = "message.IOexception";
    public static final String LOGIN_ERROR = "message.loginError";
    public static final String EMAIL_ERROR="message.emailError";
    public static final String NAME_ERROR="message.nameError";
    public static final String PASSWORD_ERROR="message.passwordError";
    public static final String AUTHOR_ALREADY_EXIST_ERROR ="message.authorExistError";
    public static final String GENRE_ALREADY_EXIST_ERROR = "message.genreExistError";
    public static final String BOOK_IS_NOT_AVAILABLE ="message.bookIsNotAvailable";
    public static final String GENRE_ADDED="message.genreAdded";
    public static final String AUTHOR_ADDED ="message.authorAdded";
    public static final String BOOK_ORDERED ="message.bookOrdered";
    public static final String BOOK_DELETED ="message.bookDeleted";
    public static final String CANT_DELETE_BOOK ="message.cannotDeleteBook";
    public static final String USER_NEED_RETURN_BOOKS = "message.userNeedReturnBooks";
    public static final String DAYS_OVERDUE ="tag.daysOverdue" ;
    public static final String DAYS_LEFT="tag.daysLeft";


    public static ResourceBundle getInstance(String locale) {
        if (locale==null||locale.equals(RU)) {
            return MESSAGE_RU;
        }
        return MESSAGE_EN;
    }
}
