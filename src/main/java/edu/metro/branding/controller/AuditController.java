package edu.metro.branding.controller;

import edu.metro.branding.dto.AuditDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuditController {

    @GetMapping("/audit")
    public String showAuditForm(Model model) {
        model.addAttribute("auditDto", new AuditDto());
        return "index";
    }

    @PostMapping("/audit")
    public String executeAudit(@ModelAttribute AuditDto auditDto, Model model) {
        model.addAttribute("message", "Audit executed for target: " + auditDto.getTargetPath());
        return "dashboard";
    }
}
