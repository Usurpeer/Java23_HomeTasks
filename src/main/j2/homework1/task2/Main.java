package j2.homework1.task2;

import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Программа завершена. \nМассив аргументов пуст.");
            return;
        }

        String strPath = args[0];
        Path path = Paths.get(strPath);

        System.out.println("Введенный путь: " + path.toAbsolutePath().normalize());

        int maxDepth = findMaxDepth(path);
        System.out.println("Максимальная глубина вложенности: " + maxDepth);
    }

    private static int findMaxDepth(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Нулевое значение первого аргумента.");
        }

        if (Files.notExists(path) || Files.isRegularFile(path)) {
            return -1;
        }

        MaxDepthFileVisitor visitor = new MaxDepthFileVisitor();
        try {
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return visitor.getMaxDepth();
    }

}