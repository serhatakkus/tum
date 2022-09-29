package pgdp.game;

public enum Direction {
	UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT;

	public int getXChange() {
		if (this == UP || this == UP_RIGHT || this == UP_LEFT) {
			return -1;
		} else if (this == DOWN || this == DOWN_RIGHT || this == DOWN_LEFT) {
			return 1;
		}
		return 0;

	}

	public int getYChange() {
		if (this == LEFT || this == UP_LEFT || this == DOWN_LEFT) {
			return -1;
		} else if (this == RIGHT || this == DOWN_RIGHT || this == UP_RIGHT) {
			return 1;
		}
		return 0;

	}
}
