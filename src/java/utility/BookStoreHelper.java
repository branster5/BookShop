/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Book;

/**
 * Helper methods used by the bookstore dispatchers.
 * @author Branson
 */
public class BookStoreHelper {
    /**
     * Finds a book from the session book list using the selected ISBN.
     *
     * @param isbn the ISBN of the selected book
     * @param session the current HTTP session
     * @return the matching Book object, or null if it cannot be found
     */
    public static Book getBookFromList(String isbn, HttpSession session) {
        List<Book> list = (List<Book>) session.getAttribute("books");
        Book aBook = null;
        for (Book book : list) {
            if (isbn.equals(book.getIsbn())) {
                aBook = book;
                break;
            }
        }
        return aBook;
    }
}
