package edu.metro.brrandingauditor.report;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class RepositoryReport {

    private final int folders;
    private final int files;
    private final Map<String,Integer> extensions;

    private final long scanTimeMs;

    private final boolean hasReadme;
    private final boolean hasLicense;

    private final int logoCount;
    private final int imageCount;

    private final int htmlCount;
    private final int cssCount;
    private final int javaScriptCount;
    private final int markdownCount;


    public RepositoryReport(
            int folders,
            int files,
            Map<String,Integer> extensions,
            long scanTimeMs,
            boolean hasReadme,
            boolean hasLicense,
            int logoCount,
            int imageCount,
            int htmlCount,
            int cssCount,
            int javaScriptCount,
            int markdownCount) {

        this.folders = folders;
        this.files = files;
        this.extensions =
                Collections.unmodifiableMap(
                        new TreeMap<>(extensions));

        this.scanTimeMs = scanTimeMs;
        this.hasReadme = hasReadme;
        this.hasLicense = hasLicense;
        this.logoCount = logoCount;
        this.imageCount = imageCount;
        this.htmlCount = htmlCount;
        this.cssCount = cssCount;
        this.javaScriptCount = javaScriptCount;
        this.markdownCount = markdownCount;
    }


    public int getFolders() {
        return folders;
    }

    public int getFiles() {
        return files;
    }

    public Map<String,Integer> getExtensions() {
        return extensions;
    }

    public long getScanTimeMs() {
        return scanTimeMs;
    }

    public boolean isHasReadme() {
        return hasReadme;
    }

    public boolean isHasLicense() {
        return hasLicense;
    }

    public int getLogoCount() {
        return logoCount;
    }

    public int getImageCount() {
        return imageCount;
    }

    public int getHtmlCount() {
        return htmlCount;
    }

    public int getCssCount() {
        return cssCount;
    }

    public int getJavaScriptCount() {
        return javaScriptCount;
    }

    public int getMarkdownCount() {
        return markdownCount;
    }
}