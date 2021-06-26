package controller.menucontroller;

import controller.duel.PhaseController;
import controller.duel.SelectionController;
import models.Player;
import models.User;
import models.cards.Card;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class CheatMenuController {

    PhaseController phaseController = new PhaseController();

    public void run(String command) {
        Player cheater = PhaseController.playerInTurn;
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_MONEY)).matches())
            increaseMoney(Integer.parseInt(matcher.group(2)), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SET_WINNER)).matches())
            setWinner(matcher.group(1), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_LP)).matches())
            increaseLP(Integer.parseInt(matcher.group(2)), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_1)).matches())
            selectCardForce(matcher.group(2), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_2)).matches())
            selectCardForce(matcher.group(3), cheater);
        else
            System.out.println(StatusEnum.INVALID_COMMAND.getStatus());
    }


    private void increaseMoney(int amount, Player cheater) {
        User cheaterUser = User.getUserByUserName(cheater.getUserName());
        assert cheaterUser != null;
        cheaterUser.setMoney(cheaterUser.getMoney() + amount);
        System.out.println("Cheat Activated Successfully");
    }

    private void increaseLP(int amount, Player cheater) {
        cheater.getPlayerBoard().setLifePoints(cheater.getPlayerBoard().getLifePoints() + amount);
        System.out.println("Cheat Activated Successfully");
    }

    private void setWinner(String nickname, Player cheater) {
        /*if (!DuelView.isMultiPlayer) {
            if (cheater.getNickName().equals(nickname))

        } else {*/
            if (cheater.getNickName().equals(nickname)) {
                phaseController.endGame(cheater, PhaseController.playerAgainst);
                System.out.println("Cheat Activated Successfully");
            }
            else if (PhaseController.playerAgainst.getNickName().equals(nickname)) {
                phaseController.endGame(PhaseController.playerAgainst, cheater);
                System.out.println("Cheat Activated Successfully");
            }
        /*}*/

    }

    private void selectCardForce(String cardName, Player cheater) {
        for (Card card: cheater.getPlayerBoard().getHandCards())
            if (card.getName().equals(cardName)) SelectionController.selectedCard = card;
        System.out.println("Cheat Activated Successfully");
    }
}
