package webapp.storage;

import webapp.storage.strategy.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}