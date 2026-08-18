package edu.metro.branding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BrandingAnalyzer {

    public List<BrandingObservation> analyzeFile(Path file) throws IOException {
        List<BrandingObservation> observations = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("TODO") || line.contains("FIXME")) {
                observations.add(new BrandingObservation("WARNING", "Incomplete code marker found on line " + (i + 1), file.toString()));
            }
            if (line.toLowerCase().contains("branding")) {
                observations.add(new BrandingObservation("INFO", "Branding reference identified on line " + (i + 1), file.toString()));
            }
        }
        return observations;
    }
}
