package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("Массив переполнен. SAVE impossible");
        } else if (isExist(index)) {
            System.out.println("Резюме " + r.getUuid() + " уже существует в массиве. SAVE impossible");
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

    public Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (isExist(index)) {
            return storage[index];
        } else {
            System.out.println("\nРезюме " + uuid + " отсутствует в массиве. GET impossible");
            return null;
        }
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (isExist(index)) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " successfully updated");
        } else {
            System.out.println("Резюме " + r.getUuid() + " отсутствует в массиве. UPDATE impossible");
        }
    }

    public void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (!isExist(index)) {
            System.out.println("\nРезюме " + uuid + " Отсутствует в массиве. DELETE impossible");
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
