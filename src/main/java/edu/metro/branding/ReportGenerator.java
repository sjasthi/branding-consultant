package edu.metro.branding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ReportGenerator {

    public static void writeReport(Path outputPath, RepositoryReport report, List<BrandingObservation> observations) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BRANDING AUDIT REPORT ===\n");
        sb.append("Total Files Scanned: ").append(report.scannedFilesCount()).append("\n");
        sb.append("Total Observations: ").append(observations.size()).append("\n\n");

        for (BrandingObservation obs : observations) {
            sb.append("[").append(obs.level()).append("] ")
              .append(obs.filePath()).append(" -> ")
              .append(obs.message()).append("\n");
        }

        Files.writeString(outputPath, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
