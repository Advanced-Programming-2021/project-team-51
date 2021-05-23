package models;

import java.util.ArrayList;

public class Player {
    private static final ArrayList<Player> allPlayers;
    private Deck playerDeck;
    private Board playerBoard;
    private String userName;
    private String nickName;
    private boolean isRivalTrapsBlocked = false;
    private boolean canRivalAttack = true;
    private boolean isRivalReveled = false;
    private boolean canStrongRivalAttack = true;
    private boolean canRivalPickCard = true;
    private int lifePoint = 8000;

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

    public boolean getRivalTrapsBlocked() {
        return this.isRivalTrapsBlocked;
    }

    public void setRivalTrapsBlocked(boolean isRivalTrapsBlocked) {
        this.isRivalTrapsBlocked = isRivalTrapsBlocked;
    }

    public int getLifePoint() {
        return this.lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public boolean isRivalReveled() {
        return this.isRivalReveled;
    }

    public void setRivalReveled(boolean rivalReveled) {
        this.isRivalReveled = rivalReveled;
    }

    public boolean getCanRivalAttack() {
        return this.canRivalAttack;
    }

    public void setCanRivalAttack(boolean canRivalAttack) {
        this.canRivalAttack = canRivalAttack;
    }

    public boolean getCanStrongRivalAttack() {
        return this.canStrongRivalAttack;
    }

    public void setCanRivalPickCard(boolean canRivalPickCard) {
        this.canRivalPickCard = canRivalPickCard;
    }

    public boolean getCanRivalPickCard() {
        return this.canRivalPickCard;
    }

    public void setCanStrongRivalAttack(boolean canStrongRivalAttack) {
        this.canStrongRivalAttack = canStrongRivalAttack;
    }
}
