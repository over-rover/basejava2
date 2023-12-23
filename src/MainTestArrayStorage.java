import webapp.model.Resume;
import webapp.storage.*;

/**
 * Test for your webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //static final Storage ARRAY_STORAGE = new ArrayStorage();
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    //static final Storage ARRAY_STORAGE = new ListStorage();
    //static final Storage ARRAY_STORAGE = new MapUuidStorage();
    //static final Storage ARRAY_STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("name10", "uuid10");
        Resume r2 = new Resume("name25", "uuid25");
        Resume r3 = new Resume("name15", "uuid15");
        Resume r4 = new Resume("name20", "uuid20");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        System.out.println("Update r1: ");
        ARRAY_STORAGE.update(r1);
        /*System.out.println("Update dummy: ");
        Resume dummy = new Resume("dummy");
        ARRAY_STORAGE.update(dummy);*/

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        //ARRAY_STORAGE.delete(new Resume("dummy").getUuid());

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}