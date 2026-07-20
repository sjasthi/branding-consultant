package edu.metro.brrandingauditor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class RepositoryScanner {

    public static String scan(Path root) throws IOException {

        final int[] counts = new int[2]; // index 0 = folders, index 1 = files
        final Map<String, Integer> extensionCounts = new HashMap<>();
        final List<String> errors = new ArrayList<>();

        Files.walkFileTree(root, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                if (!dir.equals(root)) {
                    counts[0]++;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                counts[1]++;

                String name = file.getFileName().toString();
                int dotIndex = name.lastIndexOf('.');

                String ext = (dotIndex > 0)
                        ? name.substring(dotIndex).toLowerCase()
                        : "[no ext]";

                extensionCounts.merge(ext, 1, Integer::sum);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {

                errors.add(
                        file.toString() +
                                " -> " +
                                exc.getClass().getSimpleName() +
                                ": " +
                                exc.getMessage()
                );

                return FileVisitResult.CONTINUE;
            }
        });

        StringBuilder sb = new StringBuilder();

        sb.append("Folders: ")
                .append(counts[0])
                .append("\n");

        sb.append("Files: ")
                .append(counts[1])
                .append("\n\n");


        new TreeMap<>(extensionCounts).forEach((ext, count) ->
                sb.append(ext)
                        .append(": ")
                        .append(count)
                        .append("\n")
        );


        if (!errors.isEmpty()) {
            sb.append("\n\nErrors: ")
                    .append(errors.size())
                    .append("\n\n");

            for (String error : errors) {
                sb.append(error)
                        .append("\n");
            }
        }

        return sb.toString();
    }
}