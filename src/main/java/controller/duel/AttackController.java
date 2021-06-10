package controller.duel;

import models.Player;
import models.cards.Card;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import view.DuelView;
import view.StatusEnum;

import java.util.ArrayList;

public class AttackController {


    //Note That player is always the attacker and opponent is attacker or defender
    private Player player;
    private Player opponent;
    private GamePhase gamePhase;
    private ArrayList<MonsterCard> alreadyAttackedCards = new ArrayList<>();

    public AttackController(Player attacker, Player opponent, GamePhase gamePhase) {
        this.player = attacker;
        this.opponent = opponent;
        this.gamePhase = gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    private void damagePlayer(Player player, int damagePoints){
            player.setLifePoint(player.getLifePoint()-damagePoints);
    }

    private int isPlayerMonsterStrongerThanOpponent(MonsterCard playerCard , MonsterCard opponentCard){
        // return 1 if player is stronger. 2 if are the same. 3 if is weaker
        if (calculateDifferenceOPoint(playerCard,opponentCard) > 0){
            return 1;
        }
        else if (calculateDifferenceOPoint(playerCard,opponentCard) == 0){
            return 2;
        }
        else {
            return 3;
        }

    }

    private boolean areBothMonstersOffensive(MonsterCard playerCard , MonsterCard opponentCard){
        return  (isOffensive(playerCard) && isOffensive(opponentCard));
    }

    private boolean isOffensive(MonsterCard card) {
        if (card.getMode().equals(Mode.ATTACK)){
            return true;
        }
        return false;
    }

    public int calculateDifferenceOPoint(MonsterCard playerCard , MonsterCard opponentCard){
        if (areBothMonstersOffensive(playerCard,opponentCard)){
            return playerCard.getAttackPoint() - opponentCard.getAttackPoint();
        }
        else {
            return playerCard.getAttackPoint() - opponentCard.getDefensePoint();
        }
    }

    public void destroyMonster(MonsterCard playerCard , MonsterCard opponentCard){


           if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 1){
               this.opponent.getPlayerBoard().removeMonster(opponent.getPlayerBoard().getMonsterIndexInMonsterBoard(opponentCard));
           }
           else if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 2){
               if (areBothMonstersOffensive(playerCard,opponentCard)) {
                   this.opponent.getPlayerBoard().removeMonster(opponent.getPlayerBoard().getMonsterIndexInMonsterBoard(opponentCard));
                   this.player.getPlayerBoard().removeMonster(player.getPlayerBoard().getMonsterIndexInMonsterBoard(playerCard));
               }
           }
           else if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 3){
               if (areBothMonstersOffensive(playerCard,opponentCard)) {
                   this.player.getPlayerBoard().removeMonster(player.getPlayerBoard().getMonsterIndexInMonsterBoard(playerCard));
               }
           }
           alreadyAttackedCards.add(playerCard);


    }


//-----------------------Attacks------------------------------
    public void directAttack(MonsterCard playerCard){
        if (!checkPhaseValidity()){
            System.out.println(StatusEnum.CANT_DO_THIS_ACTION_IN_THIS_PHASE);
        }
        else if (checkAlreadyAttacked(playerCard)){
            System.out.println(StatusEnum.CARD_ALREADY_ATTACKED);
        }
        //TODO Check Card Attack System Validity
        else {
            damagePlayer(this.opponent, playerCard.getAttackPoint());
            alreadyAttackedCards.add(playerCard);
            System.out.println("you opponent receives"+ playerCard.getAttackPoint() +"battle damage\n");
        }
    }

    public void attackMonsterToMonster(MonsterCard playerCard , MonsterCard opponentCard){
        if (!checkPhaseValidity()){
            System.out.println(StatusEnum.CANT_DO_THIS_ACTION_IN_THIS_PHASE);
        }
        else if (checkAlreadyAttacked(playerCard)){
            System.out.println(StatusEnum.CARD_ALREADY_ATTACKED);
        }
        else if (opponentCard == null){
            System.out.println(StatusEnum.NO_CARD_TO_ATTACK_HERE);
        }
        else{
            //--------------------Attack OO-------------------------
            if (areBothMonstersOffensive(playerCard,opponentCard)){
                destroyMonster(playerCard,opponentCard);
                int damage = calculateDifferenceOPoint(playerCard,opponentCard);

                if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 1){
                    damagePlayer(opponent,damage);
                    System.out.println("your opponent’s monster is destroyed and your opponent receives "+damage +" battle damage");
                }
                else  if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 2){
                    System.out.println(StatusEnum.BOTH_RECEIVED_DAMAGE_OO);
                }
                else  if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 3){
                    damage*=-1;
                    damagePlayer(player,damage);
                    System.out.println("Your monster card is destroyed and you received " +damage +" battle damage");

                }
                }
            //--------------------Attack DH & DO--------------------
            else{
                destroyMonster(playerCard,opponentCard);
                int damage = calculateDifferenceOPoint(playerCard,opponentCard);
                //DO------------------------------------
                if (!opponentCard.getIsHidden()) {
                    if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
                        System.out.println(StatusEnum.DEFENSE_POSITION_DESTROYED_DO);
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
                        System.out.println(StatusEnum.NO_CARD_DESTROYED_EQUAL_DEFENSES_DO);
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
                        damage *= -1;
                        damagePlayer(player, damage);
                        System.out.println("no card is destroyed and you received" + damage + "battle damage");
                    }
                }
                //DH-------------------------------------
                else{
                    if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 1) {
                        System.out.println("opponent’s monster card was "+ opponentCard.getName() +" and "+StatusEnum.DEFENSE_POSITION_DESTROYED_DH);
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 2) {
                        System.out.println("opponent’s monster card was "+ opponentCard.getName() +" and "+StatusEnum.NO_CARD_DESTROYED_EQUAL_DEFENSES_DO);
                    } else if (isPlayerMonsterStrongerThanOpponent(playerCard, opponentCard) == 3) {
                        damage *= -1;
                        damagePlayer(player, damage);
                        System.out.println("opponent’s monster card was "+ opponentCard.getName() +" and "+"no card is destroyed and you received" + damage + "battle damage");
                    }
                }

            }
            }

        }

//------------------------------------------------------------
    private boolean checkPhaseValidity(){
        return gamePhase.equals(GamePhase.BATTLE) ;
    }

    //    private boolean isCardValidInInDamageStep(Card card){
//        //Method Missing
//    } TODO Check Card Validity In Damage And Attack

    private boolean checkAlreadyAttacked(MonsterCard card){
        for (MonsterCard a: alreadyAttackedCards
             ) {
            if (a == card){
                return true;
            }
        }
        return false;
    }








}
