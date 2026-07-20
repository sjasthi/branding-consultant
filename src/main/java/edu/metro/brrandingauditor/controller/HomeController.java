package edu.metro.brrandingauditor.controller;

import edu.metro.brrandingauditor.RepositoryScanner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "AI Branding Auditor is running!");
        return "index";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }

    @PostMapping("/scan")
    public String scan(@RequestParam String folder, Model model) {

        model.addAttribute("message", "AI Branding Auditor is running!");

        try {
            String result = RepositoryScanner.scan(Path.of(folder));
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("result", "Error:\n" + e.getMessage());
        }

        return "index";
    }
}
