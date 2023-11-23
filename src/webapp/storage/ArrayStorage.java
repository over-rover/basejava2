package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;
    private int index = -1;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
        System.out.println("\nВыполнена очистка массива");
    }

    public void save(Resume r) {
        if (isExist(r.getUuid())) {
            System.out.println("Резюме " + r.getUuid() + " уже существует в ArrayStorage. Придумайте другой uuid");
            return;
        }

        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Хранилище переполнено. SAVE impossible");
        }
    }

    public Resume get(String uuid) {
        if (isExist(uuid)) {
            return storage[index];
        } else {
            System.out.println("\nРезюме " + uuid + " отсутствует в хранилище. GET impossible");
            return null;
        }
    }

    public void update(Resume r) {
        if (isExist(r.getUuid())) {
            storage[index] = r;
            System.out.println("Резюме " + r.getUuid() + " successfully updated");
        } else {
            System.out.println("Резюме " + r.getUuid() + " отсутствует в хранилище. UPDATE impossible");
        }
    }

    public void delete(String uuid) {
        if (!isExist(uuid)) {
            System.out.println("\nРезюме " + uuid + " удалить невозможно. Отсутствует в хранилище");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("\nРезюме " + uuid + " удалено из массива.");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                return true;
            }
        }
        index = -1;
        return false;
    }
}