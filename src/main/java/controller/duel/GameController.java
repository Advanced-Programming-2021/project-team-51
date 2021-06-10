package controller.duel;

import controller.menucontroller.DuelController;
import models.Player;
import models.cards.Card;
import models.cards.monsters.MonsterCard;

public class GameController {

    private final SummonController summonController = new SummonController();
    private final SettingController settingController = new SettingController();

    private static Player owner;
    private static Card selectedCard;
    private static GamePhase phase;

    public static void setOwner(Player owner) {
        GameController.owner = owner;
    }

    public static Player getPlayer1() {
        return owner;
    }

    public static void setSelectedCard(Card selectedCard) {
        GameController.selectedCard = selectedCard;
    }

    public static Card getSelectedCard() {
        return selectedCard;
    }

    public void discard() {
        GameController.selectedCard = null;
    }

    public void selectCard() {

    }

    public void goToTheNextPhase() {

    }

    public void setMonsterOrSpell() {
        settingController.set();
    }

    public void summonMonster() {
        summonController.summon();
    }

    public void changePosition(String position) {
        settingController.setPosition(position);
    }

    public void flipSummon() {
        summonController.flipSummon();
    }

    public void attackToOpponentMonster(int index) {
        Player opponent;
        if (DuelController.getPlayer1().getUserName().equals(owner.getUserName()))
            opponent = DuelController.getPlayer2();
        else
            opponent = DuelController.getPlayer1();
        new AttackController(owner, opponent, phase).attackMonsterToMonster((MonsterCard) selectedCard,
                opponent.getPlayerBoard().getMonsterBoard().get(index));
    }

    public void directAttack() {
        Player opponent;
        if (DuelController.getPlayer1().getUserName().equals(owner.getUserName()))
            opponent = DuelController.getPlayer2();
        else
            opponent = DuelController.getPlayer1();
        new AttackController(owner, opponent, phase).directAttack((MonsterCard) selectedCard);
    }

    public void activeSpell() {

    }

    public void activeTrap() {

    }

    public void ritualSummon() {

    }

    public void specialSummon() {

    }
}
