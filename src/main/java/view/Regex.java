package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    /*---LOGIN---*/

    public static String loginUser1 = "^user login (--username|-u) (\\S+) (--password|-p) (\\S+)$";
    public static String loginUser2 = "^user login (--password|-p) (\\S+) (--username|-u) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---ENTER MENU,EXIT MENU,SHOW MENU---*/

    public static String enterMenu = "^menu enter (\\S+)$";
    public static String exitMenu = "^menu exit$";
    public static String showCurrentMenu = "^menu show-current$";
    //----------------------------------------------------------------------------------------------

    /*---CREATE USER---*/

    public static String createUser1 = "^user create (--username|-u) (\\S+) (--nickname|-n) (\\S+) (--password|-p) (\\S+)$";
    public static String createUser2 = "^user create (--username|-u) (\\S+) (--password|-p) (\\S+) (--nickname|-n) (\\S+)$";
    public static String createUser3 = "^user create (--nickname|-n) (\\S+) (--username|-u) (\\S+) (--password|-p) (\\S+)$";
    public static String createUser4 = "^user create (--nickname|-n) (\\S+) (--password|-p) (\\S+) (--username|-u) (\\S+)$";
    public static String createUser5 = "^user create (--password|-p) (\\S+) (--nickname|-n) (\\S+) (--username|-u) (\\S+)$";
    public static String createUser6 = "^user create (--password|-p) (\\S+) (--username|-u) (\\S+) (--nickname|-n) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---LOGOUT,SHOW SCOREBOARD,CHANGE PROFILE---*/

    public static String userLogout = "^user logout$";
    public static String showScoreboard = "^scoreboard show$";
    public static String changeProfile = "^profile change (--nickname|-n) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---CHANGE PASS---*/

    public static String changePass1 = "^profile change (--password|-p) (--current|-c) (\\S+) (--new|-n) (\\S+)$";
    public static String changePass2 = "^profile change (--password|-p) (--new|-n) (\\S+) (--current|-c) (\\S+)$";
    public static String changePass3 = "^profile change (--current|-c) (\\S+) (--password|-p) (--new|-n) (\\S+)$";
    public static String changePass4 = "^profile change (--current|-c) (\\S+) (--new|-n) (\\S+) (--password|-p)$";
    public static String changePass5 = "^profile change (--new|-n) (\\S+) (--password|-p) (--current|-c) (\\S+)$";
    public static String changePass6 = "^profile change (--new|-n) (\\S+) (--current|-c) (\\S+) (--password|-p)$";
    //----------------------------------------------------------------------------------------------

    /*---DECK CREATION,DELETION,ACTIVATION---*/

    public static String createDeck = "^deck create (\\S+)$";
    public static String deleteDeck = "^deck delete (\\S+)$";
    public static String activateDeck = "^deck set-activate (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---ADD CARD TO DECK---*/

    public static String addCardToDeck1 = "^deck add-card (--card|-c) (\\S+) (--deck|-d) (\\S+)( --side| -s)?$";
    public static String addCardToDeck2 = "^deck add-card (--card|-c) (\\S+)( --side| -s)? (--deck|-d) (\\S+)$";
    public static String addCardToDeck3 = "^deck add-card (--deck|-d) (\\S+) (--card|-c) (\\S+)( --side| -s)?$";
    public static String addCardToDeck4 = "^deck add-card (--deck|-d) (\\S+)( --side| -s)? (--card|-c) (\\S+)$";
    public static String addCardToDeck5 = "^deck add-card( --side| -s)? (--deck|-d) (\\S+) (--card|-c) (\\S+)$";
    public static String addCardToDeck6 = "^deck add-card( --side| -s)? (--card|-c) (\\S+) (--deck|-d) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---REMOVE CARD FROM DECK---*/

    public static String removeCardFromDeck1 = "^deck rm-card (--card|-c) (\\S+) (--deck|-d) (\\S+)( --side| -s)?$";
    public static String removeCardFromDeck2 = "^deck rm-card (--card|-c) (\\S+)( --side| -s)? (--deck|-d) (\\S+)$";
    public static String removeCardFromDeck3 = "^deck rm-card (--deck|-d) (\\S+) (--card|-c) (\\S+)( --side| -s)?$";
    public static String removeCardFromDeck4 = "^deck rm-card (--deck|-d) (\\S+)( --side| -s)? (--card|-c) (\\S+)$";
    public static String removeCardFromDeck5 = "^deck rm-card( --side| -s)? (--deck|-d) (\\S+) (--card|-c) (\\S+)$";
    public static String removeCardFromDeck6 = "^deck rm-card( --side| -s)? (--card|-c) (\\S+) (--deck|-d) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---SHOW DECK---*/

    public static String showAllDecks = "^deck show (--all|-a)$";
    public static String showOptionalDeck1 = "^deck show (--deck-name|-d) (\\S+)( --side| -s)?$";
    public static String showOptionalDeck2 = "^deck show( --side| -s)? (--deck-name|-d) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---SHOW CARD,BUY CARD---*/

    public static String showAllUserCards = "^deck show (--cards|-c)$";
    public static String buyCard = "^shop buy (\\S+)$";
    public static String showCardsInShop = "^shop show (--all|-a)$";
    //----------------------------------------------------------------------------------------------

    /*---DUEL MULTI---*/

    public static String duelMultiplayer1 = "^duel (--new|-n) (--second-player|-sp) (\\S+) (--rounds|-r) (\\d+)$";
    public static String duelMultiplayer2 = "^duel (--new|-n) (--rounds|-r) (\\d+) (--second-player|-sp) (\\S+)$";
    public static String duelMultiplayer3 = "^duel (--second-player|-sp) (\\S+) (--new|-n) (--rounds|-r) (\\d+)$";
    public static String duelMultiplayer4 = "^duel (--second-player|-sp) (\\S+) (--rounds|-r) (\\d+) (--new|-n)$";
    public static String duelMultiplayer5 = "^duel (--rounds|-r) (\\d+) (--second-player|-sp) (\\S+) (--new|-n)$";
    public static String duelMultiplayer6 = "^duel (--rounds|-r) (\\d+) (--new|-n) (--second-player|-sp) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---DUEL SINGLE---*/

    //What is sort form of ai?:|
    public static String duelSinglePlayer1 = "^duel (--new|-n) (--ai|-ai) (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer2 = "^duel (--new|-n) (--rounds|-r) (1|3) (--ai|-ai) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer3 = "^duel (--new|-n) (--ai|-ai) (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer4 = "^duel (--new|-n) (--difficulty|-d) (easy|hard) (--ai|-ai) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer5 = "^duel (--new|-n) (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3) (--ai|-ai)$";
    public static String duelSinglePlayer6 = "^duel (--new|-n) (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard) (--ai|-ai)$";
    //===============================
    public static String duelSinglePlayer7 = "^duel (--ai|-ai) (--new|-n) (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer8 = "^duel (--ai|-ai) (--rounds|-r) (1|3) (--new|-n) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer9 = "^duel (--ai|-ai) (--new|-n) (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer10 = "^duel (--ai|-ai) (--difficulty|-d) (easy|hard) (--new|-n) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer11 = "^duel (--ai|-ai) (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3) (--new|-n)$";
    public static String duelSinglePlayer12 = "^duel (--ai|-ai) (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard) (--new|-n)$";
    //==============================
    public static String duelSinglePlayer13 = "^duel (--rounds|-r) (1|3) (--new|-n) (--ai|-ai) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer14 = "^duel (--rounds|-r) (1|3) (--ai|-ai) (--new|-n) (--difficulty|-d) (easy|hard)$";
    public static String duelSinglePlayer15 = "^duel (--rounds|-r) (1|3) (--new|-n) (--difficulty|-d) (easy|hard) (--ai|-ai)$";
    public static String duelSinglePlayer16 = "^duel (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard) (--new|-n) (--ai|-ai)$";
    public static String duelSinglePlayer17 = "^duel (--rounds|-r) (1|3) (--difficulty|-d) (easy|hard) (--ai|-ai) (--new|-n)$";
    public static String duelSinglePlayer18 = "^duel (--rounds|-r) (1|3) (--ai|-ai) (--difficulty|-d) (easy|hard) (--new|-n)$";
    //==============================
    public static String duelSinglePlayer19 = "^duel (--difficulty|-d) (easy|hard) (--new|-n) (--ai|-ai) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer20 = "^duel (--difficulty|-d) (easy|hard) (--ai|-ai) (--new|-n) (--rounds|-r) (1|3)$";
    public static String duelSinglePlayer21 = "^duel (--difficulty|-d) (easy|hard) (--new|-n) (--rounds|-r) (1|3) (--ai|-ai)$";
    public static String duelSinglePlayer22 = "^duel (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3) (--new|-n) (--ai|-ai)$";
    public static String duelSinglePlayer23 = "^duel (--difficulty|-d) (easy|hard) (--rounds|-r) (1|3) (--ai|-ai) (--new|-n)$";
    public static String duelSinglePlayer24 = "^duel (--difficulty|-d) (easy|hard) (--ai|-ai) (--rounds|-r) (1|3) (--new|-n)$";
    //----------------------------------------------------------------------------------------------

    /*---SELECTION AND DESELECTION---*/

    public static String selectHandCard = "^select (--hand|-h) (\\d+)$";
    public static String selectOwnMasterOrSpellCard = "^select (--master|-m|--spell|-s) (\\d+)$";
    public static String selectOwnField = "^select (--field|-f) (\\d+)$";
    public static String selectOpponentField1 = "^select (--opponent|-o) (--field|-f) (\\d+)$";
    public static String selectOpponentField2 = "^select (--field|-f) (\\d+) (--opponent|-o)$";
    public static String selectOpponentMasterOrSpellCard1 = "^select (--opponent|-p) (--master|-m|--spell|-s) (\\d+)$";
    public static String selectOpponentMasterOrSpellCard2 = "^select (--master|-m|--spell|-s) (\\d+) (--opponent|-p)$";
    public static String deselectCard = "^select -d$";
    //----------------------------------------------------------------------------------------------

    /*---OTHER DUEL COMMANDS---*/

    public static String summon = "^summon$";
    public static String setMonsterCard = "^set$";
    public static String setCardPosition = "^set (--position|-p) (attack|defense)$";
    public static String flipSummon = "^flip-summon$";
    public static String attackMonster = "^attack (\\d+)$";
    public static String attackDirect = "^attack direct$";
    public static String activateEffect= "^activate effect$";
    public static String setSpell = "^set$";
    public static String setTrap = "^set$";
    public static String showGraveyard = "^show graveyard$";
    public static String backToGame = "^back$";
    public static String showSelectedCard = "^card show (--selected|-s)$";
    public static String surrender = "^surrender$";
    //----------------------------------------------------------------------------------------------

    /*---CHEATS---*/

    public static String cheatIncreaseMoney = "^increase (--money|-m) (\\d+)$";
    public static String cheatSelectMoreCards1 = "^select (--hand|-h) (\\S+) (--force|-f)$";
    public static String cheatSelectMoreCards2 = "^select (--force|-f) (--hand|-h) (\\S+)$";
    public static String cheatIncreaseLP = "^increase (--LP|-l) (\\d+)$";
    public static String cheatSetWinner = "^duel set-winner (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---IMPORT EXPORT---*/

    public static String importCard = "^import card (\\S+)$";
    public static String exportCard = "^export card (\\S+)$";

    //----------------------------------------------------------------------------------------------

    /*---CANCEL---*/

    public static String cancelCommand = "^cancel$";
    //----------------------------------------------------------------------------------------------

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


}
