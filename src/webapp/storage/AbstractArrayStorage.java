package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    protected boolean isExist(Integer searchKey) {
        return (int) searchKey >= 0;
    }

    protected void checkOverflowException(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен. SAVE impossible", r.getUuid());
        }
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void doDelete(Integer searchKey);

    protected abstract void doSave(Resume r, Integer searchKey);
}