/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles returning the user to the book list page.
 * @author Branson
 */
public class ContinueDispatcher implements IDispatcher {

    /**
     * Returns the titles page so the user can continue shopping.
     *
     * @param request the current HTTP request
     * @return the titles JSP page
     * @throws Exception if the action cannot be processed
     */
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        return "/jsp/titles.jsp";
    }
}
