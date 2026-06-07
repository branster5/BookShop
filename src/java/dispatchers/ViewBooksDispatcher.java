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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Handles home page / books list
 *
 * @author Branson
 */
public class ViewBooksDispatcher implements IDispatcher {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("BookShopPU");

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
        EntityManager em = emf.createEntityManager();

        try {
            List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
            session.setAttribute("books", books);
            return "/jsp/titles.jsp";
        } catch (Exception ex) {
            request.setAttribute("result", ex.toString());
            return "/jsp/error.jsp";
        } finally {
            em.close();
        }
    }
}
