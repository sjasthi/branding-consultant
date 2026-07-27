package edu.metro.brrandingauditor;

import edu.metro.brrandingauditor.report.RepositoryReport;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class RepositoryScanner {

    private static final Set<String> IMAGE_EXTENSIONS = Set.of("png", "jpg", "jpeg", "svg", "gif", "webp", "ico");

    public static RepositoryReport scan(Path root) throws IOException {
        long startTime = System.currentTimeMillis();

        ScanContext ctx = new ScanContext();

        Files.walkFileTree(root, new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                if (!dir.equals(root)) {
                    ctx.folders++;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                ctx.files++;
                String fileName = file.getFileName().toString();
                String lowerName = fileName.toLowerCase();

                int dot = fileName.lastIndexOf(".");
                String ext = (dot > 0 && dot < fileName.length() - 1)
                        ? fileName.substring(dot + 1).toLowerCase()
                        : "[no ext]";

                ctx.extensions.merge(ext, 1, Integer::sum);

                // Detect Core Metadata Files
                if (lowerName.startsWith("readme")) ctx.hasReadme = true;
                if (lowerName.startsWith("license") || lowerName.startsWith("licence")) ctx.hasLicense = true;

                // Detect Branding Assets
                if (lowerName.contains("logo") || lowerName.equals("favicon.ico")) ctx.logoCount++;
                if (IMAGE_EXTENSIONS.contains(ext)) ctx.imageCount++;

                // Tech Stack & File Count Breakdown
                switch (ext) {
                    case "html", "htm" -> ctx.htmlCount++;
                    case "css" -> ctx.cssCount++;
                    case "js", "mjs", "jsx" -> ctx.javaScriptCount++;
                    case "md", "markdown" -> ctx.markdownCount++;
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });

        long elapsedTime = System.currentTimeMillis() - startTime;

        return new RepositoryReport(
                ctx.folders,
                ctx.files,
                ctx.extensions,
                elapsedTime,
                ctx.hasReadme,
                ctx.hasLicense,
                ctx.logoCount,
                ctx.imageCount,
                ctx.htmlCount,
                ctx.cssCount,
                ctx.javaScriptCount,
                ctx.markdownCount
        );
    }

    private static class ScanContext {
        int folders = 0;
        int files = 0;
        Map<String, Integer> extensions = new HashMap<>();

        boolean hasReadme = false;
        boolean hasLicense = false;

        int logoCount = 0;
        int imageCount = 0;

        int htmlCount = 0;
        int cssCount = 0;
        int javaScriptCount = 0;
        int markdownCount = 0;
    }
}