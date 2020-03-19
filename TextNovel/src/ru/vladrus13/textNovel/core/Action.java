package ru.vladrus13.textNovel.core;

public class Action {
    private final String text, who;
    private final String[] answers;

    private final Action[] actions;

    public Action(String text, String who, String[] answers, Action[] actions) {
        this.text = text;
        this.who = who;
        this.answers = answers;
        this.actions = actions;
    }

    public String[] getAnswers() {
        return answers;
    }

    public Action[] getActions() {
        return actions;
    }

    public String getText() {
        return text;
    }

    public String getWho() {
        return who;
    }
}
