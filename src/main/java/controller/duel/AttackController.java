package controller.duel;

import models.Player;
import models.cards.Card;

public class AttackController {


    private void damagePlayer(Player player, int damagePoints){

            player.setLifePoint(player.getLifePoint()-damagePoints);
    }
    private boolean isDamageValid(){
    }
    private boolean isCardValidInInDamageStep(Card card){

    }

}
