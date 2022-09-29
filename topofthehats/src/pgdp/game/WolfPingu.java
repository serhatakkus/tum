package pgdp.game;

import java.util.List;

public class WolfPingu extends Figure {
	public WolfPingu(Position position) {
		super(new LocatedBoundingBox(position, new BoundingBox(3, 3)));

	}

	public void attack(Game game) {
		if (getLastDirection() == null) {
			return;
		}
		List<Entity> collidedEntities = game.getCollisionBoard().getCollisions(this, getLastDirection());
		if (collidedEntities != null) {
			takeHat(collidedEntities);

		}

	}

	public int getFullMoveCooldown() {
		return 10;
	}

	public int getFullAttackCooldown() {
		return 120;
	}

	@Override
	public Image getImage() {
		if (this.isHasHat()) {
			return Image.WOLF_PINGU_HAT;
		} else {
			return Image.WOLF_PINGU;
		}
	}

}
