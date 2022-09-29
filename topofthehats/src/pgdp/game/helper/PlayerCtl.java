package pgdp.game.helper;

public final class PlayerCtl {
    private static volatile boolean up = false;
    private static volatile boolean left = false;
    private static volatile boolean down = false;
    private static volatile boolean right = false;
    private static volatile boolean attack = false;

    private PlayerCtl() {
    }

    public static boolean isUp() {
        return up;
    }

    public static void setUp(boolean up) {
        PlayerCtl.up = up;
    }

    public static boolean isLeft() {
        return left;
    }

    public static void setLeft(boolean left) {
        PlayerCtl.left = left;
    }

    public static boolean isDown() {
        return down;
    }

    public static void setDown(boolean down) {
        PlayerCtl.down = down;
    }

    public static boolean isRight() {
        return right;
    }

    public static void setRight(boolean right) {
        PlayerCtl.right = right;
    }

    public static boolean isAttack() {
        return attack;
    }

    public static void setAttack(boolean attack) {
        PlayerCtl.attack = attack;
    }
}
