import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardGameFileReader {
    public List<BoardGame> readGamesFromFile(String filePath) throws IOException {
        List<BoardGame> boardGames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    boolean purchased = parts[1].trim().equalsIgnoreCase("ano");
                    int popularity = Integer.parseInt(parts[2].trim());

                    BoardGame game = new BoardGame(name, purchased, popularity);
                    boardGames.add(game);
                }
            }
        }

        return boardGames;
    }
}
