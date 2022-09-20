package com.spring5.demo.filter;

import com.spring5.demo.EventNotify;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class MyFilter implements Filter{

    private EventNotify eventNotify;

    public MyFilter(EventNotify eventNotify) {
        this.eventNotify = eventNotify;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("init()");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("doFilter()");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // httpServletResponse.setContentType("text/plain;charset=utf-8");  //한방에 보낸다.
        httpServletResponse.setContentType("text/event-stream;charset=utf-8");  //스트림...플러스할때마다...

        PrintWriter out = httpServletResponse.getWriter();
        /**
         * 비동기로 구성해보려고...해보기
         * 1. 5초동안 서버에서 만든 후, 한꺼번에 리턴한다. 즉 블럭킹,동기 프로그램이다
         * 2. 버퍼를 비운다.
         * 순차적으로 데이터를 준다.
         */
        for(int i=0 ; i < 5; i++){
            out.println("응답 " + i + "\n");
            out.flush();
            try {
                Thread.sleep(1000); // 1초 딜레이  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (true){
            try {
                if(eventNotify.getChange()){
                    int lastIndex = eventNotify.getEvents().size() - 1;
                    out.println("응답 " + eventNotify.getEvents().get(lastIndex));
                    out.flush();
                    eventNotify.setChange(false);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("destroy()");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
