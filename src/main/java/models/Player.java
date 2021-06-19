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
    private int maxLifePoint = 0;

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

    public static Player getFirstPlayer() {
        return allPlayers.get(0);
    }

    public static Player getSecondPlayer() {
        return allPlayers.get(1);
    }

    public static void removePlayers() {
        allPlayers.clear();
    }

    public static void resetPlayers() {
        for (Player player : allPlayers) {
            player.isRivalTrapsBlocked = false;
            player.canRivalAttack = true;
            player.isRivalReveled = false;
            player.canStrongRivalAttack = true;
            player.canRivalPickCard = true;
            player.lifePoint = 8000;
            player.getPlayerBoard().resetTheBoard(null, null); //TODO handle switching cards
        }
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

    public User getUser() {
        return User.getUserByUserName(this.userName);
    }

    public int getMaxLifePoint() {
        return this.maxLifePoint;
    }

    public void setMaxLifePoint(int lifePoint) {
        if (lifePoint > this.maxLifePoint)
            this.maxLifePoint = lifePoint;
    }
}
