/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles the view cart action for the bookstore application.
 * @author Branson
 */
public class ViewCartDispatcher implements IDispatcher {

    /**
     * Returns the cart page so the user can view the current cart.
     *
     * @param request the current HTTP request
     * @return the cart JSP page
     * @throws Exception if the action cannot be processed
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        return "/jsp/cart.jsp";
    }
}
