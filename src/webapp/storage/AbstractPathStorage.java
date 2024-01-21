package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

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
        Path[] paths = streamToPathArray();
        Resume[] resumes = new Resume[paths.length];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(paths[i]);
        }
        return resumes;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Ошибка при очистке директории ", null);
        }
    }

    @Override
    public int size() {
        return streamToPathArray().length;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Ошибка при удалении файла ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
            doWrite(r, path);
        } catch (IOException e) {
            throw new StorageException("Ошибка при создании/сохранении файла ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        Resume resume;
        try {
            resume = doRead(path);
        } catch (IOException e) {
            throw new StorageException("Ошибка при чтении из файла ", path.getFileName().toString(), e);
        }
        return resume;
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            doWrite(r, path);
        } catch (IOException e) {
            throw new StorageException("Ошибка при записи в файл ", path.getFileName().toString(), e);
        }
    }

    private Path[] streamToPathArray() {
        Path[] paths;
        try (Stream<Path> pathStream = Files.walk(directory)) {
            paths = (Path[]) pathStream.toArray();
        } catch (IOException e) {
            throw new StorageException("Ошибка при обращении к директории ", directory.getFileName().toString(), e);
        }
        return paths;
    }

    protected abstract Resume doRead(Path path) throws IOException;

    protected abstract void doWrite(Resume r, Path path) throws IOException;
}