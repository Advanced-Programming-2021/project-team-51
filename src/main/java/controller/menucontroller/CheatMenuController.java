package controller.menucontroller;

import controller.duel.GameController;
import models.Player;
import models.User;
import models.cards.Card;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class CheatMenuController {

    public void run(String command, Player cheater) {
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_MONEY)).matches())
            increaseMoney(Integer.parseInt(matcher.group(2)), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SET_WINNER)).matches())
            setWinner(matcher.group(1));
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_LP)).matches())
            increaseLP(Integer.parseInt(matcher.group(2)), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_1)).matches())
            selectCardForce(matcher.group(2), cheater);
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_2)).matches())
            selectCardForce(matcher.group(3), cheater);
        else
            System.out.println(StatusEnum.INVALID_COMMAND);
    }


    private void increaseMoney(int amount, Player cheater) {
        User cheaterUser = User.getUserByUserName(cheater.getUserName());
        cheaterUser.setMoney(cheaterUser.getMoney() + amount);
    }

    private void increaseLP(int amount, Player cheater) {
        cheater.getPlayerBoard().setLifePoints(cheater.getPlayerBoard().getLifePoints() + amount);
    }

    private void setWinner(String nickname) {
        if (DuelController.getPlayer2() == null && DuelController.getBot().getName().equals(nickname))
            DuelController.getBot().getBoard().setLifePoints(0);
        else if (DuelController.getBot() == null && DuelController.getPlayer2().getNickName().equals(nickname))
            DuelController.getPlayer2().getPlayerBoard().setLifePoints(0);
    }

    private void selectCardForce(String cardName, Player cheater) {
        for (Card card: cheater.getPlayerBoard().getHandCards())
            if (card.getName().equals(cardName)) GameController.setSelectedCard(card);
    }
}
