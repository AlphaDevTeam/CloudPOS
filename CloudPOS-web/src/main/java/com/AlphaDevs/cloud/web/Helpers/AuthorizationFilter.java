package com.AlphaDevs.cloud.web.Helpers;

import com.AlphaDevs.cloud.web.Extra.AlphaConstant;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mihindu Karunarathne
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.contains("/Login.xhtml")
                    || (ses != null && ses.getAttribute(AlphaConstant.SESSION_USER) != null)
                    || reqURI.contains("javax.faces.resource") 
                    || reqURI.contains("/Tools/")){
                chain.doFilter(request, response);
            } else {
                MessageHelper.addErrorMessage("Session Expired", "Sorry Session Expired ... Redirect to Login Page");
                resp.sendRedirect(reqt.getContextPath() + "/UI/Login.xhtml");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
