package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid10";
    private static final String UUID_2 = "uuid25";
    private static final String UUID_3 = "uuid15";
    private static final String DUMMY = "dummy";

    private final Resume r1 = new Resume(UUID_1);
    private final Resume r2 = new Resume(UUID_2);
    private final Resume r3 = new Resume(UUID_3);
    private final Resume dummy = new Resume(DUMMY);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = 3; i <= 10_000; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() {
        storage.save(storage.get(UUID_2));
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test
    public void get() {
        Assert.assertEquals(r2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(dummy);
    }

    @Test
    public void update() {
        storage.update(r2);
        Assert.assertEquals(r2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}