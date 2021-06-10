package view.menus;

import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class CheatMenu {
    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_MONEY)).matches()){
            Integer amount = Integer.parseInt(matcher.group(2));
            increaseMoney(amount);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_INCREASE_LP)).matches()){
            Integer amount = Integer.parseInt(matcher.group(2));
            increaseLP(amount);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_1)).matches()){
            String cardName = matcher.group(2);
            selectMoreCards(cardName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SELECT_MORE_CARDS_2)).matches()){
            String cardName = matcher.group(3);
            selectMoreCards(cardName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.CHEAT_SET_WINNER)).matches()){
            String nickname = matcher.group(1);
            setWinner(nickname);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.EXIT_MENU)).matches()){
            ProgramController.currentMenu = MenuEnum.DUEL_MENU;
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }

    private void increaseMoney(Integer amount) {
    }

    private void increaseLP(Integer amount) {
    }

    private void selectMoreCards(String cardName) {
    }

    private void setWinner(String nickname) {
    }
}
