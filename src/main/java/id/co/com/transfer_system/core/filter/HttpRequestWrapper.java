package id.co.com.transfer_system.core.filter;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpRequestWrapper extends HttpServletRequestWrapper {
    private final transient ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        final ServletInputStream servletInputStream = request.getInputStream();
        IOUtils.copy(servletInputStream, this.byteArrayOutputStream);
        IOUtils.closeQuietly(servletInputStream);
    }

    public final ServletInputStream getInputStream() {
        return new ServletInputStreamWrapper(this.byteArrayOutputStream);
    }

    public final BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public final byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    public final String getBodyAsString() {
        return StringUtils.newStringUtf8(getBody());
    }

    public final void reset() {
        this.byteArrayOutputStream.reset();
    }

    public final void setBody(final byte[] data) throws IOException {
        this.reset();
        this.byteArrayOutputStream.write(data);
    }

    public final void setBody(final String data) throws IOException {
        byte[] bdata;
        if (null == data) {
            bdata = new byte[0];
        } else {
            bdata = StringUtils.getBytesUtf8(data);
        }

        this.setBody(bdata);
    }
}
