package pgdp.game;

public class BoundingBox {
    private int width;
    private int height;

    public BoundingBox() {
        width = 0;
        height = 0;
    }

    public BoundingBox(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
