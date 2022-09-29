package pgdp.maze;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// TODO: Implementiere diese Klasse, sodass sie eine Sequenz von Directions repräsentiert
public class Path {
	// TODO: Attribut(e)

	private List<Direction> path;

	// TODO: Soll einen leeren Pfad erzeugen
	public Path() {
		path = new LinkedList<Direction>();
	}

	// TODO: Soll ein HashSet<Position> mit allen Positionen zurückgeben, die man
	// beim Ablaufen des Pfades 'this'
	// besucht, wenn man bei der Position 'start' beginnt (ungeachtet irgendwelcher
	// WALLs o.Ä.)
	public Set<Position> toPositionSet(Position start) {

		Set<Position> positionSet = new HashSet<Position>();
		Position currentElement = start;
		positionSet.add(currentElement);

		if (path == null || path.size() <= 0) {
			return positionSet;
		}

		for (int i = 0; i < path.size(); i++) {
			Position nextPosition;
			int newI = currentElement.getI();
			int newJ = currentElement.getJ();

			switch (path.get(i)) {
			case UP:
				newI -= 1;
				break;
			case DOWN:
				newI += 1;
				break;
			case LEFT:
				newJ -= 1;
				break;
			case RIGHT:
				newJ += 1;
				break;
			default:
				newI = 0;
				newJ = 0;
			}

			nextPosition = new Position(newI, newJ);
			positionSet.add(nextPosition);

			currentElement = nextPosition;
		}
		return positionSet;
	}

	// TODO: Soll die übergebene Richtung 'direction' vorne in die bisherige Sequenz
	// einfügen
	public void prepend(Direction direction) {
		path.add(0, direction);
	}

	// TODO: Soll die Richtung am übergebenen 'index' zurückgeben
	public Direction getStep(int index) {
		return path.get(index);

	}

	// TODO: Soll eine String-Repräsentation des Pfades 'this' zurückgeben, wie in
	// der Aufgabenstellung beschrieben
	@Override
	public String toString() {
		// [DOWN,⎵LEFT,⎵UP,⎵UP,⎵LEFT]
		String pathString;
		pathString = path.get(0).toString();
		for (int i = 1; i < path.size(); i++) {
			pathString = pathString + ", " + path.get(i).toString();
		}
		return "[" + pathString + "]";

	}
}
