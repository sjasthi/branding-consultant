package edu.metro.branding;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class RepositoryScanner {

    public List<Path> scanDirectory(Path rootPath) throws IOException {
        List<Path> foundFiles = new ArrayList<>();
        Files.walkFileTree(rootPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".java") || file.toString().endsWith(".html") || file.toString().endsWith(".properties")) {
                    foundFiles.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return foundFiles;
    }
}
