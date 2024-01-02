package webapp.storage;

import webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
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

    // Resume-type searchKey. key будем вытаскивать оттуда
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    protected void doDelete(Resume searchKey) {
        storage.remove((searchKey).getUuid());
    }

    //по ключу работать не получается, т.к. при первом вызове он нуль, put бросает исключение
    protected void doSave(Resume r, Resume searchKey) {
        storage.put(r.getUuid(), r);
    }

    protected final Resume doGet(Resume searchKey) {
        return storage.get((searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume searchKey) {
        storage.replace((searchKey).getUuid(), r);
    }
}