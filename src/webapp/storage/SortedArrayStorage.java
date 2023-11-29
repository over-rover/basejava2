package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void deleteLogic(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected void saveLogic(Resume r, int index) {
        int insertPoint = -(index + 1);
        for (int i = size; i > insertPoint; i--) {
            storage[i] = storage[i - 1];
        }
        storage[insertPoint] = r;
    }
}