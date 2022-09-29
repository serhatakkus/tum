package pgdp.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private CollisionBoard collisionBoard;
	private List<Entity> entities;
	private List<Entity> entityAdd;

	public CollisionBoard getCollisionBoard() {
		return collisionBoard;
	}

	public void setCollisionBoard(CollisionBoard collisionBoard) {
		this.collisionBoard = collisionBoard;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Entity> getEntityAdd() {
		return entityAdd;
	}

	public void setEntityAdd(List<Entity> entityAdd) {
		this.entityAdd = entityAdd;
	}

	public Game(CollisionBoard collisionBoard) {
		this.collisionBoard = collisionBoard;
		entities = new ArrayList<Entity>();
		entityAdd = new ArrayList<Entity>();

	}

	public void runTick() {
		List<Integer> removedEntities = new ArrayList<Integer>();
		for (int i = 0; i < entities.size(); i++) {
			if (!entities.get(i).visit(this)) {
				this.getCollisionBoard().remove(entities.get(i));
				removedEntities.add(i);
			}
		}

		for (int i = removedEntities.size() - 1; i >= 0; i--) {
			entities.remove(removedEntities.get(i).intValue());
		}

		for (int i = 0; i < entityAdd.size(); i++) {
			if (this.getCollisionBoard().set(entityAdd.get(i))) {
				entities.add(entityAdd.get(i));

			}

		}
		entityAdd.clear();

	}

}
