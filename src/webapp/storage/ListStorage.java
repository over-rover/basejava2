package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    //если придет null, что произойдет?
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    protected void doSave(Resume r, Integer searchKey) {
        storage.add(r);
    }

    protected final Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage.set(searchKey, r);
    }
}