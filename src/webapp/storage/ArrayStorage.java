package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteLogic(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    @Override
    protected void saveLogic(Resume r, int index) {
        storage[size] = r;
    }
}