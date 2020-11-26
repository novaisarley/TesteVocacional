package com.arley.testevocacional.model;

public class Alternative {
    private Group group;
    private String description;

    public Alternative(Group group, String description) {
        this.group = group;
        this.description = description;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
