package edu.metro.brrandingauditor.model;

public class BrandingObservation {

    public enum Status {
        PASS,
        WARNING,
        INFO
    }

    private final String category;
    private final Status status;
    private final String observation;
    private final String detail;

    public BrandingObservation(
            String category,
            Status status,
            String observation,
            String detail) {

        this.category = category;
        this.status = status;
        this.observation = observation;
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public String getObservation() {
        return observation;
    }

    public String getDetail() {
        return detail;
    }
}