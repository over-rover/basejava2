import webapp.model.Resume;
import webapp.storage.ArrayStorage;
import webapp.storage.ListStorage;
import webapp.storage.SortedArrayStorage;
import webapp.storage.Storage;

/**
 * Test for your webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    //static final Storage ARRAY_STORAGE = new ArrayStorage();
    //static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    static final Storage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid10");
        Resume r2 = new Resume("uuid25");
        Resume r3 = new Resume("uuid15");
        Resume r4 = new Resume("uuid20");

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