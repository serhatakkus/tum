package pgdp.game;

import java.util.List;

public class Cookie extends Entity {

	private Direction direction;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Cookie(Position position, Direction direction) {
		super(new LocatedBoundingBox(position, new BoundingBox(1, 1)));
		this.direction = direction;
	}

	public boolean visit(Game game) {
		boolean retVal = true;
		List<Entity> collidedEntities = game.getCollisionBoard().getCollisions(this, this.direction);

		if (game.getCollisionBoard().hasCollisions(this, this.direction)
				&& (collidedEntities == null || collidedEntities.isEmpty())) {
			retVal = false;
		}
		if (collidedEntities != null && !collidedEntities.isEmpty()) {
			for (Entity entity : collidedEntities) {
				retVal = false;
				if (entity instanceof Figure figure) {
					figure.setDisabledCooldown(60);
					if (figure.isHasHat()) {
						figure.setHasHat(false);
						game.getEntityAdd().add(new Hat(figure.getHitBox().get().getPosition()));
					}
				}
			}
		}

		game.getCollisionBoard().move(this, this.direction);

		if (this.getHitBox().isEmpty()) {
			retVal = false;
		}

		return retVal;
	}

	@Override
	public Image getImage() {

		return Image.COOKIE;
	}
}
