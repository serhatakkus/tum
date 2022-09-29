package pgdp.game;

import java.util.List;

public abstract class Figure extends Entity {
	private Direction lastDirection = Direction.UP;
	private int attackCooldown;
	private int moveCooldown;
	private int disabledCooldown = 0;
	private boolean hasHat = false;

	private Controls controls = new PlayerControls();

	public Direction getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(Direction lastDirection) {
		this.lastDirection = lastDirection;
	}

	public int getAttackCooldown() {
		return attackCooldown;
	}

	public void setAttackCooldown(int attackCooldown) {
		this.attackCooldown = attackCooldown;
	}

	public int getMoveCooldown() {
		return moveCooldown;
	}

	public void setMoveCooldown(int moveCooldown) {
		this.moveCooldown = moveCooldown;
	}

	public int getDisabledCooldown() {
		return disabledCooldown;
	}

	public void setDisabledCooldown(int disabledCooldown) {
		this.disabledCooldown = disabledCooldown;
	}

	public boolean isHasHat() {
		return hasHat;
	}

	public void setHasHat(boolean hasHat) {
		this.hasHat = hasHat;
	}

	public Figure(LocatedBoundingBox locatedBoundingBox) {
		super(locatedBoundingBox);
		moveCooldown = getFullMoveCooldown();
		attackCooldown = getFullAttackCooldown();

	}

	public boolean visit(Game game) {
		if (disabledCooldown > 0) {
			disabledCooldown--;
		}
		if (disabledCooldown > 0) {
			return true;
		}

		moveCooldown--;
		if (moveCooldown == 0) {
			moveCooldown = getFullMoveCooldown();
			controls.move(game, this);

		}

		if (attackCooldown > 0) {
			attackCooldown--;
		}
		if (attackCooldown == 0 && controls.attack(game, this)) {
			this.attack(game);
			attackCooldown = getFullAttackCooldown();
		}
		return true;

	}

	public abstract void attack(Game game);

	public void moveTo(Game game, Direction direction) {
		setLastDirection(direction);
		if (direction == null) {
			return;
		}

		List<Entity> collidedEntities = game.getCollisionBoard().getCollisions(this, direction);
		for (Entity entity : collidedEntities) {
			if (!hasHat && entity instanceof Hat hat) {
				hasHat = true;
				hat.setDontRemove(false);
				game.getCollisionBoard().remove(hat);

			}
		}

		game.getCollisionBoard().move(this, direction);
	}

	public abstract int getFullMoveCooldown();

	public abstract int getFullAttackCooldown();

	public Controls getControls() {
		return controls;
	}

	public void setControls(Controls controls) {
		this.controls = controls;
	}

	protected void takeHat(List<Entity> collidedEntities) {
		for (Entity entity : collidedEntities) {
			if (entity instanceof Figure figure) {
				figure.setDisabledCooldown(60);
				if (figure.isHasHat() && !this.isHasHat()) {
					figure.setHasHat(false);
					this.setHasHat(true);

				}
			}

		}
	}
}
