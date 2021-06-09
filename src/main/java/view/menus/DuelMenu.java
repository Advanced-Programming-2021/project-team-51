package view.menus;

import view.MenuEnum;
import view.ProgramController;
import view.Regex;

public class DuelMenu {

    private boolean isCommandValid;

    public void run(String command) {
        isCommandValid = false;
        startTwoPlayerGame(command);
        startSinglePlayerGame(command);
        if (!isCommandValid)
            System.out.println("invalid command!");
    }

    private void startTwoPlayerGame(String command) {
        String[] regexes = {Regex.DUEL_MULTIPLAYER_1, Regex.DUEL_MULTIPLAYER_2, Regex.DUEL_MULTIPLAYER_3,
                Regex.DUEL_MULTIPLAYER_4, Regex.DUEL_MULTIPLAYER_5, Regex.DUEL_MULTIPLAYER_6};
        for (int i = 0; i < 6; i++) {
            if (Regex.getMatcher(command, regexes[i]).find())
                break;
            if (i == regexes.length - 1)
                return;
        }
        isCommandValid = true;
        //System.out.println(); TODO call function from controller
        ProgramController.currentMenu = MenuEnum.DUEL_VIEW;
    }

    private void startSinglePlayerGame(String command) {
        String[] regexes = {Regex.DUEL_SINGLE_PLAYER_1, Regex.DUEL_SINGLE_PLAYER_2, Regex.DUEL_SINGLE_PLAYER_3, Regex.DUEL_SINGLE_PLAYER_4
                , Regex.DUEL_SINGLE_PLAYER_5, Regex.DUEL_MULTIPLAYER_6, Regex.DUEL_SINGLE_PLAYER_7, Regex.DUEL_SINGLE_PLAYER_8
                , Regex.DUEL_SINGLE_PLAYER_9, Regex.DUEL_SINGLE_PLAYER_10, Regex.DUEL_SINGLE_PLAYER_11, Regex.DUEL_SINGLE_PLAYER_12
                , Regex.DUEL_SINGLE_PLAYER_13, Regex.DUEL_SINGLE_PLAYER_14, Regex.DUEL_SINGLE_PLAYER_15, Regex.DUEL_SINGLE_PLAYER_16
                , Regex.DUEL_SINGLE_PLAYER_17, Regex.DUEL_SINGLE_PLAYER_18, Regex.DUEL_SINGLE_PLAYER_19, Regex.DUEL_SINGLE_PLAYER_20
                , Regex.DUEL_SINGLE_PLAYER_21, Regex.DUEL_SINGLE_PLAYER_22, Regex.DUEL_SINGLE_PLAYER_23, Regex.DUEL_SINGLE_PLAYER_24};
        for (int i = 0; i < 24; i++) {
            if (Regex.getMatcher(command, regexes[i]).find())
                break;
            if (i == regexes.length - 1)
                return;
        }
        isCommandValid = true;
        //System.out.println(); TODO call function from controller
        ProgramController.currentMenu = MenuEnum.DUEL_VIEW;
    }
}