package webapp.storage;

import webapp.storage.strategy.PathIO;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new PathIO()));
    }
}