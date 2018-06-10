package karabaka.game.client.utils;

public enum Direction {
    LEFT(1),
    RIGHT(2),
    UP(3),
    DOWN(4);

    Direction(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}