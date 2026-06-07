/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;
/**
 * Handles the checkout action.
 * @author Branson
 */
public class CheckoutDispatcher implements IDispatcher {

    /**
     * Returns the checkout page for the current request.
     *
     * @param request the current HTTP request
     * @return the checkout JSP page
     * @throws Exception if the action cannot be processed
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        return "/jsp/checkout.jsp";
    }
}
