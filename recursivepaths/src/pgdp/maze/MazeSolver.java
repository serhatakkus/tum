package pgdp.maze;

public final class MazeSolver {

	// Man soll von außen keine Objekte der Klasse erzeugen können. Alle Methoden
	// sind static. Daher ist der Konstruktor private
	private MazeSolver() {
	}

	// TODO: Implementiere diese Methode, sodass sie einen Pfad vom Eingang
	// ('entrance') zum Ausgang ('exit')
	// des übergebenen Labyrinths 'maze' zurückgibt.
	public static Path solveMaze(Maze maze) {
		Path path = solveMazeFrom(maze, maze.getEntrance());
		return path;
	}

	// TODO: Implementiere diese Methode, sodass sie einen Pfad vom der übergebenen
	// Position 'position'
	// zum Ausgang ('exit') des übergebenen Labyrinths 'maze' zurückgibt.
	// Sie muss rekursiv implementiert werden.
	public static Path solveMazeFrom(Maze maze, Position position) {
		Path path = new Path();
		int i = position.getI();
		int j = position.getJ();
		int exitI = maze.getExit().getI();
		int exitJ = maze.getExit().getJ();
		
		System.out.println("running for " + i + "," + j);

		// check if we have reached the exit
		if (i + 1 == exitI && j == exitJ) {
			path.prepend(Direction.DOWN);
			System.out.println("exit on " + (i+1) + "," + j + " path: " + path.toString());
			return path;
		}
		if (i == exitI && j + 1 == exitJ) {
			path.prepend(Direction.RIGHT);
			System.out.println("exit on " + (i) + "," + (j+1) + " path: " + path.toString());
			return path;
		}
		if (i - 1 == exitI && j == exitJ) {
			path.prepend(Direction.UP);
			System.out.println("exit on " + (i-1) + "," + j + " path: " + path.toString());
			return path;
		}
		if (i == exitI && j - 1 == exitJ) {
			path.prepend(Direction.LEFT);
			System.out.println("exit on " + (i) + "," + (j-1) + " path: " + path.toString());
			return path;
		}
		
		// not reached exit yet. go to next empty tiles
		Position left = new Position(i, j-1);
		Position right = new Position(i, j+1);
		Position up = new Position(i-1,j);
		Position down = new Position(i+1,j);
		
		
		if (maze.isEmptyTile(right)) {
			maze.mark(right);
			path = solveMazeFrom(maze, right);
			if (path != null) {
				path.prepend(Direction.RIGHT);
				System.out.println("return for right");
				return path;
			}
		}
		if (maze.isEmptyTile(left)) {
			maze.mark(left);
			path = solveMazeFrom(maze, left);
			if (path != null) {
				path.prepend(Direction.LEFT);
				System.out.println("return for left");
				return path;
			}
		}
		
		if (maze.isEmptyTile(down)) {
			maze.mark(down);
			path = solveMazeFrom(maze, down);
			if (path != null) {
				path.prepend(Direction.DOWN);
				System.out.println("return for down");
				return path;
			}
		}
		
		if (maze.isEmptyTile(up)) {
			maze.mark(up);
			path = solveMazeFrom(maze, up);
			if (path != null) {
				path.prepend(Direction.UP);
				System.out.println("return for up");
				return path;
			}
		}
		System.out.println("return dead end");
		return null;

	}

	// Zum Testen
	public static void main(String[] args) {
		Maze maze = MazeParser.parseFromFile("resources/Maze.txt");
		// Maze maze = MazeParser.parseFromFile("resources/MazeSimple2.txt");
		if (maze == null) {
			return;
		}

		System.out.println(maze);

		Path path = solveMaze(maze);
		// Path path = solveMazeFrom(maze, maze.getEntrance());
		System.out.println(path);
		System.out.println();
		System.out.println(maze.toString(path));
	}

}
