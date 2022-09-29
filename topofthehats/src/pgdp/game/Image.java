package pgdp.game;

public enum Image {
    COOKIE("/pgdp/game/assets/cookie-resized.png"), HAT("/pgdp/game/assets/hat-resized.png"),
    WOLF_PINGU("/pgdp/game/assets/wolf-pingu-resized.png"),
    WOLF_PINGU_HAT("/pgdp/game/assets/wolf-pingu-hat-resized.png"), HAMSTER("/pgdp/game/assets/hamster-resized.png"),
    HAMSTER_HAT("/pgdp/game/assets/hamster-hat-resized.png");
    private final String resource;

    Image(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
