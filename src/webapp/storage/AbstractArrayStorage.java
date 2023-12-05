package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен. SAVE impossible", r.getUuid());
        } else if (isExist(index)) {
            throw new ExistStorageException("Резюме " + r.getUuid() + " уже существует в массиве. SAVE impossible", r.getUuid());
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
        System.out.println("\nВыполнена очистка массива");
    }

    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (isExist(index)) {
            return storage[index];
        } else {
            throw new NotExistStorageException("\nРезюме " + uuid + " отсутствует в массиве. GET impossible", uuid);
        }
    }

    public final void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (isExist(index)) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " successfully updated");
        } else {
            throw new NotExistStorageException("Резюме " + r.getUuid() + " отсутствует в массиве. UPDATE impossible", r.getUuid());
        }
    }

    public final void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException("\nРезюме " + uuid + " Отсутствует в массиве. DELETE impossible", uuid);
        } else {
            deleteResume(index);
            size--;
            System.out.println("\nРезюме " + uuid + " successfully deleted из массива.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getSearchKey(String uuid);

    protected boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);
}
