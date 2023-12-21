package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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

    // Resume-type searchKey. key будем вытаскивать оттуда
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    //по ключу работать не получается, т.к. при первом вызове он нуль, put бросает исключение
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    protected final Resume doGet(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace(((Resume) searchKey).getUuid(), r);
    }
}