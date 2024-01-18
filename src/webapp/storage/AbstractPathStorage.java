package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null!!!");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public Resume[] getAll() {

        return null;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path clear error", null);
        }
        System.out.println("\nВыполнена очистка директории");
    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    protected Path getSearchKey(String uuid) {

        return null;
    }

    @Override
    protected boolean isExist(Path Path) {

        return true;
    }

    @Override
    protected void doDelete(Path Path) {

    }

    @Override
    protected void doSave(Resume r, Path Path) {

    }

    @Override
    protected Resume doGet(Path Path) {

        return null;
    }

    @Override
    protected void doUpdate(Resume r, Path Path) {

    }

    protected abstract Resume doRead(InputStream Path) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream Path) throws IOException;
}