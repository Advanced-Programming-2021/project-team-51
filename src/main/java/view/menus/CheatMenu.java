package view.menus;

import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class CheatMenu {
    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.cheatIncreaseMoney)).matches()){
            Integer amount = Integer.parseInt(matcher.group(2));
            increaseMoney(amount);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.cheatIncreaseLP)).matches()){
            Integer amount = Integer.parseInt(matcher.group(2));
            increaseLP(amount);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.cheatSelectMoreCards1)).matches()){
            String cardName = matcher.group(2);
            selectMoreCards(cardName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.cheatSelectMoreCards2)).matches()){
            String cardName = matcher.group(3);
            selectMoreCards(cardName);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.cheatSetWinner)).matches()){
            String nickname = matcher.group(1);
            setWinner(nickname);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.DUEL;
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
