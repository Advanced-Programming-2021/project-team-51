package controller.duel;

import models.Player;
import models.cards.Card;
import models.cards.monsters.MonsterCard;

public class AttackController {

    private Player player;
    private Player opponent;



    public AttackController(Player player,Player opponent){
        this.player = player;
        this.opponent = opponent;
    }




    private void damagePlayer(Player player, int damagePoints){
            player.setLifePoint(player.getLifePoint()-damagePoints);
    }
    private boolean isDamageValid(MonsterCard playerCard , MonsterCard opponentCard){
        if (areBothMonstersOffensive(playerCard,opponentCard)){
            return isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard)==1 || isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard)==1;
        }
        else {
            return isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard)==3;
        }
    }

//    private boolean isCardValidInInDamageStep(Card card){
//        //Method Missing
//    }
    public int getAttackPoints(MonsterCard card) {
        return card.getAttackPoint();
    }

    public int getDefensePoints(MonsterCard card) {
        return card.getDefensePoint();
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
        if (!card.getIsHidden()){
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
            this.opponent.getPlayerBoard().addToGraveyard(opponentCard);
        }
        else if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 2){
            this.opponent.getPlayerBoard().addToGraveyard(playerCard);
            this.opponent.getPlayerBoard().addToGraveyard(opponentCard);
        }
        else if (isPlayerMonsterStrongerThanOpponent(playerCard,opponentCard) == 3){
            this.opponent.getPlayerBoard().addToGraveyard(playerCard);

        }
    }





}