package j2.homework1.task1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path p1 = Paths.get("/File1/file2/file3");
        Path p2 = Paths.get("/File1/file2");;

        System.out.println("\nПути" + "\n\tp1: " + p1 + "\n\tp2: " + p2 + "\n");

        var list = PathDifference.difference(p1, p2);

        for (PathDifferenceStatus a: list) {
            System.out.println(a.getDescription());
        }

         p1 = Paths.get("/File1/file2");
         p2 = Paths.get("/File1/file2/file3");;

        System.out.println("\nПути" + "\n\tp1: " + p1 + "\n\tp2: " + p2 + "\n");

        list = PathDifference.difference(p1, p2);

        for (PathDifferenceStatus a: list) {
            System.out.println(a.getDescription());
        }

        p1 = Paths.get("/File1/file2/file3");;
        p2 = Paths.get("/File1/file2/file3");;

        System.out.println("\nПути" + "\n\tp1: " + p1 + "\n\tp2: " + p2 + "\n");

        list = PathDifference.difference(p1, p2);

        for (PathDifferenceStatus a: list) {
            System.out.println(a.getDescription());
        }
    }
}
