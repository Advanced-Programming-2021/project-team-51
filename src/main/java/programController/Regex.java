package programController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*-------------------REMEMBER TO CORRECT SHORT FORMS----------------------*/


public class Regex {

    /*---LOGIN---*/

    public static String loginUser1 = "^user login --username (\\S+) --password (\\S+)$";
    public static String loginUser1Short = "^user login -u (\\S+) -p (\\S+)$";
    public static String loginUser2 = "^user login --password (\\S+) (\\S+) --username (\\S+)$";
    public static String loginUser2Short = "^user login -p (\\S+) -u (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---ENTER MENU,EXIT MENU,SHOW MENU---*/

    public static String enterMenu = "^menu enter (\\S+)$";
    public static String exitMenu = "^menu exit$";
    public static String showCurrentMenu = "^menu show-current$";
    //----------------------------------------------------------------------------------------------

    /*---CREATE USER---*/

    public static String createUser1 = "^user create --username (\\S+) --nickname (\\S+) --password (\\S+)$";
    public static String createUser1Short = "^user create -u (\\S+) -n (\\S+) -p (\\S+)$";
    public static String createUser2 = "^user create --username (\\S+) --password (\\S+) --nickname (\\S+)$";
    public static String createUser2Short = "^user create -u (\\S+) -p (\\S+) -n (\\S+)$";
    public static String createUser3 = "^user create --nickname (\\S+) --username (\\S+) --password (\\S+)$";
    public static String createUser3Short = "^user create -n (\\S+) -u (\\S+) -p (\\S+)$";
    public static String createUser4 = "^user create --nickname (\\S+) --password (\\S+) --username (\\S+)$";
    public static String createUser4Short = "^user create -n (\\S+) -p (\\S+) -u (\\S+)$";
    public static String createUser5 = "^user create --password (\\S+) --nickname (\\S+) --username (\\S+)$";
    public static String createUser5Short = "^user create -p (\\S+) -n (\\S+) -u (\\S+)$";
    public static String createUser6 = "^user create --password (\\S+) --username (\\S+) --nickname (\\S+)$";
    public static String createUser6Short = "^user create -p (\\S+) -u (\\S+) -n (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---LOGOUT,SHOW SCOREBOARD,CHANGE PROFILE---*/

    public static String userLogout = "^user logout$";
    public static String showScoreboard = "^scoreboard show$";
    public static String changeProfile = "^profile change --nickname (\\S+)$";
    public static String changeProfileShort = "^profile change -n (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---CHANGE PASS---*/

    public static String changePass1 = "^profile change --password --current (\\S+) --new (\\S+)$";
    public static String changePass1Short = "^profile change -p -c (\\S+) -n (\\S+)$";
    public static String changePass2= "^profile change --new (\\S+) --password --current (\\S+)$";
    public static String changePass2Short= "^profile change -n (\\S+) -p -c (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---DECK CREATION,DELETION,ACTIVATION---*/

    public static String createDeck = "^deck create (\\S+)$";
    public static String deleteDeck = "^deck delete (\\S+)$";
    public static String activateDeck = "^deck set-activate (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---ADD CARD TO DECK---*/

    public static String addCardToDeck1 = "^deck add-card --card (\\S+) --deck (\\S+)( --side)?$";
    public static String addCardToDeck1Short = "^deck add-card -c (\\S+) -d (\\S+)( -s)?$";
    public static String addCardToDeck2 = "^deck add-card --card (\\S+)( --side)? --deck (\\S+)$";
    public static String addCardToDeck2Short = "^deck add-card -c (\\S+)( -s)? -d (\\S+)$";
    public static String addCardToDeck3 = "^deck add-card --deck (\\S+) --card (\\S+)( --side)?$";
    public static String addCardToDeck3Short = "^deck add-card -d (\\S+) -c (\\S+)( -s)?$";
    public static String addCardToDeck4 = "^deck add-card --deck (\\S+)( --side)? --card (\\S+)$";
    public static String addCardToDeck4Short = "^deck add-card -d (\\S+)( -s)? -c (\\S+)$";
    public static String addCardToDeck5 = "^deck add-card( --side)? --deck (\\S+) --card (\\S+)$";
    public static String addCardToDeck5Short = "^deck add-card( -s)? -d (\\S+) -c (\\S+)$";
    public static String addCardToDeck6 = "^deck add-card( --side)? --card (\\S+) --deck (\\S+)$";
    public static String addCardToDeck6Short = "^deck add-card( -s)? -c (\\S+) -d (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---REMOVE CARD FROM DECK---*/

    public static String removeCardFromDeck1 = "^deck rm-card (--card|-c) (\\S+) (--deck|-d) (\\S+)( --side|-s)?$";
    public static String removeCardFromDeck2 = "^deck rm-card (--card|-c) (\\S+)( --side|-s)? (--deck|-d) (\\S+)$";
    public static String removeCardFromDeck3 = "^deck rm-card (--deck|-d) (\\S+) (--card|-c) (\\S+)( --side|-s)?$";
    public static String removeCardFromDeck4 = "^deck rm-card --deck (\\S+)( --side)? --card (\\S+)$";
    public static String removeCardFromDeck4Short = "^deck rm-card -d (\\S+)( -s)? -c (\\S+)$";
    public static String removeCardFromDeck5 = "^deck rm-card( --side)? --deck (\\S+) --card (\\S+)$";
    public static String removeCardFromDeck5Short = "^deck rm-card( -s)? -d (\\S+) -c (\\S+)$";
    public static String removeCardFromDeck6 = "^deck rm-card( --side)? --card (\\S+) --deck (\\S+)$";
    public static String removeCardFromDeck6Short = "^deck rm-card( -s)? -c (\\S+) -d (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---SHOW DECK---*/

    public static String showAllDecks = "^deck show (--all|-a)$";
    public static String showOptionalDeck1 = "^deck show (--deck-name|-d) (\\S+)( --side|-s)?$";
    public static String showOptionalDeck2 = "^deck show( --side|-s)? (--deck-name|-d) (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---SHOW CARD,BUY CARD---*/

    public static String showAllUserCards = "^deck show (--cards|-c)$";
    public static String buyCard = "^shop buy (\\S+)$";
    public static String showCardsInShop = "^shop show (--all|-a)$";
    //----------------------------------------------------------------------------------------------

    /*---DUEL MULTI---*/

    public static String duelMultiplayer1 = "^duel --new --second-player (\\S+) --rounds (1|3)$";
    public static String duelMultiplayer1Short = "^duel -n -sp (\\S+) -r (1|3)$";
    public static String duelMultiplayer2 = "^duel --new --rounds (1|3) --second-player (\\S+)$";
    public static String duelMultiplayer2Short = "^duel -n -r (1|3) -sp (\\S+)$";
    public static String duelMultiplayer3 = "^duel --second-player (\\S+) --new --rounds (1|3)$";
    public static String duelMultiplayer3Short = "^duel -sp (\\S+) -n -r (1|3)$";
    public static String duelMultiplayer4 = "^duel --second-player (\\S+) --rounds (1|3) --new$";
    public static String duelMultiplayer4Short = "^duel -sp (\\S+) -r (1|3) -n$";
    public static String duelMultiplayer5 = "^duel --rounds (1|3) --second-player (\\S+) --new$";
    public static String duelMultiplayer5Short = "^duel -r (1|3) -sp (\\S+) -n$";
    public static String duelMultiplayer6 = "^duel --rounds (1|3) --new --second-player (\\S+)$";
    public static String duelMultiplayer6Short = "^duel -r (1|3) -n -sp (\\S+)$";
    //----------------------------------------------------------------------------------------------

    /*---DUEL SINGLE---*/

    //What is sort form of ai?:|
    public static String duelSingerPlayer1 = "^duel --new --ai --rounds (1|3)$";
    public static String duelSingerPlayer1Short = "^duel -n --ai -r (1|3)$";
    public static String duelSingerPlayer2 = "^duel --new --rounds (1|3) --ai$";
    public static String duelSingerPlayer2Short = "^duel -n -r (1|3) --ai$";
    public static String duelSingerPlayer3 = "^duel --ai --new --rounds (1|3)$";
    public static String duelSingerPlayer3Short = "^duel --ai -n -r (1|3)$";
    public static String duelSingerPlayer4 = "^duel --ai --rounds (1|3) --new$";
    public static String duelSingerPlayer4Short = "^duel --ai -r (1|3) -n$";
    public static String duelSingerPlayer5 = "^duel --rounds (1|3) --new --ai$";
    public static String duelSingerPlayer5Short = "^duel -r (1|3) -n --ai$";
    public static String duelSingerPlayer6 = "^duel --rounds (1|3) --ai --new$";
    public static String duelSingerPlayer6Short = "^duel -r (1|3) --ai -n$";
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
