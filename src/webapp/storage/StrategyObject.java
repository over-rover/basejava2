package webapp.storage;

import webapp.model.Resume;

import java.util.List;

public class StrategyObject {
    public Storage storage;

    public StrategyObject(Storage storage) {
        this.storage = storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void anyOptionalMethod() {

    }

    public void save(Resume r) {
        storage.save(r);
    }

    public void clear() {
        storage.clear();
    }

    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    public void update(Resume r) {
        storage.update(r);
    }

    public void delete(String uuid) {
        storage.delete(uuid);
    }

    public Resume[] getAll() {
        return storage.getAll();
    }

    public List<Resume> getAllSorted() {
        return storage.getAllSorted();
    }

    public int size() {
        return storage.size();
    }
}
