package edu.metro.brrandingauditor.model;

public class Recommendation {

    private final String category;
    private final String issue;
    private final String action;
    private final String priority;

    public Recommendation(
            String category,
            String issue,
            String action,
            String priority) {

        this.category = category;
        this.issue = issue;
        this.action = action;
        this.priority = priority;
    }


    public String getCategory() {
        return category;
    }

    public String getIssue() {
        return issue;
    }

    public String getAction() {
        return action;
    }

    public String getPriority() {
        return priority;
    }
}
