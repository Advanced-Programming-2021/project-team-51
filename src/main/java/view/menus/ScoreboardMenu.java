package view.menus;


import controller.menucontroller.LoginMenuController;
import view.MenuEnum;
import view.ProgramController;
import view.StatusEnum;
import models.User;
import view.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class ScoreboardMenu {

    private User currentUser;

    public ScoreboardMenu(){
        this.currentUser = LoginMenuController.currentUser;
    }

    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.showScoreboard)).matches()){
            showScoreboard();
        }
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches()){
           ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command, Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showCurrentMenu)).matches()){
            System.out.println("Scoreboard");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }
    private void showScoreboard(){
        ArrayList<User> sortedUsers = User.getSortedUsers();
        for (int i = 0, rank = 1; i < sortedUsers.size(); i++,rank++) {
            if (i > 0) {
                if (sortedUsers.get(i).getScore() == sortedUsers.get(i - 1).getScore())
                    rank--;
                else
                    rank = i + 1;
            }
            String nickName = sortedUsers.get(i).getNickName();
            int score = sortedUsers.get(i).getScore();
            System.out.println(rank+"-"+nickName+": "+score);
            i++;
        }
    }
}
