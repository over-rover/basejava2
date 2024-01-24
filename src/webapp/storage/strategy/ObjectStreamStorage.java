package webapp.storage.strategy;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage implements ReadWriteStrategy {

    @Override
    public Resume doRead(Object is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream((InputStream) is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Resume read ERROR", null, e);
        }
    }

    @Override
    public void doWrite(Resume r, Object os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream((OutputStream) os)) {
            oos.writeObject(r);
        }
    }
}