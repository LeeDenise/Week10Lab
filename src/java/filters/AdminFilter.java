/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.DBUtil;
import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author 807785
 */
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)             
            throws IOException, ServletException {

            EntityManager em = DBUtil.getEmFactory().createEntityManager();

            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpSession session = httpRequest.getSession();
            String email = (String)session.getAttribute("email");
            
            UserDB userDB = new UserDB();
            User user = userDB.get(email);
            
            
            
            if (user.getRole().getRoleId() != 1) {
                HttpServletResponse httpResponse = (HttpServletResponse)response;
                httpResponse.sendRedirect("login");
                return;
            }
            
            chain.doFilter(request, response);

    }
    
    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        

    }
    
}
