package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.serializer.StreamSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;
    private final StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null!!!");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }

        this.streamSerializer = streamSerializer;
    }

    @Override
    public Resume[] getAll() {
        Path[] paths = getFilesList().toArray(Path[]::new);
        Resume[] resumes = new Resume[paths.length];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(paths[i]);
        }
        return resumes;
    }

    //Warning:(42, 19) 'Stream<Path>' used without 'try'-with-resources statement
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
        return (int) getFilesList().count();
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
        } catch (IOException e) {
            throw new StorageException("Ошибка при создании файла ", path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializer.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Ошибка при чтении из файла ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerializer.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Ошибка при записи в файл ", path.getFileName().toString(), e);
        }
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Ошибка при обращении к директории ", directory.getFileName().toString(), e);
        }
    }
}