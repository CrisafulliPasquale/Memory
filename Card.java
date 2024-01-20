import javax.swing.*;

public class Card extends JButton {
    private int value;
    private boolean flipped;

    public Card(int value) {
        this.value = value;
        this.flipped = false;
    }

    public int getValue() {
        return value;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void flip() {
        flipped = !flipped;
        updateAppearance();
    }

    private void updateAppearance() {
        if (flipped) {
            setText(String.valueOf(value));
        } else {
            setText("");
        }
    }
}
