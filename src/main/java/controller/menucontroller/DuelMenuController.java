package controller.menucontroller;

import controller.duel.singlePlayer.EasyBot;
import controller.duel.singlePlayer.HardBot;
import models.Player;
import models.User;
import view.DuelView;
import view.StatusEnum;

import java.util.Objects;

public class DuelMenuController {

    public String startTwoPlayer(User currentUser, String secondPlayer, String rounds) throws CloneNotSupportedException {
        if (!User.isUserNameTaken(secondPlayer))
            return StatusEnum.NO_EXISTENCE_OF_PLAYER2.getStatus();
        if (currentUser.getActiveDeck() == null)
            return currentUser.getUserName() + " has no active deck";
        if (Objects.requireNonNull(User.getUserByUserName(secondPlayer)).getActiveDeck() == null)
            return secondPlayer + " has no active deck";
        if (!currentUser.getActiveDeck().isDeckValid())
            return currentUser.getUserName() + "'s deck is invalid";
        if (!Objects.requireNonNull(User.getUserByUserName(secondPlayer)).getActiveDeck().isDeckValid())
            return secondPlayer + "'s deck is invalid";
        int round = Integer.parseInt(rounds);
        if (round != 3 && round != 1)
            return StatusEnum.ROUNDS_NOT_SUPPORTED.getStatus();
        DuelView.rounds = round;
        DuelView.isMultiPlayer = true;
        new Player(currentUser);
        new Player(Objects.requireNonNull(User.getUserByUserName(secondPlayer)));
        return "";
    }

    public String startSinglePlayer(User currentUser, String rounds, String difficulty) throws CloneNotSupportedException {
        if (currentUser.getActiveDeck() == null)
            return currentUser.getUserName() + " has no active deck";
        if (!currentUser.getActiveDeck().isDeckValid())
            return currentUser.getUserName() + "'s deck is invalid";
        int round = Integer.parseInt(rounds);
        if (round != 3 && round != 1)
            return StatusEnum.ROUNDS_NOT_SUPPORTED.getStatus();
        DuelView.isMultiPlayer = false;
        Player player = new Player(currentUser);
        DuelView.rounds = round;
        if (difficulty.equals("easy"))
            new EasyBot(player);
        else
            new HardBot(player);
        return "";
    }
}
