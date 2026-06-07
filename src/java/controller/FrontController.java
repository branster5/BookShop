package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;

import dispatchers.IDispatcher;
import dispatchers.ViewBooksDispatcher;
import dispatchers.AddToCartDispatcher;
import dispatchers.CheckoutDispatcher;
import dispatchers.ContinueDispatcher;
import dispatchers.UpdateCartDispatcher;
import dispatchers.ViewCartDispatcher;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {

    private final HashMap<String, IDispatcher> actions = new HashMap<String, IDispatcher>();

    /**
     * Initialize global variables.
     * @param config ServletConfig object
     * @throws ServletException if an error occurs during initialization
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        actions.put("view_titles", new ViewBooksDispatcher());
        actions.put("add_to_cart", new AddToCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
    }

    /**
     * Process the HTTP GET request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        // Forward GET requests to doPost method
        doPost(request, response);
    }

    /**
     * Process the HTTP POST request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get the requested action from the request parameters
        String requestedAction = request.getParameter("action");

        // If no action is specified, fetch all books and display them
        if (requestedAction == null) {
            requestedAction = "view_titles";
        } 
        
        IDispatcher dispatcher = actions.get(requestedAction);

        if (dispatcher == null) {
            request.setAttribute("result", "Unknown action: " + requestedAction);
            this.dispatch(request, response, "/jsp/error.jsp");
            return;
        }

        try {
            String nextPage = dispatcher.execute(request);
            this.dispatch(request, response, nextPage);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("result", ex.toString());
            this.dispatch(request, response, "/jsp/error.jsp");
        }
    }

    /**
     * Forward the request to the specified page.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page Page to forward to
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Get Servlet information.
     * @return Servlet information
     */
    public String getServletInfo() {
        return "controller.FrontController Information";
    }
}

