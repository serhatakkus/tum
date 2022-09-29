package pgdp.game;

import java.util.ArrayList;
import java.util.List;

import pgdp.game.helper.Random;

public abstract class AIControls implements Controls {

	private Entity target;

	@Override
	public void move(Game game, Figure figure) {
		if (!figure.isHasHat()) {
			figure.moveTo(game, selectDirection(selectTarget(game), figure));
		} else {
			Direction direction = null;
			switch (Random.get(1, 9)) {
			case 1:
				direction = Direction.UP;
				break;
			case 2:
				direction = Direction.DOWN;
				break;
			case 3:
				direction = Direction.DOWN_LEFT;
				break;
			case 4:
				direction = Direction.DOWN_RIGHT;
				break;
			case 5:
				direction = Direction.LEFT;
				break;
			case 6:
				direction = Direction.RIGHT;
				break;
			case 7:
				direction = Direction.UP_LEFT;
				break;
			case 8:
				direction = Direction.UP_RIGHT;
				break;
			default:

			}
			figure.moveTo(game, direction);
		}
	}

	@Override
	public abstract boolean attack(Game game, Figure figure);

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public Entity selectTarget(Game game) {
		if (target == null || target.getHitBox().isEmpty() || target instanceof Figure figure && !figure.isHasHat()) {
			List<Entity> targetEntities = new ArrayList<Entity>();
			for (Entity entity : game.getEntities()) {
				if (entity instanceof Hat hat && !hat.getHitBox().isEmpty()) {
					targetEntities.add(hat);
				} else if (entity instanceof Figure figure && !figure.getHitBox().isEmpty() && figure.isHasHat()) {
					targetEntities.add(figure);
				}
			}
			int randomInt = Random.get(0, targetEntities.size());
			target = targetEntities.get(randomInt);
		}
		return target;
	}

	public Direction selectDirection(Entity entity, Figure figure) {
		if (entity.getHitBox().isEmpty() || figure.getHitBox().isEmpty()
				|| entity.getHitBox().get().getPosition() == null || figure.getHitBox().get().getPosition() == null) {

			return Direction.UP;
		}

		int entityX = entity.getHitBox().get().getPosition().getX();
		int entityY = entity.getHitBox().get().getPosition().getY();
		int figureX = figure.getHitBox().get().getPosition().getX();
		int figureY = figure.getHitBox().get().getPosition().getY();

		if (entityX == figureX && figureY == entityY) {
			return Direction.UP;
		}

		if (entityX == figureX && entityY > figureY) {
			return Direction.DOWN;
		}

		if (entityX == figureX && entityY < figureY) {
			return Direction.UP;
		}
		if (entityX < figureX && entityY == figureY) {
			return Direction.LEFT;
		}
		if (entityX > figureX && entityY == figureY) {
			return Direction.RIGHT;
		}
		if (entityX < figureX && entityY < figureY) {
			return Direction.UP_LEFT;
		}
		if (entityX > figureX && entityY < figureY) {
			return Direction.UP_RIGHT;

		}
		if (entityX > figureX && entityY > figureY) {
			return Direction.DOWN_RIGHT;

		}
		if (entityX < figureX && entityY > figureY) {
			return Direction.DOWN_LEFT;

		}

		return Direction.UP;

	}

}
