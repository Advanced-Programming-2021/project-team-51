package controller.duel;

import models.Deck;
import models.Board;

public class HardBot extends AI {

    public HardBot(Deck deck) throws CloneNotSupportedException {
        setName("HardBot");
        setDeck(deck);
        setBoard(new Board(this));
    }
    
}
