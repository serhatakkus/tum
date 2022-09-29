package pgdp.maze;

import java.util.Set;

public class Maze {
    private final TileState[][] tiles;
    private final Position entrance;
    private final Position exit;

    public Maze(TileState[][] tiles, Position entrance, Position exit) {
        this.tiles = tiles;
        this.entrance = entrance;
        this.exit = exit;
    }

    // ------------------------ Getter u.Ä. ------------------------ //
    
    
    public Position getEntrance() {
        return entrance; 
    }

    public Position getExit() {
        return exit;
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public boolean isEmptyTile(Position position) {
        return position.isInBounds(getHeight(), getWidth())
                && tiles[position.getI()][position.getJ()] == TileState.SPACE;
    }

    /**
     * Markiert das Feld an der übergebenen Position 'position' (D.h. der TileState
     * wird auf MARKED gesetzt). Nur Felder mit TileState SPACE können markiert
     * werden, keine WALLs.
     *
     * @param position Position, die markiert werden soll
     */
    public void mark(Position position) {
        if(isEmptyTile(position)) {
            tiles[position.getI()][position.getJ()] = TileState.MARKED;
        }
    }

    /**
     * Gibt eine String-Repräsentation des Labyrinths zurück. Dabei steht ein W für
     * eine 'WALL', ein Leerzeichen für ein freies Feld ('SPACE') und ein ' für ein
     * markiertes Feld ('MARKED'). Außerdem steht E für den Eingang ('entrance') und
     * X für den Ausgang ('exit') des Labyrinthes. Dabei wird E vor X vor den
     * "normalen" Tiles gezeichnet.
     *
     * @return String-Repräsentation des Labyrinths 'this'
     */
    @Override
    public String toString() {
        return toString(null);
    }

    /**
     * Gibt eine String-Repräsentation des Labyrinths zurück, in der auch der der
     * Methode übergebene Pfad eingezeichnet ist. Dabei steht ein W für eine 'WALL',
     * ein Leerzeichen für ein freies Feld ('SPACE') und ein ' für ein markiertes
     * Feld ('MARKED'). Außerdem steht E für den Eingang ('entrance'), X für den
     * Ausgang ('exit') des Labyrinthes und * für ein Feld des übergebenen Pfades.
     * Dabei wird E vor X vor * vor den "normalen" Tiles gezeichnet.
     *
     * @param solution Der übergebene Pfad
     * @return String-Repräsentation des Labyrinths 'this' mit eingezeichnetem Pfad
     *         'solution'
     */
    public String toString(Path solution) {
        return toString(solution, getEntrance());
    }

    /** Gibt eine String-Repräsentation des Labyrinths zurück, in der auch der der Methode übergebene Pfad
     *  eingezeichnet ist und zwar von der übergebenen Startposition aus.
     *  Dabei steht ein W für eine 'WALL', ein Leerzeichen für ein freies Feld ('SPACE')
     *  und ein ' für ein markiertes Feld ('MARKED').
     *  Außerdem steht E für den Eingang ('entrance'), X für den Ausgang ('exit')
     *  des Labyrinthes und * für ein Feld des übergebenen Pfades.
     *  Dabei wird E vor X vor * vor den "normalen" Tiles gezeichnet.
     *
     * @param solution Der übergebene Pfad
     * @param start Die übergebene Startposition
     * @return String-Repräsentation des Labyrinths 'this' mit eingezeichnetem Pfad 'solution' von 'start' aus
     */
    public String toString(Path solution, Position start) {
        Set<Position> positionsOnPath = solution == null ? null : solution.toPositionSet(start);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < getHeight(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                if(entrance.isAt(i, j)) {
                    sb.append('E');
                } else if(exit.isAt(i, j)) {
                    sb.append('X');
                } else if(positionsOnPath != null && positionsOnPath.contains(new Position(i, j))) {
                    sb.append('*');
                } else {
                    switch (tiles[i][j]) {
                        case WALL -> sb.append('W');
                        case SPACE -> sb.append(' ');
                        case MARKED -> sb.append('\'');
                        default -> sb.append('R');
                    }
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Enum, das die möglichen Zustände eines Feldes speichert: WALL: Eine Wand.
     * SPACE: Ein freies Feld. MARKED: Ein markiertes Feld.
     */
    static enum TileState {
        WALL, SPACE, MARKED
    }
}
