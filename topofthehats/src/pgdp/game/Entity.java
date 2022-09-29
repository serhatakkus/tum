package pgdp.game;

import java.util.Optional;

public abstract class Entity {
	private LocatedBoundingBox hitBox;

	public Entity(LocatedBoundingBox hitBox) {
		this.hitBox = hitBox;
	}

	public Optional<LocatedBoundingBox> getHitBox() {
		return Optional.ofNullable(hitBox);
	}

	public void setHitBox(LocatedBoundingBox hitBox) {
		this.hitBox = hitBox;
	}

	public boolean visit(Game game) {
		return false;
	}

	public abstract Image getImage();

}
