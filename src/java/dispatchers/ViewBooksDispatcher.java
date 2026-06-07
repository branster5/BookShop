/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import model.Book;
import utility.AdmitBookStoreDAO;

/**
 * Handles home page / books list
 * @author Branson
 */
public class ViewBooksDispatcher implements IDispatcher {

    /**
     * Loads all books from the database and stores them in the session.
     *
     * @param request the current HTTP request
     * @return the titles page if successful, otherwise the error page
     * @throws Exception if the request cannot be processed
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();

        try {
            List<Book> books = dao.getAllBooks();
            session.setAttribute("books", books);
            return "/jsp/titles.jsp";
        } catch (Exception ex) {
            request.setAttribute("result", ex.toString());
            return "/jsp/error.jsp";
        }
    }
}
