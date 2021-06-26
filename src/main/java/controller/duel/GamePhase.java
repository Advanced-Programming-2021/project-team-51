package controller.duel;

public enum GamePhase {
    DRAW("draw"),
    STANDBY("stand by"),
    MAIN1("main 1"),
    BATTLE("battle"),
    MAIN2("main 2"),
    END("end"),
    RIVAL_TURN("rival turn");

    private final String label;

    GamePhase(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
