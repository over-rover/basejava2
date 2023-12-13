package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        int index = getSearchKey(r.getUuid());
        checkOverflowException(r);
        checkExistenceException(r.getUuid(), index);
        insertResume(r, index);
    }

    public abstract void clear();

    public final Resume get(String uuid) {
        int index = getSearchKey(uuid);
        checkAbsenceException(uuid, index);
        return getResume(index);
    }

    public final void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        checkAbsenceException(r.getUuid(), index);
        updateResume(r, index);
        System.out.println("Резюме " + r.getUuid() + " successfully updated");
    }

    public final void delete(String uuid) {
        int index = getSearchKey(uuid);
        checkAbsenceException(uuid, index);
        deleteResume(index);
        System.out.println("\nРезюме " + uuid + " successfully deleted.");
    }

    public abstract Resume[] getAll();

    public abstract int size();

    protected boolean isExist(int index) {
        return index >= 0;
    }

    protected void checkExistenceException(String uuid, int index) {
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected void checkAbsenceException(String uuid, int index) {
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume r, int index);

    protected abstract void checkOverflowException(Resume r);
}