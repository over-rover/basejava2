import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\src";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File obj = new File(".\\src\\webapp");
        //printDirectoryContent(obj);

        try (FileInputStream fis = new FileInputStream(".\\.gitignore")) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // рекурсивный обход (суб)директорий
    public static void printDirectoryContent(File obj) {
        File[] objects = obj.listFiles();

        if (obj.isDirectory() && objects != null) {
            System.out.println(obj + " содержит:");
            for (File dir : objects) {
                System.out.println(dir.getName());
            }
            System.out.println("***********************************");

            for (File element : objects) {
                printDirectoryContent(element);
            }
        }
    }
}