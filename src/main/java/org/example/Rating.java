package org.example;

public enum Rating {
    XUAT_SAC(9.0, "Xuat sac"),
    GIOI(8.0, "Gioi"),
    KHA(7.0, "Kha"),
    TRUNG_BINH(5.0, "Trung binh"),
    YEU(0.0, "Yeu");
    private final double minScore;
    private final String description;
    Rating(double minScore, String description) {
        this.minScore = minScore;
        this.description = description;
    }
    public static Rating getRating(double score) {
        for (Rating rating : Rating.values()) {
            if (score >= rating.minScore) {
                return rating;
            }
        }
        return null;
    }

}
