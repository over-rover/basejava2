package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume("", uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - searchKey - 1);
        size--;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        checkOverflowException(r);
        int insertPoint = -(searchKey + 1);
        System.arraycopy(storage, insertPoint, storage, insertPoint + 1, size - insertPoint);
        storage[insertPoint] = r;
        size++;
    }
}