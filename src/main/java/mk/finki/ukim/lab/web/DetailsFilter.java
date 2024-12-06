package mk.finki.ukim.lab.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "details_filter")
public class DetailsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (Objects.equals(req.getServletPath(), "/details")) {
            filterChain.doFilter(req, resp);
            return;
        }

        if (req.getSession().getAttribute("name") == null || req.getSession().getAttribute("address") == null) {
            resp.sendRedirect("/details");
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
