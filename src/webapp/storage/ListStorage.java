package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected final ArrayList<Resume> storage = new ArrayList<>();

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
        //return storage.indexOf(new Resume(uuid));
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

    protected void doDelete(Object index) {
        storage.remove((int) index);
    }

    protected void doSave(Resume r, Object index) {
        storage.add(r);
    }

    protected final Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set((int) index, r);
    }
}