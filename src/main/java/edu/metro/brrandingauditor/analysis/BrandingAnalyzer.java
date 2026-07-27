package edu.metro.brrandingauditor.analysis;

import edu.metro.brrandingauditor.model.AnalysisResult;
import edu.metro.brrandingauditor.model.BrandingObservation;
import edu.metro.brrandingauditor.model.BrandingObservation.Status;
import edu.metro.brrandingauditor.report.RepositoryReport;

import java.util.ArrayList;
import java.util.List;

public class BrandingAnalyzer {

    public static AnalysisResult evaluate(RepositoryReport report) {
        List<BrandingObservation> observations = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();

        // 1. Logo Assessment
        if (report.getLogoCount() > 0) {
            observations.add(new BrandingObservation(
                    "Logo",
                    Status.PASS,
                    report.getLogoCount() + " logo asset(s) detected.",
                    "Primary visual branding element present."
            ));
        } else {
            observations.add(new BrandingObservation(
                    "Logo",
                    Status.WARNING,
                    "No logo or favicon asset found.",
                    "Repository lacks standard visual identifier (e.g., logo.png, favicon.ico)."
            ));
            recommendations.add("Create a standard brand logo (logo.png or logo.svg) in the project assets.");
        }

        // 2. README Assessment
        if (report.isHasReadme()) {
            observations.add(new BrandingObservation(
                    "README",
                    Status.PASS,
                    "README file found.",
                    "Repository identity documentation is present."
            ));
        } else {
            observations.add(new BrandingObservation(
                    "README",
                    Status.WARNING,
                    "No README file detected.",
                    "Missing landing page documentation."
            ));
            recommendations.add("Add a README.md file with product overview and usage guidelines.");
        }

        // 3. License Assessment
        if (report.isHasLicense()) {
            observations.add(new BrandingObservation(
                    "License",
                    Status.PASS,
                    "LICENSE file detected.",
                    "Usage rights are explicit."
            ));
        } else {
            observations.add(new BrandingObservation(
                    "License",
                    Status.WARNING,
                    "No LICENSE file detected.",
                    "Unclear usage terms can affect open-source adoption."
            ));
            recommendations.add("Add an explicit LICENSE file (e.g., MIT, Apache 2.0).");
        }

        // 4. Styling Assessment
        if (report.getCssCount() > 0) {
            observations.add(new BrandingObservation(
                    "Website Styling",
                    Status.PASS,
                    report.getCssCount() + " CSS stylesheet(s) found.",
                    "Custom styling layer present."
            ));
        } else if (report.getHtmlCount() > 0) {
            observations.add(new BrandingObservation(
                    "Website Styling",
                    Status.WARNING,
                    "HTML files detected without dedicated CSS stylesheets.",
                    "UI layout and brand presentation may lack consistent styling."
            ));
            recommendations.add("Standardize presentation styling using custom CSS design tokens.");
        } else {
            observations.add(new BrandingObservation(
                    "Website Styling",
                    Status.INFO,
                    "No web UI files detected.",
                    "Repository appears to be non-web or backend library code."
            ));
        }

        // 5. Brand Imagery Assessment
        if (report.getImageCount() > 0) {
            observations.add(new BrandingObservation(
                    "Brand Assets",
                    Status.PASS,
                    report.getImageCount() + " image asset(s) present.",
                    "Visual media support confirmed."
            ));
        } else {
            observations.add(new BrandingObservation(
                    "Brand Assets",
                    Status.WARNING,
                    "No visual imagery assets detected.",
                    "Documentation or UI may lack visual elements."
            ));
            recommendations.add("Add architecture diagrams, UI screenshots, or banner imagery.");
        }

        return new AnalysisResult(observations, recommendations);
    }

    // Preserved for backwards compatibility with textual reports
    public static String analyze(RepositoryReport report) {
        AnalysisResult result = evaluate(report);
        StringBuilder sb = new StringBuilder();

        sb.append("Branding Analysis Observations\n");
        sb.append("------------------------------\n");
        for (BrandingObservation obs : result.getObservations()) {
            sb.append(String.format("- [%s] %s: %s%n", obs.getStatus(), obs.getCategory(), obs.getObservation()));
            sb.append(String.format("  Detail: %s%n", obs.getDetail()));
        }

        sb.append("\nRecommendations\n");
        sb.append("----------------\n");
        if (result.getRecommendations().isEmpty()) {
            sb.append("- Repository satisfies core branding heuristics.\n");
        } else {
            for (String rec : result.getRecommendations()) {
                sb.append("- ").append(rec).append("\n");
            }
        }

        return sb.toString();
    }
}