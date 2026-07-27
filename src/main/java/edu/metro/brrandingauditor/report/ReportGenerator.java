package edu.metro.brrandingauditor.report;

import edu.metro.brrandingauditor.analysis.BrandingAnalyzer;
import edu.metro.brrandingauditor.model.AnalysisResult;
import edu.metro.brrandingauditor.model.BrandingObservation;

import java.nio.file.Files;
import java.nio.file.Path;

public class ReportGenerator {

    public static String generate(RepositoryReport repo) {
        AnalysisResult analysis = BrandingAnalyzer.evaluate(repo);
        StringBuilder sb = new StringBuilder();

        sb.append("========================================================\n");
        sb.append("                AI BRANDING AUDITOR REPORT              \n");
        sb.append("========================================================\n\n");

        sb.append("1. Repository Summary\n");
        sb.append("---------------------\n");
        sb.append("Scan Duration : ").append(repo.getScanTimeMs()).append(" ms\n");
        sb.append("Folders       : ").append(repo.getFolders()).append("\n");
        sb.append("Files         : ").append(repo.getFiles()).append("\n\n");

        sb.append("File Types Breakdown:\n");
        sb.append("  - HTML Files     : ").append(repo.getHtmlCount()).append("\n");
        sb.append("  - CSS Files      : ").append(repo.getCssCount()).append("\n");
        sb.append("  - JS Files       : ").append(repo.getJavaScriptCount()).append("\n");
        sb.append("  - Markdown Files : ").append(repo.getMarkdownCount()).append("\n");
        sb.append("  - Image Assets   : ").append(repo.getImageCount()).append("\n\n");

        sb.append("Extension Breakdown:\n");
        repo.getExtensions().forEach((ext, count) ->
                sb.append("  ").append(ext).append(": ").append(count).append("\n")
        );

        sb.append("\n2. Repository Metadata\n");
        sb.append("----------------------\n");
        sb.append("README Present  : ").append(repo.isHasReadme() ? "YES" : "NO").append("\n");
        sb.append("LICENSE Present : ").append(repo.isHasLicense() ? "YES" : "NO").append("\n");
        sb.append("Logos Detected  : ").append(repo.getLogoCount()).append("\n");

        sb.append("\n3. Branding Analysis\n");
        sb.append("--------------------\n");
        for (BrandingObservation obs : analysis.getObservations()) {
            sb.append(String.format(" [%-7s] %-16s : %s%n", obs.getStatus(), obs.getCategory(), obs.getObservation()));
            sb.append("           Detail           : ").append(obs.getDetail()).append("\n");
        }

        sb.append("\n4. FP8 AI Recommendations\n");
        sb.append("-------------------------\n");
        if (analysis.getRecommendations().isEmpty()) {
            sb.append(" [PASS] Repository satisfies core branding heuristics.\n");
        } else {
            int idx = 1;
            for (String rec : analysis.getRecommendations()) {
                sb.append(" ").append(idx++).append(". ").append(rec).append("\n");
            }
        }

        return sb.toString();
    }

    public static void generateTextReport(RepositoryReport repo) throws Exception {
        Files.createDirectories(Path.of("reports"));

        Files.writeString(
                Path.of("reports/repository-report.txt"),
                generate(repo)
        );
    }
}