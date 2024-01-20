import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    private List<Card> cards;
    private List<Card> flippedCards;
    private int attemptsLeft;
    private MemoryGame memoryGame;

    public GameManager(int cardCount, MemoryGame memoryGame) {
        this.memoryGame = memoryGame;
        cards = generateCards(cardCount);
        flippedCards = new ArrayList<>();
        attemptsLeft = 3;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getFlippedCards() {
        return flippedCards;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decreaseAttempts() {
        attemptsLeft--;
    }

    public boolean allCardsFlipped() {
        for (Card card : cards) {
            if (!card.isFlipped()) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        for (Card card : cards) {
            card.flip();
        }
        attemptsLeft = 3;
    }

    private List<Card> generateCards(int cardCount) {
        List<Card> generatedCards = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < cardCount / 2; i++) {
            values.add(i);
            values.add(i);
        }

        Collections.shuffle(values);

        for (int i = 0; i < cardCount; i++) {
            Card card = new Card(values.get(i));
            card.addActionListener(new CardClickListener());
            generatedCards.add(card);
        }

        return generatedCards;
    }
}

