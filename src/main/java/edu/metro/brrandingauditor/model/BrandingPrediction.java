package edu.metro.brrandingauditor.model;

import java.util.List;

public class BrandingPrediction {

    private final int brandingScore;
    private final List<Recommendation> recommendations;


    public BrandingPrediction(
            int brandingScore,
            List<Recommendation> recommendations) {

        this.brandingScore = brandingScore;
        this.recommendations = recommendations;
    }


    public int getBrandingScore() {
        return brandingScore;
    }


    public List<Recommendation> getRecommendations() {
        return recommendations;
    }
}
