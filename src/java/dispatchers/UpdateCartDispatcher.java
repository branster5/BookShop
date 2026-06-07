/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 * Handles updating and removing items from the shopping cart.
 * @author Branson
 */
public class UpdateCartDispatcher implements IDispatcher {

    /**
     * Updates the cart quantities and removes selected cart items.
     *
     * @param request the current HTTP request
     * @return the cart JSP page
     * @throws Exception if the cart cannot be updated
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = null;
        CartItem item = null;
        String isbn = null;
        String nextPage = "/jsp/cart.jsp";
        cart = (Map<String, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            return "/jsp/titles.jsp";
        }
        String[] booksToRemove = request.getParameterValues("remove");
        if (booksToRemove != null) {
            for (String bookToRemove : booksToRemove) {
                cart.remove(bookToRemove);
            }
        }
        Set<Map.Entry<String, CartItem>> entries = cart.entrySet();
        Iterator<Map.Entry<String, CartItem>> iter = entries.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, CartItem> entry = iter.next();
            isbn = entry.getKey();
            item = entry.getValue();
            int quantity = Integer.parseInt(request.getParameter(isbn));
            item.updateQuantity(quantity);
        }

        return nextPage;
    }
}
