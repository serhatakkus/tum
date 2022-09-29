package pgdp.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class MazeParser {

    // Man soll von außen keine Objekte der Klasse erzeugen können. Alle Methoden
    // sind static. Daher ist der Konstruktor private
    private MazeParser() {

    }

    /**
     * Liest die Datei am übergebenen Dateipfad (vom Root-Verzeichnis des Projektes
     * aus interpretiert) ein und versucht, daraus mittels der Methode
     * 'mazeFromStringArray()' ein Labyrinth auszulesen.
     *
     * @param filePath Dateipfad (vom Root-Verzeichnis des Projektes aus
     *                 interpretiert)
     * @return Das ausgelesene Labyrinth. 'null', wenn ein Fehler auftritt.
     */
    public static Maze parseFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, Charset.defaultCharset()))) {
            List<String> lines = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }

            br.close();
            return mazeFromStringArray(lines.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Das übergebene String-Array wird in ein Maze-Objekt geparst. Jeder String
     * wird dabei als eine Zeile des Labyrinths interpretiert. Dabei steht W für
     * WALL, ein Leerzeichen für SPACE, ein ' für MARKED, E für SPACE und den
     * Eingang und X für SPACE und den Ausgang.
     *
     * Wenn 'lines' kein valides Labyrinth enthält, wird eine Exception geworfen.
     *
     * @param lines Array mit Strings, welche die Zeilen des Labyrinths
     *              repräsentieren.
     * @return Das geparste Labyrinth.
     */
    private static Maze mazeFromStringArray(String[] lines) {
        if (!allSameLength(lines)) {
            throw new IllegalArgumentException("All lines in input file should be of same length!");
        }

        Maze.TileState[][] tiles = new Maze.TileState[lines.length][lines[0].length()];
        Position entrance = null;
        Position exit = null;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                switch (lines[i].charAt(j)) {
                    case ' ' -> {
                        tiles[i][j] = Maze.TileState.SPACE;
                    }
                    case 'E' -> {
                        tiles[i][j] = Maze.TileState.SPACE;
                        if (entrance != null) {
                            throw new IllegalArgumentException("Input file contains multiple entrances!");
                        }
                        entrance = new Position(i, j);
                    }
                    case 'X' -> {
                        tiles[i][j] = Maze.TileState.SPACE;
                        if (exit != null) {
                            throw new IllegalArgumentException("Input file contains multiple exits!");
                        }
                        exit = new Position(i, j);
                    }
                    case 'W' -> {
                        tiles[i][j] = Maze.TileState.WALL;
                    }
                    case '\'' -> {
                        tiles[i][j] = Maze.TileState.MARKED;
                    }
                    default -> {
                        throw new IllegalArgumentException(
                                "Input file should only contain characters ' ', 'E', 'X', 'W' and '\'' and line breaks!");
                    }
                }
            }
        }
        return new Maze(tiles, entrance, exit);
    }

    // Prüft, ob alle Strings in 'lines' gleichlang sind
    private static boolean allSameLength(String[] lines) {
        boolean allSameLength = true;
        int length = lines[0].length();
        for (int i = 1; i < lines.length; i++) {
            allSameLength &= lines[i].length() == length;
        }
        return allSameLength;
    }

}
