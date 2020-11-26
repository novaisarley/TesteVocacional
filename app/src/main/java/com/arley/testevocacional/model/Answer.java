package com.arley.testevocacional.model;

public class Answer {
    private Question question;
    private Group group;

    public Answer(Question question, Group group) {
        this.question = question;
        this.group = group;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
