package examen2_lab2;

public enum Trophy {
    PLATINO(5),
    ORO(3),
    PLATA(2),
    BRONCE(1);

    private final int points;

    Trophy(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
