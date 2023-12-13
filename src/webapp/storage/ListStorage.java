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

    protected int getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void checkOverflowException(Resume r) {
    }


    protected void deleteResume(int index) {
        storage.remove(index);
    }

    protected void insertResume(Resume r, int index) {
        storage.add(r);
    }

    protected final Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void updateResume(Resume r, int index) {
        storage.set(index, r);
    }
}