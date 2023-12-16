package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        System.arraycopy(storage, (int) searchKey + 1, storage, (int) searchKey, size - (int) searchKey - 1);
        size--;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        checkOverflowException(r);
        int insertPoint = -((int) searchKey + 1);
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        storage[insertPoint] = r;
        size++;
    }
}