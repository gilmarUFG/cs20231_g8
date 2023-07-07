package com.ufg.g8.imagerepoapi.infrastructure.enums;

public enum ReportReason {

    PORNOGRAPHY("Conteúdo pornográfico"),
    VIOLENCE("Violência ou conteúdo gráfico"),
    DISCRIMINATION("Discurso de ódio ou discriminação"),
    COPYRIGHT_INFRINGEMENT("Violação de direitos autorais"),
    SPAM("Conteúdo de spam ou publicidade não autorizada"),
    PRIVACY_INVASION("Invasão de privacidade"),
    OTHER("Outro motivo");

    private final String description;

    ReportReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}