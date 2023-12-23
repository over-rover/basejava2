package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
        System.out.println("\nВыполнена очистка ListStorage");
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    protected final Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set((int) searchKey, r);
    }
}