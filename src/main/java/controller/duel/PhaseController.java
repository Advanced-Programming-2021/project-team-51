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
    public static Player playerInTurn;
    public static Player playerAgainst;
    public static GamePhase currentPhase = GamePhase.DRAW;
    public static boolean isFirstPlay = true;

    public void startTheGame() {
        int coin = (int) (Math.random() * 2);
        if (coin == 0) {
            playerInTurn = Player.getFirstPlayer();
            playerAgainst = Player.getSecondPlayer();
        } else {
            playerInTurn = Player.getSecondPlayer();
            playerAgainst = Player.getFirstPlayer();
        }
        System.out.println("Coin has Flipped and " + playerInTurn.getNickName() + " goes First");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(printBoard());
    }

    public String printBoard() {
        String middleLine = "\n--------------------------\n";
        if (!DuelView.isMultiPlayer) {
            String playerBoard = Player.getFirstPlayer().getPlayerBoard().toString();
            String botBoard = AI.getInstance().getBoard().reverseToString();
            return botBoard + middleLine + playerBoard;
        }
            String firstBoard = playerInTurn.getPlayerBoard().toString();
            String secondBoard = playerAgainst.getPlayerBoard().reverseToString();
            return secondBoard + middleLine + firstBoard;
    }

    public String changePhase() {
        AttackController.alreadyAttackedCards.clear();
        AttackController.isBattleHappened = false;
        findNextPhase();
        StringBuilder result = new StringBuilder(currentPhase.getLabel());
        if (currentPhase == GamePhase.DRAW) {
            SummonController.hasSummonedInThisTurn = false;
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
        else if (currentPhase == GamePhase.RIVAL_TURN) {
            result.append("\nit's ").append(playerAgainst.getNickName()).append("'s turn to activate quick spells or traps");
            Player keepPlayer = PhaseController.playerInTurn;
            playerInTurn = playerAgainst;
            playerAgainst = keepPlayer;
        }
        else if (currentPhase == GamePhase.MAIN2) {
            result.append("\nit's ").append(playerAgainst.getNickName()).append("'s turn again to respond to rival");
            Player keepPlayer = PhaseController.playerInTurn;
            playerInTurn = playerAgainst;
            playerAgainst = keepPlayer;
        }
        else if (currentPhase == GamePhase.END) {
            result.append("\nit's ").append(playerAgainst.getNickName()).append("'s turn");
            //TODO what about ai
            ContinuousEffects.run(playerInTurn.getPlayerBoard(), playerAgainst.getPlayerBoard());
            Chain.activate();
            SelectionController.selectedCard = null;
            resetSomeEffects();
            SummonController.hasSummonedInThisTurn = false;
            System.out.println(changePhase());
            isFirstPlay = false;
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
        else if (currentPhase == GamePhase.BATTLE)
            currentPhase = GamePhase.RIVAL_TURN;
        else if (currentPhase == GamePhase.RIVAL_TURN)
            currentPhase = GamePhase.MAIN2;
        else if (currentPhase == GamePhase.MAIN2)
            currentPhase = GamePhase.END;
        else
            currentPhase = GamePhase.DRAW;
    }

    public void endGame(Player winner, Player looser) {
        User winnerUser = winner.getUser();
        User loserUser = looser.getUser();
        if (DuelView.rounds == 1) {
            winnerUser.setMoney(winnerUser.getMoney() + 1000 + winner.getPlayerBoard().getLifePoints());
            loserUser.setMoney(loserUser.getMoney() + 100);
            winnerUser.setScore(winnerUser.getScore() + 1000);
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
            Player.removePlayers();
            System.out.println(winnerUser.getUserName() + " won the whole match with score: 1-0");
            DuelView.shouldDrawBoard = false;
        }
        else {
            if (winner.equals(Player.getFirstPlayer()))
                DuelView.player1Wins++;
            else
                DuelView.player2Wins++;
            winner.setMaxLifePoint(winner.getPlayerBoard().getLifePoints());
            looser.setMaxLifePoint(looser.getPlayerBoard().getLifePoints());
            if (DuelView.player2Wins == 2 || DuelView.player1Wins == 2) {
                loserUser.setMoney(loserUser.getMoney() + 300);
                winnerUser.setScore(winnerUser.getScore() + 3000);
                winnerUser.setMoney(winnerUser.getMoney() + winner.getMaxLifePoint() * 3 + 3000);
                ProgramController.currentMenu = MenuEnum.MAIN_MENU;
                Player.removePlayers();
                System.out.println(winnerUser.getUserName() + " won the whole match with score: " +
                        DuelView.player1Wins + "-" + DuelView.player2Wins);
                DuelView.shouldDrawBoard = false;
            }
            else {
                System.out.println(winnerUser.getUserName() + "won the game and the score is: " +
                        DuelView.player1Wins + "-" + DuelView.player2Wins);
                Player.resetPlayers();
                isFirstPlay = true;
                currentPhase = GamePhase.DRAW;
            }
        }
    }
}
