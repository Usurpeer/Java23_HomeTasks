package j2.homework1.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathDifference {
    public static List<PathDifferenceStatus> difference(Path path1, Path path2) {
        if (path1 == null) {
            throw new IllegalArgumentException("Нулевое значение первого аргумента.");
        }
        if (path2 == null) {
            throw new IllegalArgumentException("Нулевое значение второго аргумента.");
        }

        List<PathDifferenceStatus> a = new ArrayList<>();

        if (isNotExists(path1, path2)) {
            a.add(PathDifferenceStatus.NotExists);
        }
        if (isSameFIle(path1, path2)) {
            a.add(PathDifferenceStatus.SameFIle);
        }
        if (isBiggerFile(path1, path2)) {
            a.add(PathDifferenceStatus.BiggerFile);
        }
        if (isSmallerFile(path1, path2)) {
            a.add(PathDifferenceStatus.SmallerFile);
        }
        if (isSameSizeFile(path1, path2)) {
            a.add(PathDifferenceStatus.SameSizeFile);
        }
        if (isSameDirectory(path1, path2)) {
            a.add(PathDifferenceStatus.SameDirectory);
        }
        if (isSameAbsoluteNameDepth(path1, path2)) {
            a.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        }
        if (isSamePrefix(path1, path2)) {
            a.add(PathDifferenceStatus.SamePrefix);
        }
        if (isSameRoot(path1, path2)) {
            a.add(PathDifferenceStatus.SameRoot);
        }
        if (isSubPath(path1, path2)) {
            a.add(PathDifferenceStatus.SubPath);
        }
        if (isParentPath(path1, path2)) {
            a.add(PathDifferenceStatus.ParentPath);
        }

        return a;
    }

    // хотя бы один из двух путей ведет к несуществующему файлу/каталогу
    public static boolean isNotExists(Path path1, Path path2) {
        if (path1 == null) {
            throw new IllegalArgumentException("Нулевое значение первого аргумента.");
        }
        if (path2 == null) {
            throw new IllegalArgumentException("Нулевое значение второго аргумента.");
        }

        return Files.notExists(path1) || Files.notExists(path2);
    }

    // Оба пути ведут к одному файлу
    public static boolean isSameFIle(Path path1, Path path2) {
        if (path1 == null) {
            throw new IllegalArgumentException("Нулевое значение первого аргумента.");
        }
        if (path2 == null) {
            throw new IllegalArgumentException("Нулевое значение второго аргумента.");
        }

        try {
            Path absolutePath1 = path1.toAbsolutePath().normalize();
            Path absolutePath2 = path2.toAbsolutePath().normalize();

            return Files.isSameFile(absolutePath1, absolutePath2);
        } catch (IOException e) {
            return false;
        }
    }

    // оба пути ведут к файлу, при этом размер path1 > path2
    public static boolean isBiggerFile(Path path1, Path path2) {
        if (path1 == null) {
            throw new IllegalArgumentException("Нулевое значение первого аргумента.");
        }
        if (path2 == null) {
            throw new IllegalArgumentException("Нулевое значение второго аргумента.");
        }

        try {
            if (Files.isRegularFile(path1) && Files.isRegularFile(path2)) {
                return Files.size(path1) > Files.size(path2);
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    // оба пути ведут к файлу, при этом размер path1 < path2
    public static boolean isSmallerFile(Path path1, Path path2) {
        try {
            if (Files.isRegularFile(path1) && Files.isRegularFile(path2)) {
                return Files.size(path1) < Files.size(path2);
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    // оба пути ведут к файлу, при этом размеры файлов совпадают
    public static boolean isSameSizeFile(Path path1, Path path2) {
        try {
            if (Files.isRegularFile(path1) && Files.isRegularFile(path2)) {
                return Files.size(path1) == Files.size(path2);
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    // оба пути ведут к одной директории
    public static boolean isSameDirectory(Path path1, Path path2) {
        Path normalizedPath1 = path1.toAbsolutePath().normalize();
        Path normalizedPath2 = path2.toAbsolutePath().normalize();

        if (Files.isDirectory(normalizedPath1) && Files.isDirectory(normalizedPath2)) {
            return normalizedPath1.equals(normalizedPath2);
        }
        return false;
    }

    // глубина (число частей) абсолютного пути к файлам одинаковая
    public static boolean isSameAbsoluteNameDepth(Path path1, Path path2) {
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = path2.toAbsolutePath().normalize();

        int depth1 = absolutePath1.getNameCount();
        int depth2 = absolutePath2.getNameCount();

        return depth1 == depth2;
    }

    // пути имеют общее начало (не считая корня)
    public static boolean isSamePrefix(Path path1, Path path2) {
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = path2.toAbsolutePath().normalize();

        Path subPath1 = absolutePath1.subpath(0, 1);
        Path subPath2 = absolutePath2.subpath(0, 1);

        return subPath1.equals(subPath2);
    }

    // пути имеют общий корень
    public static boolean isSameRoot(Path path1, Path path2) {
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = path2.toAbsolutePath().normalize();

        Path root1 = absolutePath1.getRoot();
        Path root2 = absolutePath2.getRoot();

        if (root1 != null && root2 != null) {
            return root1.equals(root2);
        }

        return false;
    }

    // path2 находится внутри path1
    public static boolean isSubPath(Path path1, Path path2) {
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = path2.toAbsolutePath().normalize();

        return absolutePath2.startsWith(absolutePath1) && !absolutePath2.endsWith(absolutePath1);
    }

    // path1 находится внутри path2
    public static boolean isParentPath(Path path1, Path path2) {
        Path absolutePath1 = path1.toAbsolutePath().normalize();
        Path absolutePath2 = path2.toAbsolutePath().normalize();

        return absolutePath1.startsWith(absolutePath2) && !absolutePath1.endsWith(absolutePath2);
    }
}
