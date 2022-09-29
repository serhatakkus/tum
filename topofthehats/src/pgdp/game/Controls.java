package pgdp.game;

public interface Controls {

	void move(Game game, Figure figure);

	boolean attack(Game game, Figure figure);

}
