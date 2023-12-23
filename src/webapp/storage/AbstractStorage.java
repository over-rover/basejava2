package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public void save(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final Resume get(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public final List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(getAll());
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    public final void update(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
        System.out.println("Резюме " + r.getUuid() + " successfully updated");
    }

    public final void delete(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
        System.out.println("\nРезюме " + uuid + " successfully deleted.");
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract Resume[] getAll();

    public abstract void clear();

    public abstract int size();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    // Компаратор через анонимный класс
    /*Comparator<Resume> comparator = new Comparator<Resume>() {
        @Override
        public int compare(Resume r1, Resume r2) {
            return (r1.getFullName() + r1.getUuid()).compareTo(
                    r2.getFullName() + r2.getUuid());
        }
    };*/


    // Компаратор через лямбда-выражение
    /*Comparator<Resume> comparator = (r1, r2) ->
            (r1.getFullName() + r1.getUuid()).compareTo(
                    r2.getFullName() + r2.getUuid());*/

    // Идея предложила еще более короткую лямбду
    //Comparator<Resume> comparator = Comparator.comparing(r -> (r.getFullName() + r.getUuid()));


}