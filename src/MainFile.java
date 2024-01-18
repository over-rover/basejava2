import java.io.File;
import java.io.IOException;

public class MainFile {
    private static final StringBuilder indent = new StringBuilder();

    public static void main(String[] args) {
        String filePath = ".\\src";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File obj = new File(".\\src");
        System.out.println(obj.getName());
        printDirectoryContent(obj);
    }

    // рекурсивный обход (суб)директорий  и вывод в консоль c иерархическими отступами
    public static void printDirectoryContent(File obj) {
        if (obj.isFile())
            return;
        File[] objects = obj.listFiles();
        if (objects != null) {
            indent.append(" ");
            for (File element : objects) {
                System.out.println(indent + element.getName());
                printDirectoryContent(element);
            }
            indent.deleteCharAt(indent.length() - 1);
        }
    }
}