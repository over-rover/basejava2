package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    public static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public final List<Resume> getAllSorted() {
        LOG.info("Get all sorted ");
        List<Resume> list = Arrays.asList(getAll());
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    public final void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getNotExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
        System.out.println("Резюме " + r.getUuid() + " successfully updated");
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
        System.out.println("\nРезюме " + uuid + " successfully deleted.");
    }

    protected SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " уже существует в хранилище. SAVE impossible");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " отсутствует в хранилище.");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract Resume[] getAll();

    public abstract void clear();

    public abstract int size();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

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