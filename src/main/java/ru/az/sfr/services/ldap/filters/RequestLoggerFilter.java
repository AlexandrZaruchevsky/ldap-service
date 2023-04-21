package ru.az.sfr.services.ldap.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.az.sfr.services.ldap.services.LogService;
import ru.az.sfr.services.ldap.services.RequestLog;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@Order(2)
@Slf4j
public class RequestLoggerFilter implements Filter {

    private final LogService logService;

    public RequestLoggerFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Map<String, Map<String, String>> attrRequest = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        Iterator<String> headersIter = request.getHeaderNames().asIterator();
        while (headersIter.hasNext()) {
            String key = headersIter.next();
            headers.put(key, request.getHeader(key));
        }
        Map<String, String> attrs = new HashMap<>();
        attrs.put("RemoteAddr", request.getRemoteAddr());
        attrs.put("Method", request.getMethod());
        attrs.put("ServletPath", request.getServletPath());
        attrs.put("CharacterEncoding", request.getCharacterEncoding());
        attrs.put("RequestURI", request.getRequestURI());
        attrs.put("ContentType", request.getContentType());
        attrRequest.put(RequestLog.HEADERS.getName(), headers);
        attrRequest.put(RequestLog.ATTRIBUTES.getName(), attrs);
        logService.requestExecute(attrRequest);
        log.info("Request from [{}] with method [{}] and URI [{}]", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
