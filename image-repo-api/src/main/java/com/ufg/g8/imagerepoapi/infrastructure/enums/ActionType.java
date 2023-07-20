package com.ufg.g8.imagerepoapi.infrastructure.enums;

public enum ActionType {

    CREATE("Create"),
    READ("Read"),
    UPDATE("Update"),
    DELETE("Delete");

    private final String description;

    ActionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
