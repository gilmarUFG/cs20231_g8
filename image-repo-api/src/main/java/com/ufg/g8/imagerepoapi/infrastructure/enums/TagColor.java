package com.ufg.g8.imagerepoapi.infrastructure.enums;

public enum TagColor {

    BLACK("black", "white");

    private final String backgroundColor;

    private final String textColor;

    TagColor(String backgroundColor, String textColor) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }
}
