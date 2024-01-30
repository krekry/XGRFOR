import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class BoardGameGUI extends JFrame {
    private List<BoardGame> boardGames;
    private int currentIndex;

    private JLabel nameLabel;
    private JCheckBox purchasedCheckBox;
    private JRadioButton favorite1RadioButton, favorite2RadioButton, favorite3RadioButton;
    private JButton nextButton;
    private JButton previousButton;

    public BoardGameGUI(List<BoardGame> boardGames) {
        this.boardGames = boardGames;
        this.currentIndex = 0;

        setTitle("Deskové Hry");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeUI();
        updateGameInfo();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));

        nameLabel = new JLabel();
        mainPanel.add(new JLabel("Název hry:"));
        mainPanel.add(nameLabel);

        purchasedCheckBox = new JCheckBox("Koupeno");
        mainPanel.add(purchasedCheckBox);

        ButtonGroup popularityGroup = new ButtonGroup();
        favorite1RadioButton = new JRadioButton("1");
        favorite2RadioButton = new JRadioButton("2");
        favorite3RadioButton = new JRadioButton("3");

        popularityGroup.add(favorite1RadioButton);
        popularityGroup.add(favorite2RadioButton);
        popularityGroup.add(favorite3RadioButton);

        mainPanel.add(new JLabel("Oblíbenost:"));
        mainPanel.add(favorite1RadioButton);
        mainPanel.add(new JLabel(""));
        mainPanel.add(favorite2RadioButton);
        mainPanel.add(new JLabel(""));
        mainPanel.add(favorite3RadioButton);

        JButton previousButton = new JButton("Předchozí");
        JButton nextButton = new JButton("Další");

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + boardGames.size()) % boardGames.size();
                updateGameInfo();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % boardGames.size();
                updateGameInfo();
            }
        });

        mainPanel.add(previousButton);
        mainPanel.add(nextButton);

        add(mainPanel);
    }

    private void updateGameInfo() {
        if (!boardGames.isEmpty()) {
            BoardGame currentGame = boardGames.get(currentIndex);

            nameLabel.setText(currentGame.getName());
            purchasedCheckBox.setSelected(currentGame.isPurchased());

            int popularity = currentGame.getPopularity();
            switch (popularity) {
                case 1:
                    favorite1RadioButton.setSelected(true);
                    break;
                case 2:
                    favorite2RadioButton.setSelected(true);
                    break;
                case 3:
                    favorite3RadioButton.setSelected(true);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                BoardGameFileReader fileReader = new BoardGameFileReader();
                List<BoardGame> boardGames = fileReader.readGamesFromFile("deskovky.txt");

                if (boardGames.isEmpty()) {
                    System.out.println("Soubor neobsahuje žádné hry.");
                    return;
                }

                BoardGameGUI gui = new BoardGameGUI(boardGames);
                gui.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
