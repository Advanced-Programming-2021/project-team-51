package controller.duel;

import models.Player;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import view.StatusEnum;

import java.util.ArrayList;

public class AttackController {


    //Note That player is always the attacker and opponent is attacker or defender
    public static boolean isBattleHappened;
    public static boolean isAnyMonsterDead = false;
    private Player player;
    private Player opponent;
    private GamePhase gamePhase;
    private ArrayList<MonsterCard> alreadyAttackedCards = new ArrayList<>();
    private final PhaseController phaseController = new PhaseController();

    public AttackController() {
        this.player = PhaseController.playerInTurn;
        this.opponent = PhaseController.playerAgainst;
        this.gamePhase = PhaseController.currentPhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    private void damagePlayer(Player player, int damagePoints) {
        player.setLifePoint(player.getLifePoint() - damagePoints);
    }

    private int isPlayerMonsterStrongerThanOpponent(MonsterCard playerCard, MonsterCard opponentCard) {
        // return 1 if player is stronger. 2 if are the same. 3 if is weaker
        if (calculateDifferenceOPoint(playerCard, opponentCard) > 0) {
            return 1;
        } else if (calculateDifferenceOPoint(playerCard, opponentCard) == 0) {
            return 2;
        } else {
            return 3;
        }

    }

    private boolean areBothMonstersOffensive(MonsterCard playerCard, MonsterCard opponentCard) {
        return (isOffensive(playerCard) && isOffensive(opponentCard));
    }

    private boolean isOffensive(MonsterCard card) {
        return card.getMode().equals(Mode.ATTACK);
    }

    public int calculateDifferenceOPoint(MonsterCard playerCard, MonsterCard opponentCard) {
        if (areBothMonstersOffensive(playerCard, opponentCard)) {
            return playerCard.getAttackPoint() - opponentCard.getAttackPoint();
        } else {
            return playerCard.getAttackPoint() - opponentCard.getDefensePoint();
        }
    }

    public void destroyMonster(MonsterCard playerCard, MonsterCard opponentCard) {


        if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
            this.opponent.getPlayerBoard().removeMonster(opponent.getPlayerBoard().getMonsterIndexInMonsterBoard(opponentCard));
        } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
            if (areBothMonstersOffensive(playerCard, opponentCard)) {
                this.opponent.getPlayerBoard().removeMonster(opponent.getPlayerBoard().getMonsterIndexInMonsterBoard(opponentCard));
                this.player.getPlayerBoard().removeMonster(player.getPlayerBoard().getMonsterIndexInMonsterBoard(playerCard));
            }
        } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
            if (areBothMonstersOffensive(playerCard, opponentCard)) {
                this.player.getPlayerBoard().removeMonster(player.getPlayerBoard().getMonsterIndexInMonsterBoard(playerCard));
            }
        }
        alreadyAttackedCards.add(playerCard);


    }


    //-----------------------Attacks------------------------------
    public String directAttack() {
        //TODO call sum selection functions
        MonsterCard playerCard = (MonsterCard) SelectionController.selectedCard;
        //TODO some ifs
        if (!checkPhaseValidity()) {
            return StatusEnum.CANT_DO_THIS_ACTION_IN_THIS_PHASE.getStatus();
        } else if (checkAlreadyAttacked(playerCard)) {
            return StatusEnum.CARD_ALREADY_ATTACKED.getStatus();
        }
        //TODO Check Card Attack System Validity
        else {
            isBattleHappened = true;
            damagePlayer(this.opponent, playerCard.getAttackPoint());
            alreadyAttackedCards.add(playerCard);
            return "you opponent receives" + playerCard.getAttackPoint() + "battle damage\n";
        }
    }

    public String attackMonsterToMonster(String rivalCardNumber) {
        //TODO call sum selection functions
        MonsterCard opponentCard = null; //TODO Formalite
        MonsterCard playerCard = (MonsterCard) SelectionController.selectedCard;
        //TODO some ifs
        if (!checkPhaseValidity()) {
            return StatusEnum.CANT_DO_THIS_ACTION_IN_THIS_PHASE.getStatus();
        } else if (checkAlreadyAttacked(playerCard)) {
            return StatusEnum.CARD_ALREADY_ATTACKED.getStatus();
        } else if (opponentCard == null) {
            return StatusEnum.NO_CARD_TO_ATTACK_HERE.getStatus();
        } else {
            //--------------------Attack OO-------------------------
            isBattleHappened = true;
            if (areBothMonstersOffensive(playerCard, opponentCard)) {
                destroyMonster(playerCard, opponentCard);
                int damage = calculateDifferenceOPoint(playerCard, opponentCard);

                if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
                    damagePlayer(opponent, damage);
                    return "your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage";
                } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
                    return StatusEnum.BOTH_RECEIVED_DAMAGE_OO.getStatus();
                } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
                    damage *= -1;
                    damagePlayer(player, damage);
                    return "Your monster card is destroyed and you received " + damage + " battle damage";

                }
            }
            //--------------------Attack DH & DO--------------------
            else {
                destroyMonster(playerCard, opponentCard);
                int damage = calculateDifferenceOPoint(playerCard, opponentCard);
                //DO------------------------------------
                if (!opponentCard.getIsHidden()) {
                    if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
                        return StatusEnum.DEFENSE_POSITION_DESTROYED_DO.getStatus();
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
                        return StatusEnum.NO_CARD_DESTROYED_EQUAL_DEFENSES_DO.getStatus();
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
                        damage *= -1;
                        damagePlayer(player, damage);
                        return "no card is destroyed and you received" + damage + "battle damage";
                    }
                }
                //DH-------------------------------------
                else {
                    if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
                        return "opponent’s monster card was " + opponentCard.getName() + " and " + StatusEnum.DEFENSE_POSITION_DESTROYED_DH.getStatus();
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
                        return "opponent’s monster card was " + opponentCard.getName() + " and " + StatusEnum.NO_CARD_DESTROYED_EQUAL_DEFENSES_DO.getStatus();
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
                        damage *= -1;
                        damagePlayer(player, damage);
                        return "opponent’s monster card was " + opponentCard.getName() + " and " + "no card is destroyed and you received" + damage + "battle damage";
                    }
                }

            }
        }
        return null;
    }

    //------------------------------------------------------------
    private boolean checkPhaseValidity() {
        return gamePhase.equals(GamePhase.BATTLE);
    }

    //    private boolean isCardValidInInDamageStep(Card card){
//        //Method Missing
//    } TODO Check Card Validity In Damage And Attack

    private boolean checkAlreadyAttacked(MonsterCard card) {
        for (MonsterCard a : alreadyAttackedCards
        ) {
            if (a == card) {
                return true;
            }
        }
        return false;
    }

    public void checkEndGame() {
        if (Player.getFirstPlayer().getLifePoint() == 0)
            phaseController.endGame(Player.getSecondPlayer(), Player.getFirstPlayer());
        else if (Player.getSecondPlayer().getLifePoint() == 0)
            phaseController.endGame(Player.getFirstPlayer(), Player.getSecondPlayer());
    }

}
