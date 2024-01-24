package webapp.storage.strategy;

import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.AbstractStorage;

import java.io.*;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final ReadWriteStrategy readWriteStrategy;

    public FileStorage(File directory, ReadWriteStrategy readWriteStrategy) {
        Objects.requireNonNull(directory, "directory must not be null!!!");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.readWriteStrategy = readWriteStrategy;
    }

    @Override
    public Resume[] getAll() {
        File[] files = getFiles();
        Resume[] resumes = new Resume[files.length];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(files[i]);
        }
        return resumes;
    }

    @Override
    public void clear() {
        for (File file : getFiles()) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getFiles().length;
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
            throw new StorageException("Ошибка при удалении файла ", file.getName());
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Ошибка при создании файла ", file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return readWriteStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Ошибка при чтении из файла ", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            readWriteStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Ошибка при записи в файл ", file.getName(), e);
        }
    }

    private File[] getFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Директория не существует", directory.getName());
        }
        return files;
    }

    /*protected Resume doRead(InputStream file) throws IOException {
        return readWriteStrategy.doRead(file);
    }

    protected void doWrite(Resume r, OutputStream file) throws IOException {
        readWriteStrategy.doWrite(r, file);
    }*/
}