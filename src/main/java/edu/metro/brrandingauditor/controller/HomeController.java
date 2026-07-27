package edu.metro.brrandingauditor.controller;

import edu.metro.brrandingauditor.RepositoryScanner;
import edu.metro.brrandingauditor.analysis.BrandingAnalyzer;
import edu.metro.brrandingauditor.report.ReportGenerator;
import edu.metro.brrandingauditor.report.RepositoryReport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class HomeController {

    private RepositoryReport lastRepositoryReport;
    private String repositoryPath = "";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("repositoryPath", repositoryPath);
        return "index";
    }

    @PostMapping("/scan")
    public String scan(
            @RequestParam(value = "repositoryPath", required = false) String path,
            Model model) {

        // Check if path was left blank
        if (path == null || path.trim().isEmpty()) {
            model.addAttribute("result", "Please enter a repository directory path to scan.");
            model.addAttribute("repositoryPath", "");
            return "index";
        }

        Path targetPath = Path.of(path.trim());

        // Validate that the path exists and is a valid directory
        if (!Files.exists(targetPath) || !Files.isDirectory(targetPath)) {
            model.addAttribute("result", "Error: Specified path does not exist or is not a directory.\nPlease enter a valid folder path (e.g., C:\\Projects\\my-repo).");
            model.addAttribute("repositoryPath", path);
            return "index";
        }

        try {
            repositoryPath = path.trim();
            lastRepositoryReport = RepositoryScanner.scan(targetPath);

            double scanSeconds = lastRepositoryReport.getScanTimeMs() / 1000.0;

            StringBuilder resultText = new StringBuilder();
            resultText.append("Repository scanned successfully\n\n");
            resultText.append("Repository Summary\n");
            resultText.append("------------------\n");
            resultText.append("Folders: ").append(lastRepositoryReport.getFolders()).append("\n");
            resultText.append("Files: ").append(lastRepositoryReport.getFiles()).append("\n");
            resultText.append("Scan Time: ").append(String.format("%.3f", scanSeconds)).append(" seconds\n\n");

            resultText.append("Repository Metadata\n");
            resultText.append("-------------------\n");
            resultText.append("README: ").append(lastRepositoryReport.isHasReadme() ? "Found" : "Missing").append("\n");
            resultText.append("LICENSE: ").append(lastRepositoryReport.isHasLicense() ? "Found" : "Missing").append("\n");
            resultText.append("Logos Detected: ").append(lastRepositoryReport.getLogoCount()).append("\n\n");

            resultText.append(BrandingAnalyzer.analyze(lastRepositoryReport));

            model.addAttribute("result", resultText.toString());

        } catch (Exception e) {
            model.addAttribute("result", "Scan failed: " + e.getMessage());
        }

        model.addAttribute("repositoryPath", repositoryPath);
        return "index";
    }

    @PostMapping("/report")
    public String report(Model model) {

        try {
            if (lastRepositoryReport == null) {
                model.addAttribute("result", "Please scan a repository first.");
                model.addAttribute("repositoryPath", repositoryPath);
                return "index";
            }

            ReportGenerator.generateTextReport(lastRepositoryReport);

            model.addAttribute(
                    "result",
                    "Report created successfully\n\n" +
                    "File:\n" +
                    "reports/repository-report.txt"
            );

        } catch (Exception e) {
            model.addAttribute("result", "Report failed: " + e.getMessage());
        }

        model.addAttribute("repositoryPath", repositoryPath);
        return "index";
    }
}