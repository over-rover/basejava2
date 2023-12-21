package webapp.storage;

import webapp.model.Resume;

import java.util.List;

public interface Storage {
    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void update(Resume r);

    void delete(String uuid);

    Resume[] getAll();

    List<Resume> getAllSorted();

    int size();
}