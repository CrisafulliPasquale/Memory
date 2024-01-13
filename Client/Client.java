package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
    private JButton[] buttons;
    private int[] cardValues;
    private int firstCardIndex;
    private int secondCardIndex;
    private int pairsFound;

    public Client() {
        setTitle("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4));

        buttons = new JButton[16];
        cardValues = new int[16];
        firstCardIndex = -1;
        secondCardIndex = -1;
        pairsFound = 0;

        // Inizializza i pulsanti e assegna i valori delle carte
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(new CardClickListener(i));
            add(buttons[i]);
            cardValues[i] = i / 2;
        }

        // Mescola i valori delle carte
        shuffleCards();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void shuffleCards() {
        for (int i = 0; i < 16; i++) {
            int randomIndex = (int) (Math.random() * 16);
            int temp = cardValues[i];
            cardValues[i] = cardValues[randomIndex];
            cardValues[randomIndex] = temp;
        }
    }

    private void showCard(int index) {
        buttons[index].setText(String.valueOf(cardValues[index]));
        buttons[index].setEnabled(false);
    }

    private void hideCard(int index) {
        buttons[index].setText("");
        buttons[index].setEnabled(true);
    }

    private void checkCards() {
        if (cardValues[firstCardIndex] == cardValues[secondCardIndex]) {
            pairsFound++;
            if (pairsFound == 8) {
                JOptionPane.showMessageDialog(this, "Hai vinto!");
                System.exit(0);
            }
        } else {
            hideCard(firstCardIndex);
            hideCard(secondCardIndex);
        }
        firstCardIndex = -1;
        secondCardIndex = -1;
    }

    private class CardClickListener implements ActionListener {
        private int index;

        public CardClickListener(int index) {
            this.index = index;
        }

        public void actionPerformed(ActionEvent e) {
            if (firstCardIndex == -1) {
                firstCardIndex = index;
                showCard(index);
            } else if (secondCardIndex == -1) {
                secondCardIndex = index;
                showCard(index);
                checkCards();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Client();
            }
        });
    }
}
