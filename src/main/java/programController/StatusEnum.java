package main.java.programController;


/*-------------------REMEMBER TO CORRECT SKIPS----------------------*/


public enum StatusEnum {
    INVALID_COMMAND("invalid command"),
    PLEASE_LOGIN_FIRST("please login first"),
    MENU_NAVIGATION_NOT_POSSIBLE("menu navigation is not possible"),
    USER_CREATE_SUCCESSFULLY("user created successfully!"),
    ALREADY_EXISTENCE_OF_A_USERNAME(""),//SKIP--------------------------
    ALREADY_EXISTENCE_OF_A_NICKNAME(""),//SKIP--------------------------
    USER_LOGIN_SUCCESSFULLY("user logged in successfully!"),
    USERNAME_AND_PASSWORD_MISMATCH("Username and password didn’t match!"),
    USER_LOGOUT_SUCCESSFULLY("user logged out successfully!"),
    CHANGE_NICKNAME_SUCCESSFULLY("nickname changed successfully!"),
    CHANGE_PASSWORD_SUCCESSFULLY("password changed successfully!"),
    CURRENT_PASSWORD_INVALIDITY("current password is invalid"),
    ENTER_A_NEW_PASSWORD("please enter a new password"),
    DECK_CREATE_SUCCESSFULLY("deck created successfully!"),
    ALREADY_EXISTENCE_OF_A_DECK(""),//SKIP--------------------------
    NO_EXISTENCE_OF_A_DECK(""),//SKIP--------------------------
    DECK_DELETED_SUCCESSFULLY("deck deleted successfully"),
    DECK_ACTIVATED_SUCCESSFULLY("deck activated successfully"),
    CARD_ADDED_TO_DECK_SUCCESSFULLY("card added to deck successfully"),
    NO_EXISTENCE_OF_A_CARD_IN_STORAGE(""),//SKIP--------------------------
    NO_EXISTENCE_OF_A_CARD_IN_MAIN_OR_SIDE_DECK(""),//SKIP--------------------------
    DECK_IS_FULL(""),//SKIP--------------------------
    EXISTENCE_OF_THREE_CARD_WITH_THE_SAME_NAME(""),//SKIP--------------------------
    CARD_REMOVED_SUCCESSFULLY("card removed form deck successfully"),
    INVALID_CARD_IN_SHOP("there is no card with this name"),
    DESELECT_SELECTED_CARD("card deselected"),
    NOT_ENOUGH_MONEY("not enough money"),
    NO_EXISTENCE_OF_PLAYER2("there is no player with this username"),
    NO_ACTIVE_DECK(""),//SKIP--------------------------
    DECK_INVALID(""),//SKIP--------------------------
    ROUNDS_NOT_SUPPORTED("number of rounds is not supported"),
    INVALID_SELECTION("invalid selection"),
    CARD_SELECTED("card selected"),
    NO_CARD_FOUND_IN_POSITION("no card found in the given position"),
    NO_CARD_SELECTED_YET("no card is selected yet"),
    CARD_DESELECTED("card deselected"),
    CURRENT_PHASE(""),//SKIP--------------------------
    DRAW_PHASE(""),//SKIP--------------------------
    NEXT_PLAYER_TURN(""),//SKIP--------------------------
    CANT_SUMMON_CARD("you can’t summon this card"),
    ACTION_NOT_ALLOWED_IN_PHASE("action not allowed in this phase"),
    MONSTER_CARD_ZONE_FULL("monster card zone is full"),
    ALREADY_SUMMON_ON_TURN("you already summoned on this turn"),
    ALREADY_SET_ON_TURN("you already set on this turn"),
    SUMMON_SUCCESSFULLY("summoned successfully"),
    NOT_ENOUGH_CARDS_FOR_TRIBUTE("there are not enough cards for tribute"),
    NO_MONSTERS_ON_ADDRESS("there no monsters one this address"),
    NO_MONSTERS_ON_AT_LEAST_TWO_ADDRESSES("there no monsters on one of this addresses"),
    NO_CARD_IS_SELECTED_YET("no card is selected yet"),
    CANT_SET_THIS_CARD("you can’t set this card"),
    CANT_DO_THIS_ACTION_IN_THIS_PHASE("you can’t do this action in this phase"),
    SET_SUCCESSFULLY("set successfully"),
    YOU_CANT_CHANGE_THIS_CARD_POSITION("you can’t change this card position"),
    CARD_ALREADY_IN_WANTED_POSITION("this card is already in the wanted position"),
    ALREADY_CHANGED_THIS_CARD_POSITION_IN_TURN("you already changed this card position in this turn"),
    MONSTER_CARD_POSITION_CHANGED_SUCCESSFULLY("monster card position changed successfully"),
    CANT_FLIP_SUMMON_THIS_CARD("you can’t flip summon this card"),
    FLIP_SUMMONED_SUCCESSFULLY("flip summoned successfully"),
    CANT_ATTACK_WITH_CARD("you can’t attack with this card"),
    CARD_ALREADY_ATTACKED("this card already attacked"),
    NO_CARD_TO_ATTACK_HERE("there is no card to attack here"),
    OPPONENT_RECEIVE_DAMAGE_OO(""),//SKIP--------------------------
    YOU_RECEIVED_DAMAGE_OO(""),//SKIP--------------------------
    BOTH_RECEIVED_DAMAGE_OO("both you and your opponent monster cards are destroyed and no one receives damage"),
    DEFENSE_POSITION_DESTROYED_DO("the defense position monster is destroyed"),
    NO_CARD_DESTROYED_EQUAL_DEFENSES_DO("no card is destroyed"),
    NO_CARD_DESTROYED_NON_EQUAL_DEFENSES_DO(""),//SKIP--------------------------
    DEFENSE_POSITION_DESTROYED_DH("the defense position monster is destroyed"),
    NO_CARD_DESTROYED_EQUAL_DEFENSES_DH("no card is destroyed"),
    NO_CARD_DESTROYED_NON_EQUAL_DEFENSES_DH(""),//SKIP--------------------------
    ACTIVATE_NONE_SPELL_CARD("activate effect is only for spell cards."),
    CANT_ACTIVATE_EFFECT("you can’t activate an effect on this turn"),
    CARD_ALREADY_ACTIVATED("you have already activated this card"),
    SPELL_CARD_ZONE_FULL("spell card zone is full"),
    PREPARATION_OF_SPELL_NOT_DONE("preparations of this spell are not done yet"),
    SPELL_ACTIVATED("spell activated"),
    PLAYER_TURN(""),//SKIP--------------------------
    DO_YOU_WANT_ACTIVATE("do you want to activate your trap and spell?"),
    NOT_YOUR_TURN_TO_PLAY_MOVES("it’s not your turn to play this kind of moves"),
    SPELL_OR_TRAP_ACTIVATED(""),//SKIP--------------------------
    NO_WAY_TO_RITUAL("there is no way you could ritual summon a monster"),
    SHOULD_RITUAL_NOW("you should ritual summon right now"),
    MONSTERS_NO_MATCH_WITH_RITUAL_MONSTERS("selected monsters levels don’t match with ritual monster"),
    NO_WAY_TO_SPECIAL_SUMMON("there is no way you could special summon a monster"),
    SHOULD_SPECIAL_SUMMON_NOW("you should special summon right now"),
    GRAVE_YARD_EMPTY("graveyard empty"),
    CARD_NOT_VISIBLE("card is not visible"),
    WIN_THE_GAME(""),//SKIP--------------------------
    WIN_THE_MATCH("");//SKIP--------------------------


   private String status;

    StatusEnum(String status){
        setStatus(status);
    }



    public String getStatus(){
        return  status;
    }
    public void setStatus(String status){
        this.status = status;
    }

}
