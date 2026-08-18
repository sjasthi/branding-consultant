package edu.metro.branding.dto;

public class AuditDto {
    private String targetPath;
    private String status;

    public AuditDto() {}

    public AuditDto(String targetPath, String status) {
        this.targetPath = targetPath;
        this.status = status;
    }

    public String getTargetPath() { return targetPath; }
    public void setTargetPath(String targetPath) { this.targetPath = targetPath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
