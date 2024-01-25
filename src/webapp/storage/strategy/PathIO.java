package webapp.storage.strategy;

import webapp.model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathIO implements  ReadWriteStrategy {
    @Override
    public Resume doRead(Object obj) throws IOException {
        Path path = (Path) obj;
        return new ObjectStreamStorage().doRead(Files.newInputStream(path));
    }

    @Override
    public void doWrite(Resume r, Object obj) throws IOException {
        Path path = (Path) obj;
        new ObjectStreamStorage().doWrite(r, Files.newOutputStream(path));
    }
}
