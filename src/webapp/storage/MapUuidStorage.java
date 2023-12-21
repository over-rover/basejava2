package webapp.storage;

import webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
        System.out.println("\nВыполнена очистка HashMap");
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage.values());
        list.sort(comparator);
        return list;
    }

    public int size() {
        return storage.size();
    }

    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    protected void doSave(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    protected final Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((String) searchKey, r);
    }
}