package pgdp.game;

import pgdp.game.helper.PlayerCtl;

public class PlayerControls implements Controls {

	@Override
	public void move(Game game, Figure figure) {

		Direction direction = null;
		if (PlayerCtl.isUp() && PlayerCtl.isDown()) {
			PlayerCtl.setUp(false);
			PlayerCtl.setDown(false);
		}

		if (PlayerCtl.isLeft() && PlayerCtl.isRight()) {
			PlayerCtl.setLeft(false);
			PlayerCtl.setRight(false);
		}
		if (PlayerCtl.isDown() && PlayerCtl.isLeft()) {
			direction = Direction.DOWN_LEFT;
		}

		else if (PlayerCtl.isDown() && PlayerCtl.isRight()) {
			direction = Direction.DOWN_RIGHT;
		} else if (PlayerCtl.isUp() && PlayerCtl.isLeft()) {
			direction = Direction.UP_LEFT;

		} else if (PlayerCtl.isUp() && PlayerCtl.isRight()) {
			direction = Direction.UP_RIGHT;
		} else if (PlayerCtl.isDown()) {
			direction = Direction.DOWN;
		} else if (PlayerCtl.isUp()) {
			direction = Direction.UP;
		} else if (PlayerCtl.isLeft()) {
			direction = Direction.LEFT;
		} else if (PlayerCtl.isRight()) {
			direction = Direction.RIGHT;
		}

		figure.moveTo(game, direction);
	}

	@Override
	public boolean attack(Game game, Figure figure) {

		return PlayerCtl.isAttack();
	}

}
