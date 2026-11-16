public enum TypeEnvironnement {
    TERRESTRE, AQUATIQUE, AERIEN, SAVANE, FORET, POLAIRE, DESERTIQUE;

    // Convertit un texte en constante, retourne null si le texte est invalide
    public static TypeEnvironnement depuisTexte(String texte) {
        for (TypeEnvironnement t : values()) {
            if (t.name().equalsIgnoreCase(texte)) {
                return t;
            }
        }
        return null;
    }
}
