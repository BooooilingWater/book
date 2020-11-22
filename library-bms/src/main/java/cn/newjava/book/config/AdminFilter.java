package cn.newjava.book.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台页面和接口的过滤器, 防止未登录的用户进行访问
 */
@WebFilter(filterName = "adminFilter", urlPatterns = "/admin/*")
@Slf4j
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 登录过的用户直接跳过
        if (request.getSession().getAttribute("admin") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String uri = request.getRequestURI();
//        log.info(uri);
        // 排除不需要验证的页面
        if (uri.endsWith("/admin/") || uri.equals("/admin/index.html")
                || uri.endsWith("/post/login") || uri.equals("/logout/out")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        response.sendRedirect("/admin/index.html"); // 直接重定向到首页
    }
}
