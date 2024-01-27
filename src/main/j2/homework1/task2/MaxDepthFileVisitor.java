package j2.homework1.task2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MaxDepthFileVisitor implements FileVisitor<Path> {
    private int maxDepth = 0;
    private int currentDepth = 0;

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        currentDepth++;
        maxDepth = Math.max(currentDepth, maxDepth);

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        currentDepth--;
        return FileVisitResult.CONTINUE;
    }
}