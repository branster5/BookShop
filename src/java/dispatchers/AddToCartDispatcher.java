/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import utility.BookStoreHelper;

/**
 * Handles the add to cart action.
 * @author Branson
 */
public class AddToCartDispatcher implements IDispatcher {

    /**
     * Adds the selected books and quantities to the session cart.
     *
     * @param request the current HTTP request
     * @return the titles page after the cart has been updated
     * @throws Exception if the cart cannot be updated
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String nextPage = "/jsp/titles.jsp";

        HttpSession session = request.getSession();

        // Retrieve the cart from the session
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");

        // Check if selectedBooks is null or empty
        if (selectedBooks == null || selectedBooks.length == 0) {
            return nextPage;
        }

        // If the cart is null, create a new cart and add selected books
        if (cart == null) {
            cart = new HashMap<String, CartItem>();

            for (String isbn : selectedBooks) {
                int quantity = Integer.parseInt(request.getParameter(isbn));
                Book book = BookStoreHelper.getBookFromList(isbn, session);
                CartItem item = new CartItem(book);
                item.setQuantity(quantity);
                cart.put(isbn, item);
            }

            session.setAttribute("cart", cart);
        } else {
            // If the cart already exists, update the quantities of selected books
            for (String isbn : selectedBooks) {
                int quantity = Integer.parseInt(request.getParameter(isbn));
                if (cart.containsKey(isbn)) {
                    CartItem item = cart.get(isbn);
                    item.setQuantity(quantity);
                } else {
                    Book book = BookStoreHelper.getBookFromList(isbn, session);
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }
            }
        }

        return nextPage;
    }
}
