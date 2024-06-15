package id.co.com.transfer_system.core.filter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ServletInputStreamWrapper extends ServletInputStream {
    private transient InputStream inputStream;

    public ServletInputStreamWrapper(ByteArrayOutputStream byteArrayOutputStream) {
        super();
        this.inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    }

    @Override
    public int read() throws IOException {
        return this.inputStream.read();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }
}
