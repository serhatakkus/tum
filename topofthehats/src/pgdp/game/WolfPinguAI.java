package pgdp.game;

import java.util.List;

public class WolfPinguAI extends AIControls {

	@Override
	public boolean attack(Game game, Figure figure) {

		if (figure.getLastDirection() == null) {
			return false;
		}
		List<Entity> collidedEntities = game.getCollisionBoard().getCollisions(figure, figure.getLastDirection());
		if (collidedEntities != null) {
			for (Entity entity : collidedEntities) {
				if (entity instanceof Figure) {
					return true;
				}

			}

		}
		return false;
	}

}
