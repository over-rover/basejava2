package webapp.storage.strategy;

import webapp.model.Resume;

import java.io.IOException;

public interface ReadWriteStrategy {
    Resume doRead(Object obj) throws IOException;

    void doWrite(Resume r, Object obj) throws IOException;
}