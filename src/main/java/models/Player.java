package models;

import java.util.ArrayList;

public class Player {
    private static final ArrayList<Player> allPlayers;
    private Deck playerDeck;
    private Board playerBoard;
    private String userName;
    private String nickName;

    static {
        allPlayers = new ArrayList<>();
    }

    public Player(User user) throws CloneNotSupportedException {
        allPlayers.add(this);
        setPlayerDeck(user.getActiveDeck());
        setPlayerBoard(new Board(this));
        setUserName(user.getUserName());
        setNickName(user.getNickName());
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public static Player getPlayerByUsername(String userName) {
        for (Player player : allPlayers) {
            if (player.userName.equals(userName))
                return player;
        }
        return null;
    }

    public Deck getPlayerDeck() {
        return this.playerDeck;
    }

    private void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public Board getPlayerBoard() {
        return this.playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public String getUserName() {
        return this.userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
