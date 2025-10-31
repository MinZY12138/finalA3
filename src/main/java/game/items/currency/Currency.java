package game.items.currency;

public enum Currency {

    GREEN(5),

    BLUE(10),

    RED(20);

    private final int VALUE;

    Currency(int value) {
        this.VALUE = value;
    }

    public int getVALUE() {
        return VALUE;
    }
}
