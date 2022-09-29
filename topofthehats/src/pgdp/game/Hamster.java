package pgdp.game;

import java.util.List;

public class Hamster extends Figure {
	public Hamster(Position position) {
		super(new LocatedBoundingBox(position, new BoundingBox(3, 3)));

	}

	public void attack(Game game) {
		if (getLastDirection() == null) {
			return;
		}
		List<Entity> collidedEntities = game.getCollisionBoard().getCollisions(this, getLastDirection());
		if (collidedEntities != null && collidedEntities.size() > 0) {
			takeHat(collidedEntities);

		} else {
			int x = this.getHitBox().get().getPosition().getX();
			int y = this.getHitBox().get().getPosition().getY();
			switch (getLastDirection()) {
			case UP:
				x++;
				y--;
				break;

			case DOWN:
				x++;
				y = y + 3;
				break;

			case LEFT:
				x--;
				y++;
				break;

			case RIGHT:
				x = x + 3;
				y++;
				break;

			case UP_LEFT:
				x--;
				y--;
				break;

			case UP_RIGHT:
				y--;
				x = x + 3;
				break;

			case DOWN_LEFT:
				x--;
				y = y + 3;
				break;

			case DOWN_RIGHT:
				y = y + 3;
				x = x + 3;
				break;

			default:

			}

			Position position = new Position(x, y);

			Cookie cookie = new Cookie(position, getLastDirection());

			game.getEntityAdd().add(cookie);
		}

	}

	public int getFullMoveCooldown() {
		return 15;
	}

	public int getFullAttackCooldown() {
		return 140;
	}

	@Override
	public Image getImage() {
		if (this.isHasHat()) {
			return Image.HAMSTER_HAT;
		} else {
			return Image.HAMSTER;
		}
	}

}
