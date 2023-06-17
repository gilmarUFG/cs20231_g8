package com.ufg.g8.imagerepoapi.infrastructure.enums;

public enum TagColors {

    BLACK("black", "white");

    private String backgroundColor;

    private String textColor;

    TagColors(String backgroundColor, String textColor) {
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
