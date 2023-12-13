package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
        System.out.println("\nВыполнена очистка массива");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected void checkOverflowException(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен. SAVE impossible", r.getUuid());
        }
    }

    @Override
    protected void updateResume(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);
}