package pgdp.maze;

/**
 * Repräsentiert eine Position in einem Labyrinth als Zeile i und Spalte j.
 */
public class Position {
    private final int i;
    private final int j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isAt(int otherI, int otherJ) {
        return i == otherI && j == otherJ;
    }

    public boolean isInBounds(int height, int width) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    /*
     * Eigene Implementierungen von hashCode() und equals(), damit Position-Objekt
     * sinnvoll in einem HashSet (und ähnlichen Datenstrukturen) verwendet werden
     * können.
     */
    @Override
    public int hashCode() {
        return i + j;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Position otherPosition) {
            return otherPosition.i == i && otherPosition.j == j;
        }

        return false;
    }

    /**
     * Berechnet die Position ein Tile weiter in der übergebenen Richtung von 'this'
     * aus.
     *
     * @param direction Die Richtung, in die ein Schritt "gegangen" werden soll
     * @return Die Position einen Schritt in Richtung 'direction' von 'this' aus
     */
    public Position getPositionOneTile(Direction direction) {
        return switch (direction) {
            case UP -> new Position(i - 1, j);
            case DOWN -> new Position(i + 1, j);
            case LEFT -> new Position(i, j - 1);
            case RIGHT -> new Position(i, j + 1);
        };
    }
}
