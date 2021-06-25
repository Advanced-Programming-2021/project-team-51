package controller.duel;

import controller.duel.monsterseffect.ContinuousEffects;
import controller.duel.monsterseffect.TurnEffects;
import controller.duel.singlePlayer.AI;
import controller.duel.spells.OnMonsterSpells;
import controller.duel.spells.FiledSpells;
import controller.duel.spells.MessengerOfPeace;
import controller.duel.spells.TurnSpells;
import models.Chain;
import models.Player;
import models.User;
import models.cards.Card;
import view.DuelView;
import view.MenuEnum;
import view.ProgramController;

public class PhaseController {
    public static boolean isFirstPlayerTurn = true;
    public static Player playerInTurn;
    public static Player playerAgainst;
    public static GamePhase currentPhase = GamePhase.DRAW;

    public String printBoard() {
        String middleLine = "\n--------------------------\n";
        if (!DuelView.isMultiPlayer) {
            String playerBoard = Player.getFirstPlayer().getPlayerBoard().toString();
            String botBoard = AI.getInstance().getBoard().reverseToString();
            return botBoard + middleLine + playerBoard;
        }
        if (isFirstPlayerTurn) {
            String firstBoard = Player.getFirstPlayer().getPlayerBoard().toString();
            String secondBoard = Player.getSecondPlayer().getPlayerBoard().reverseToString();
            return secondBoard + middleLine + firstBoard;
        }
        else {
            String firstBoard = Player.getFirstPlayer().getPlayerBoard().reverseToString();
            String secondBoard = Player.getSecondPlayer().getPlayerBoard().toString();
            return firstBoard + middleLine + secondBoard;
        }
    }

    public String changePhase() {
        findNextPhase();
        StringBuilder result = new StringBuilder(currentPhase.getLabel());
        if (currentPhase == GamePhase.DRAW) {
            Player keepPlayer = PhaseController.playerInTurn;
            playerInTurn = playerAgainst;
            playerAgainst = keepPlayer;
            Card card = playerInTurn.getPlayerBoard().drawCard();
            if (card == null)
                endGame(playerAgainst, playerInTurn);
            assert card != null;
            result.append("\nnew card added to hand: ").append(card.getName());
        }
        else if (currentPhase == GamePhase.STANDBY) {
            //TODO what about ai
            TurnEffects.run(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
            FiledSpells.check(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
            MessengerOfPeace.checkStandBy(playerInTurn.getPlayerBoard());
            playerAgainst.getPlayerBoard().getEffectsStatus().setCanRivalPickCard(true);
        }
        else if (currentPhase == GamePhase.BATTLE) {
            //TODO what about ai
            ContinuousEffects.run(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
        }
        else if (currentPhase == GamePhase.END) {
            result.append("\nit's ").append(playerAgainst.getNickName()).append("'s turn");
            //TODO what about ai
            ContinuousEffects.run(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
            Chain.activate();
            SelectionController.selectedCard = null;
            resetSomeEffects();
        }
        return result.toString();
    }

    private void resetSomeEffects() {
        playerInTurn.getPlayerBoard().getEffectsStatus().setRivalTrapsBlocked(false);
        playerAgainst.getPlayerBoard().getEffectsStatus().setRivalTrapsBlocked(false);
        AttackController.isAnyMonsterDead = false;
        AttackController.isBattleHappened = false;
        TurnSpells.checkTurn(playerInTurn.getPlayerBoard());
        OnMonsterSpells.deactivate(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
    }

    private void findNextPhase() {
        if (currentPhase == GamePhase.DRAW)
            currentPhase = GamePhase.STANDBY;
        else if (currentPhase == GamePhase.STANDBY)
            currentPhase = GamePhase.MAIN1;
        else if (currentPhase == GamePhase.MAIN1)
            currentPhase = GamePhase.BATTLE;
        else if (currentPhase == GamePhase.BATTLE && AttackController.isBattleHappened)
            currentPhase = GamePhase.MAIN2;
        else if (currentPhase == GamePhase.BATTLE)
            currentPhase = GamePhase.END;
        else
            currentPhase = GamePhase.DRAW;
    }

    public void endGame(Player winner, Player looser) {
        User winnerUser = winner.getUser();
        User loserUser = looser.getUser();
        if (DuelView.rounds == 1) {
            winnerUser.setMoney(winnerUser.getMoney() + 1000 + winner.getLifePoint());
            loserUser.setMoney(loserUser.getMoney() + 100);
            winnerUser.setScore(winnerUser.getScore() + 1000);
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
            Player.removePlayers();
        }
        else {
            if (winner.equals(Player.getFirstPlayer()))
                DuelView.player1Wins++;
            else
                DuelView.player2Wins++;
            winner.setMaxLifePoint(winner.getLifePoint());
            looser.setMaxLifePoint(looser.getLifePoint());
            if (DuelView.player2Wins == 2 || DuelView.player1Wins == 2) {
                loserUser.setMoney(loserUser.getMoney() + 300);
                winnerUser.setScore(winnerUser.getScore() + 3000);
                winnerUser.setMoney(winnerUser.getMoney() + winner.getMaxLifePoint() * 3 + 3000);
                ProgramController.currentMenu = MenuEnum.MAIN_MENU;
                Player.removePlayers();
            }
            else
                Player.resetPlayers();
        }
    }
}
