/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
        System.out.println("\nВыполнена очистка массива");
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Добавить резюме невозможно. Хранилище переполнено");
        }
    }

    Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("\nРезюме " + uuid + " посмотреть невозможно. Отсутствует в хранилище");
            return null;
        }

        return storage[index];
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("\nРезюме " + uuid + " удалить невозможно. Отсутствует в хранилище");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("\nРезюме " + uuid + " удалено из массива.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }

    int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid))
                return i;
        }

        return -1;
    }
}
