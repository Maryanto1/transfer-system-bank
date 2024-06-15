package id.co.com.transfer_system.util;

import id.co.com.transfer_system.core.filter.HttpRequestWrapper;
import id.co.com.transfer_system.core.filter.HttpResponseWrapper;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ISTHttpUtil {
    public static HttpRequestWrapper wrapRequest(ServletRequest request) throws IOException {
        if (request instanceof HttpRequestWrapper) {
            return (HttpRequestWrapper) request;
        } else {
            return new HttpRequestWrapper((HttpServletRequest) request);
        }
    }

    public static HttpResponseWrapper wrapResponse(ServletResponse response) throws IOException {
        if (response instanceof HttpResponseWrapper) {
            return (HttpResponseWrapper) response;
        } else {
            return new HttpResponseWrapper((HttpServletResponse) response);
        }
    }
}
