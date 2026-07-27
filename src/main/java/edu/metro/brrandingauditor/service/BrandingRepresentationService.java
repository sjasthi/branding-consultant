package edu.metro.brrandingauditor.service;

import edu.metro.brrandingauditor.model.BrandingState;
import org.springframework.stereotype.Service;


@Service
public class BrandingRepresentationService {


    public BrandingState encode(String repositoryContent) {

        /*
         * MuZero representation step:
         *
         * Raw repository data
         *        |
         *        v
         * Internal branding state
         */

        return new BrandingState(repositoryContent);
    }
}
