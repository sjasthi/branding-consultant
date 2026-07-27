package edu.metro.brrandingauditor.model;

import java.util.List;

public class AnalysisResult {

    private final List<BrandingObservation> observations;
    private final List<String> recommendations;


    public AnalysisResult(
            List<BrandingObservation> observations,
            List<String> recommendations) {

        this.observations = observations;
        this.recommendations = recommendations;
    }


    public List<BrandingObservation> getObservations() {
        return observations;
    }


    public List<String> getRecommendations() {
        return recommendations;
    }
}