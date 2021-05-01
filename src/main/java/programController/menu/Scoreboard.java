package main.java.programController.menu;

import main.java.models.User;
import main.java.programController.MenuEnum;
import main.java.programController.ProgramController;
import main.java.programController.Regex;
import main.java.programController.StatusEnum;

import java.util.HashMap;
import java.util.regex.Matcher;

/*---------SHOW SCOREBOARD NOT FIXED YET------------*/
/*------------------CURRENT USER NOT FIXED-------------*/


public class Scoreboard {
    private User currentUser;
    private HashMap<String,Integer> players = new HashMap<>();
    //HashMap Must Be Set In Constructor--------------------------------------
    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.showScoreboard)).matches()){
            showScoreboard();
        }
        else if ((matcher = Regex.getMatcher(command,Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCurrentMenu)).matches()){
            System.out.println("Scoreboard");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }
    private void showScoreboard(){}
    private void setScoreboardFromFile(){};

}
