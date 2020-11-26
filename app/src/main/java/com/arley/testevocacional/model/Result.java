package com.arley.testevocacional.model;

public class Result {
    private Group group;
    private String name;
    private String description;
    private String professions;

    public Result(Group group, String name, String description, String professions) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.professions = professions;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessions() {
        return professions;
    }

    public void setProfessions(String professions) {
        this.professions = professions;
    }
}
