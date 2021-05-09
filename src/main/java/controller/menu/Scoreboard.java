package controller.menu;





import controller.MenuEnum;
import controller.ProgramController;
import controller.StatusEnum;
import models.User;
import view.Regex;

import java.util.regex.Matcher;





public class Scoreboard {
    private User currentUser;
    public Scoreboard(User currentUser){
        this.currentUser = currentUser;
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
        int i = 1;
        for (User user: User.getSortedUsers()) {
            String nickName = user.getNickName();
            int score = user.getScore();
            System.out.println(i+"-"+nickName+": "+score);
            i++;
        }
    }
}
