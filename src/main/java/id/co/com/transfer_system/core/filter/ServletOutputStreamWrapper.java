package id.co.com.transfer_system.core.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.IOException;
import java.io.OutputStream;

public class ServletOutputStreamWrapper extends ServletOutputStream {
    private transient OutputStream outputStream;

    public ServletOutputStreamWrapper(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        this.outputStream.write(b);
    }
}
