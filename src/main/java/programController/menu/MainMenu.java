package main.java.programController.menu;


import main.java.models.User;
import main.java.programController.MenuEnum;
import main.java.programController.ProgramController;
import main.java.programController.Regex;
import main.java.programController.StatusEnum;



import java.util.regex.Matcher;
public class MainMenu {
    private User currentUser;
    public MainMenu(){
        this.currentUser = LoginMenu.currentUser;
    }
    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.enterMenu)).matches()){
            String menuToEnter = matcher.group(1);
            if (menuToEnter.equals("Duel")){
               ProgramController.currentMenu = MenuEnum.DUEL;
            }
            else if (menuToEnter.equals("Deck")){
                ProgramController.currentMenu = MenuEnum.DECK_MENU;
            }
            else if (menuToEnter.equals("Scoreboard")){
                ProgramController.currentMenu = MenuEnum.SCOREBOARD;
            }
            else if (menuToEnter.equals("Profile")){
                ProgramController.currentMenu = MenuEnum.PROFILE_MENU;
            }
            else if (menuToEnter.equals("Shop")){
                ProgramController.currentMenu = MenuEnum.SHOP_MENU;
            }
            else if (menuToEnter.equals("Import/Export")){
                ProgramController.currentMenu = MenuEnum.IMPORT_EXPORT;
            }
        }
        else if ((matcher = Regex.getMatcher(command,Regex.exitMenu)).matches()){
           ProgramController.currentMenu = MenuEnum.LOGIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCurrentMenu)).matches()){
            System.out.println("Main Menu");
        }
        else if((matcher = Regex.getMatcher(command,Regex.userLogout)).matches()){
            LoginMenu.currentUser = null;
            LoginMenu.isLoggedOn = false;
            ProgramController.currentMenu = MenuEnum.LOGIN_MENU;
            System.out.println(StatusEnum.USER_LOGOUT_SUCCESSFULLY);
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }
}
