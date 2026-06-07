/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface used by all dispatcher classes in the bookstore application.
 * @author Branson
 */
public interface IDispatcher {
    /**
     * Processes the current HTTP request and returns the next JSP page.
     *
     * @param request the current HTTP request
     * @return the JSP page that should be displayed next
     * @throws Exception if the dispatcher cannot complete the request
     */
    public String execute(HttpServletRequest request) throws Exception;
}
