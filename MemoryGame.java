import javax.swing.*;
import java.awt.*;

public class MemoryGame extends JFrame {
    private static final int DEFAULT_CARD_COUNT = 12;
    private GameManager gameManager;

    public MemoryGame(int cardCount) {
        setTitle("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 4));

        gameManager = new GameManager(cardCount, this);

        for (Card card : gameManager.getCards()) {
            add(card);
        }

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static void main(String[] args) {
        int cardCount = DEFAULT_CARD_COUNT;

        SwingUtilities.invokeLater(() -> new MemoryGame(cardCount));
    }
}
