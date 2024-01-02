package webapp.storage;

import webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    protected final Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
        System.out.println("\nВыполнена очистка HashMap");
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

    protected String getSearchKey(String uuid) {
        return uuid;
    }

    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    protected void doSave(Resume r, String searchKey) {
        storage.put(searchKey, r);
    }

    protected final Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        storage.replace(searchKey, r);
    }
}