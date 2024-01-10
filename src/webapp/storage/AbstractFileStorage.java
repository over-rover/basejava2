package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null!!!");
        if (!directory.isDirectory())
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        if (!directory.canRead() || !directory.canWrite())
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        this.directory = directory;
    }

    @Override
    public Resume[] getAll() {
        File[] files = directory.listFiles();
        if (files == null)
            throw new StorageException("Директория не существует", directory.getName());
        Resume[] resumes = new Resume[files.length];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(files[i]);
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null)
            throw new StorageException("Директория не существует", directory.getName());
        for (File file : files) {
            doDelete(file);
        }
        System.out.println("\nВыполнена очистка директории");
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (files == null)
            throw new StorageException("Директория не существует", directory.getName());
        return files.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete())
            throw new StorageException("Не удалось удалить файл", file.getName());
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        Resume resume;
        try {
            resume = doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract Resume doRead(File file) throws IOException;

    protected abstract void doWrite(Resume r, File file) throws IOException;
}