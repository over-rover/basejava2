/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = size();

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
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
        } else {
            return storage[index];
        }
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("\nРезюме " + uuid + " удалить невозможно. Отсутствует в хранилище");
        } else {
            storage[index] = storage[size - 1];
            /*for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }*/
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
        if (storage[0] == null)
            return 0;
        int first = 0, middle = 0;
        int last = storage.length - 1;
        while (first <= last) {
            middle = (first + last) / 2;
            if (storage[middle] == null) {
                last = middle - 1;
            } else {
                first = middle + 1;
            }
        }
        return last + 1;
    }

    int findIndex(String uuid) {
        int i = 0;
        //int size = size();
        while (!uuid.equals(storage[i].uuid)) {
            i++;
            if (i == size)
                return -1;
        }
        return i;
    }
}
