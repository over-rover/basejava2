package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
        System.out.println("\nВыполнена очистка массива");
    }

    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (size == storage.length) {
            System.out.println("Хранилище переполнено. SAVE impossible");
        } else if (isExist(index)) {
            System.out.println("Резюме " + r.getUuid() + " уже существует в ArrayStorage. SAVE impossible");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getSearchKey(uuid);
        if (isExist(index)) {
            return storage[index];
        } else {
            System.out.println("\nРезюме " + uuid + " отсутствует в хранилище. GET impossible");
            return null;
        }
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (isExist(index)) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " successfully updated");
        } else {
            System.out.println("Резюме " + r.getUuid() + " отсутствует в хранилище. UPDATE impossible");
        }
    }

    public void delete(String uuid) {
        int index = getSearchKey(uuid);
        if (!isExist(index)) {
            System.out.println("\nРезюме " + uuid + " Отсутствует в хранилище. DELETE impossible");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("\nРезюме " + uuid + " successfully deleted.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(int index) {
        return index >= 0;
    }
}