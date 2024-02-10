package webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import maintest.MainResumeTestData;
import webapp.model.Resume;

import java.io.File;
import java.util.Arrays;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File(".\\storagedir");
    protected final Storage storage;
    private static final String FULL_NAME_1 = "name10";
    private static final String UUID_1 = "uuid10";
    private static final String FULL_NAME_2 = "name25";
    private static final String UUID_2 = "uuid25";
    private static final String FULL_NAME_3 = "name15";
    private static final String UUID_3 = "uuid15";
    private static final String FULL_NAME_4 = "name20";
    private static final String UUID_4 = "uuid20";

    private static final String DUMMY_NAME = "dummy_name";
    private static final String DUMMY_UUID = "dummy_uuid";

    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    private static final Resume DUMMY_RESUME;

    private static final Resume[] INITIAL_RESUMES;
    private static final int INITIAL_SIZE;

    static {
        /*R1 = new Resume(FULL_NAME_1, UUID_1);
        R2 = new Resume(FULL_NAME_2, UUID_2);
        R3 = new Resume(FULL_NAME_3, UUID_3);
        R4 = new Resume(FULL_NAME_4, UUID_4);
        DUMMY_RESUME = new Resume(DUMMY_NAME, DUMMY_UUID);*/
        R1 = MainResumeTestData.autoFillContent(FULL_NAME_1, UUID_1);
        R2 = MainResumeTestData.autoFillContent(FULL_NAME_2, UUID_2);
        R3 = MainResumeTestData.autoFillContent(FULL_NAME_3, UUID_3);
        R4 = MainResumeTestData.autoFillContent(FULL_NAME_4, UUID_4);
        DUMMY_RESUME = MainResumeTestData.autoFillContent(DUMMY_NAME, DUMMY_UUID);

        /*R1 = MainResumeTestData.fillContent(FULL_NAME_1, UUID_1);
        R2 = MainResumeTestData.fillContent(FULL_NAME_2, UUID_2);
        R3 = MainResumeTestData.fillContent(FULL_NAME_3, UUID_3);
        R4 = MainResumeTestData.fillContent(FULL_NAME_4, UUID_4);
        DUMMY_RESUME = MainResumeTestData.fillContent(DUMMY_NAME, DUMMY_UUID);*/


        INITIAL_RESUMES = new Resume[]{R1, R2, R3};
        INITIAL_SIZE = INITIAL_RESUMES.length;
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before

    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Ignore
    @Test
    public void saveCheckUnexpectedError() {
        storage.clear();
        try {
            for (int i = 0; i < 10_000; i++) {
                storage.save(new Resume(""));
            }
        } catch (Exception ignored) {
            Assert.fail("Непредвиденная ошибка при добавлении резюме в хранилище");
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() {
        storage.save(R2);
    }

    @Test
    public void save() {
        storage.save(R4);
        assertGet(R4);
        assertSize(INITIAL_SIZE + 1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        assertGet(DUMMY_RESUME);
    }

    @Test
    public void get() {
        for (Resume resume : INITIAL_RESUMES) {
            assertGet(resume);
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(DUMMY_RESUME);
    }

    @Test
    public void update() {
        Resume resume = new Resume(FULL_NAME_2, UUID_2);
        storage.update(resume);
        //Assert.assertSame(resume, storage.get(UUID_2));
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY_UUID);
        assertSize(INITIAL_SIZE);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(INITIAL_SIZE - 1);
        assertGet(R2);
    }

    @Test
    public void getAll() {
        Arrays.sort(INITIAL_RESUMES);
        Resume[] actual = storage.getAll();
        Arrays.sort(actual);
        Assert.assertArrayEquals(INITIAL_RESUMES, actual);
    }

    @Test
    public void getAllSorted() {
        Arrays.sort(INITIAL_RESUMES);
        Resume[] actual = storage.getAllSorted().toArray(new Resume[0]);
        Assert.assertArrayEquals(INITIAL_RESUMES, actual);
    }

    @Test
    public void size() {
        assertSize(INITIAL_SIZE);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume r = storage.get(resume.getUuid());
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}