package id.co.com.transfer_system.core.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.io.Charsets;
import org.apache.tomcat.util.codec.binary.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponseWrapper extends HttpServletResponseWrapper {
    private final transient ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private PrintWriter printWriter;

    private final transient OutputStream outputStream;

    public HttpResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
//        ServletOutputStream servletOutputStream = new ServletOutputStreamWrapper(this.byteArrayOutputStream);
        this.printWriter = new PrintWriter(this.byteArrayOutputStream, true);

        this.outputStream = response.getOutputStream();
    }

    public HttpResponseWrapper(final ServletResponse response) throws Exception {
        this((HttpServletResponse) response);
    }

    @Override
    public final ServletOutputStream getOutputStream() {
        return new ServletOutputStreamWrapper(this.byteArrayOutputStream);
    }

    @Override
    public final PrintWriter getWriter() {
        return this.printWriter;
    }

    public final byte[] getBody() {
        return byteArrayOutputStream.toByteArray();
    }

    public final String getBodyAsString() {
        return StringUtils.newStringUtf8(getBody());
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

    public final synchronized void flush() throws IOException {
        this.outputStream.write(this.byteArrayOutputStream.toByteArray());
        this.outputStream.close();
    }

    @Override
    public final void reset() {
        this.byteArrayOutputStream.reset();
    }

    @Override
    public final String toString() {
        String ret;
        ret = new String(this.getBody(), Charsets.UTF_8);
        return ret;
    }
}
