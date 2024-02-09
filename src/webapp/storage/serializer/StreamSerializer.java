package webapp.storage.serializer;

import webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    Resume doRead(InputStream in) throws IOException;

    void doWrite(Resume r, OutputStream os) throws IOException;
}