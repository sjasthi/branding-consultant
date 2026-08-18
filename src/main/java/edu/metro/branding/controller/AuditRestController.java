package edu.metro.branding.controller;

import edu.metro.branding.dto.AuditDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit")
public class AuditRestController {

    @PostMapping("/run")
    public ResponseEntity<AuditDto> runRestAudit(@RequestBody AuditDto dto) {
        dto.setStatus("COMPLETED");
        return ResponseEntity.ok(dto);
    }
}
