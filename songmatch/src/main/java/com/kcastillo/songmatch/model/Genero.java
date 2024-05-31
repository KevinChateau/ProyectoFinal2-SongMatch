package com.kcastillo.songmatch.model;

public enum Genero {
    ROCK("Rock"),
    BALADA("Balada"),
    POP("Pop"),
    BOLERO("Bolero"),
    INDIE("Indie");

    private String SpanishName;

    Genero(String spanishName) {
        SpanishName = spanishName;
    }

    public static Genero fromString(String text) {
        for(Genero genero: Genero.values()){
            if (genero.SpanishName.equalsIgnoreCase(text)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría encontrada: " + text);
    }

}
