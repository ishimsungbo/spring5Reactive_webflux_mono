package com.spring5.demo.filter;

import com.spring5.demo.EventNotify;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyFilter2 implements Filter{

    private EventNotify eventNotify;

    public MyFilter2(EventNotify eventNotify) {
        this.eventNotify = eventNotify;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("필터 2 ");
        // 데이터를 하나 발생시켜서

        eventNotify.add("새로운 데이터");

    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }
}
