package controller.menucontroller;

import controller.duel.singlePlayer.EasyBot;
import view.StatusEnum;
import controller.duel.singlePlayer.AI;
import controller.duel.singlePlayer.HardBot;
import models.Board;
import models.Player;
import models.User;
import models.cards.Card;
import view.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class DuelController {
    private ArrayList<Integer> attackedCards;
    private ArrayList<Card> lastRoundGraveyards;
    private static Player player1;
    private static Player player2;
    private static AI bot;
    private User currentUser;
    private Board player1Board;
    private Board player2Board;
    private boolean isSummonedThisTurn;
    private int phase;
    private int selectedCardIndex;
    private User selectedCardOwner;
    private String selectedCardAddress;
    private String selectedCardType;
    private int rounds;
    private int player1Wins;
    private int player2Wins;
    private int status;
    private boolean isAgainstBot;


    //------------------------Need to be checked------------------------
    public DuelController() throws CloneNotSupportedException {
        this.currentUser = LoginMenuController.currentUser;
    }


    public void run(String command) throws CloneNotSupportedException {
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_1)).matches()){
            String player2Name = matcher.group(3);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_2)).matches()){
            String player2Name = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(3));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE  TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_3)).matches()){
            String player2Name = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE  TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_4)).matches()){
            String player2Name = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(4));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE  TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_5)).matches()){
            String player2Name = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE  TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_MULTIPLAYER_6)).matches()){
            String player2Name = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (User.getUserByUserName(player2Name) == null){
                System.out.println(StatusEnum.NO_EXISTENCE_OF_PLAYER2);
            }
            else {
                this.player2 = new Player(User.getUserByUserName(player2Name));
            }
            //--------------------Not Sure---------------
            if (this.player1.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+" has no active deck");
            }
            else  if (this.player2.getPlayerDeck() == null){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+" has no active deck");
            }
            else if (!this.player1.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player1.getUserName()+"'s deck is invalid");
            }
            else if (!this.player2.getPlayerDeck().isDeckValid()){
                this.player2 = null;
                System.out.println(this.player2.getUserName()+"'s deck is invalid");
            }
            else if (rounds!=3 && rounds!=1){
                this.player2 = null;
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                //----------------------------------------WHAT ELSE  TO DO?---------------
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_1)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(4));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                this.bot = new EasyBot(player1);//----------
                    }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_2)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(3));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_3)).matches()){
            String difficulty = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_4)).matches()){
            String difficulty = matcher.group(3);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_5)).matches()){
            String difficulty = matcher.group(3);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_6)).matches()){
            String difficulty = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(3));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_7)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(4));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_8)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(3));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_9)).matches()){
            String difficulty = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_9)).matches()){
            String difficulty = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_10)).matches()){
            String difficulty = matcher.group(3);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_11)).matches()){
            String difficulty = matcher.group(3);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_12)).matches()){
            String difficulty = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(3));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_13)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_14)).matches()){
            String difficulty = matcher.group(6);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_15)).matches()){
            String difficulty = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_16)).matches()){
            String difficulty = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_17)).matches()){
            String difficulty = matcher.group(4);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_18)).matches()){
            String difficulty = matcher.group(5);
            Integer rounds = Integer.parseInt(matcher.group(2));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_19)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_20)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(6));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_21)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_22)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(4));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_23)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(4));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.DUEL_SINGLE_PLAYER_24)).matches()){
            String difficulty = matcher.group(2);
            Integer rounds = Integer.parseInt(matcher.group(5));
            if (rounds!=1 && rounds!=3){
                System.out.println(StatusEnum.ROUNDS_NOT_SUPPORTED);
            }
            else{
                this.rounds = rounds;
                this.player1 = new Player(currentUser);
                if (difficulty.equals("easy")){
                    this.bot = new EasyBot(player1);//----------
                }
                else{
                    this.bot = new HardBot(player1);//----------
                }
            }
        }



    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static AI getBot() {
        return bot;
    }

    private void changeStatus(int status){
        this.status = status;
    }
    private void resetTheGraveyard(){}
    private boolean isRondEnded(){return true;}
    private void giveMoney(){}
    private void giveScore(){}
    private void selectCard(){}
    private boolean isSelectionValid(User owner , int index , String type , String address){return true;}
    private boolean isThereAnyCard (User owner , int index , String type , String address){return true;}
    private void deselectCard(){}
    private void summonCard(){}
    private void isNormalSummonValid(){}
    private void isSuperSummonValid(){}
    private void setCard(){}
    private void isSetValid(){}
    private void changePosition(Matcher matcher){}
    private boolean isChangingPositionValid(User owner , int index , String type , String address){return true;}
    private void flipSummon(){}
    private void goToTheNextPhase(){}
    private void attack(Matcher matcher){}
    private boolean isAttackValid(int index){return true;}
    private void directAttack(){}
    private boolean isDirectAttackValid(){return true;}
    private void activeEffect(){}
    private boolean isActivatingEffectValid(){return true;}
    private void activeEffectInOpponentsTurn(){}
    private void ritualSummon(){}
    private boolean isRitualSummonValid(){return true;}
    private void specialSummon(){}
    private boolean isSpecialSummonValid(){return true;}
    private void showGraveYard(){}
    private void showSelectedCard(){}
    private void surrender(){}
    private void activateCheatCode(Matcher matcher){}
    private boolean isThereSpellOrTrapToActivate(){return true;}
    private boolean isActivatingTheSpellReasonable(){return true;}
    private void activateTheSpellForBot(){}
    private ArrayList<Integer> getTheLowestCards(){}
    private Card getTheBestCard(){}
    private void summonCardForBot(){}
    private ArrayList<Integer> getTheSortedCardsByPower(){}
    private boolean isAttackingToMonsterReasonable(){return true;}
    private void attackForBot(){}
    private void playForBot(){}








}
