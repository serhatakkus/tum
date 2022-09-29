package pgdp.game;

public class LocatedBoundingBox {
    private Position position;
    private BoundingBox boundingBox;

    public LocatedBoundingBox(Position position, BoundingBox boundingBox) {
        this.position = position;
        this.boundingBox = boundingBox;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

}
