package webapp.storage;

import org.junit.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    /*@Test(expected = StorageException.class)
    public void saveOverflow() {
        saveCheckUnexpectedError();
        storage.save(new Resume(""));
    }*/
}