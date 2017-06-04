package com.timur.library;

import com.timur.library.services.AuthorizationService;

/**
 * Created by timur on 19.05.2017.
 */
public class Runner {
    public static void main(String[] args) {
     /*   AuthorMySQLDAO authorMySQLDAO = new AuthorMySQLDAO();
        GenreMySQLDAO genreMySQLDAO = new GenreMySQLDAO();
        System.out.println(genreMySQLDAO.findAll().get(0).getName());
        BookMySQLDAO3 bookMySQLDAO = new BookMySQLDAO3();
        for (Book b : bookMySQLDAO3.findAll()) {
            System.out.println(b.getAmount() + " " + b.getName() + " " + b.getAuthor().getName() + " " + b.getGenre().getName());
        }
        ReaderMySQLDAO readerMySQLDAO = new ReaderMySQLDAO();
        for (Reader reader : readerMySQLDAO.findAllReaders()) {
            System.out.println(reader.getEmail() + " " + reader.getName() + " " + reader.getPassword());
        }
        System.out.println(readerMySQLDAO.login("Timur@gmail.com", "").getName());
        Reader reader = new Reader("Timurr", "Timurr", "timurr@gmail");
        readerMySQLDAO.signUp(reader);*/
       /* ReaderBookMySQLDAO readerBookMySQLDAO=new ReaderBookMySQLDAO();
        Book book=new Book();
        book.setId(3);
        Reader reader=new Reader();
        reader.setId(1);
        readerBookMySQLDAO.readerTakeBook(reader,book,20);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readerBookMySQLDAO.readerReturnBook(reader,book);*/
      /*  BookMySQLDAO bookMySQLDAO =new BookMySQLDAO();
        ReaderBookMySQLDAO readerBookMySQLDAO=new ReaderBookMySQLDAO();
        Author author=new Author();
        author.setId(1);
        Reader reader=new Reader();
        reader.setId(1);
        for(ReaderBook readerBook:readerBookMySQLDAO.findBooksForReader(reader)){
            System.out.println(readerBook.getBook().getName());
            System.out.println(readerBook.getLendDate());
            for(Author author1: readerBook.getBook().getAuthors()){
                System.out.println("a "+author1.getName());
            }
        }*/

   /*   MySQLDAO mySQLDAO=new MySQLDAO();
        System.out.println(mySQLDAO.getBookMySQLDAO().findAllBooksOfAuthor("test").get(1).getName());*/


    }
}

