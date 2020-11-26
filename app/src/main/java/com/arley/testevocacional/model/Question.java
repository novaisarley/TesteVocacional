package com.arley.testevocacional.model;

import java.util.ArrayList;

public class Question {
    private Integer group_number;
    private String description;
    private final ArrayList<Alternative> alternatives;

    public Question(Integer group_number, String description, ArrayList<Alternative> alternatives) {
        this.group_number = group_number;
        this.description = description;
        this.alternatives = alternatives;
    }

    public Integer getGroup_number() {
        return group_number;
    }

    public void setGroup_number(Integer group_number) {
        this.group_number = group_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Alternative> getAlternatives() {
        return alternatives;
    }

    public void addAlternative(Alternative alternative) {
        this.alternatives.add(alternative);
    }
}
