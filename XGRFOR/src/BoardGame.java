public class BoardGame {
    private String name;
    private boolean purchased;
    private int popularity;

    public BoardGame(String name, boolean purchased, int popularity) {
        this.name = name;
        this.purchased = purchased;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public int getPopularity() {
        return popularity;
    }
}
