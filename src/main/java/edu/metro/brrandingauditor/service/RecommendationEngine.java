package edu.metro.brrandingauditor.service;

import edu.metro.brrandingauditor.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecommendationEngine {


    public BrandingPrediction predict(BrandingState state) {


        List<Recommendation> recommendations =
                new ArrayList<>();


        String content =
                state.getRepositoryContent();


        if (!content.toLowerCase()
                .contains("logo")) {

            recommendations.add(
                new Recommendation(
                    "Logo",
                    "No consistent logo detected",
                    "Add Learn and Help logo",
                    "HIGH"
                )
            );
        }


        recommendations.add(
            new Recommendation(
                "Typography",
                "Fonts may be inconsistent",
                "Standardize presentation fonts",
                "MEDIUM"
            )
        );


        recommendations.add(
            new Recommendation(
                "Layout",
                "Title styles vary",
                "Apply common title template",
                "MEDIUM"
            )
        );


        int score =
                Math.max(100 -
                recommendations.size() * 10, 0);


        return new BrandingPrediction(
                score,
                recommendations);
    }
}
