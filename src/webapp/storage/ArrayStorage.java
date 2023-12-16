package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(int) searchKey] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        checkOverflowException(r);
        storage[size] = r;
        size++;
    }
}