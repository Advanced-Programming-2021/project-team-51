package view.menus;

import controller.menucontroller.LoginMenuController;
import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class LoginMenu {

    LoginMenuController loginMenuController;

    public void run(String command){

        loginMenuController = new LoginMenuController();

        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.loginUser1)).matches()){
            String username = matcher.group(2);
            String password = matcher.group(4);
            loginUSer(username,password);
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.loginUser2)).matches()){
            String username = matcher.group(4);
            String password = matcher.group(2);
            loginUSer(username,password);
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser1)).matches()){
            String username = matcher.group(2);
            String nickname = matcher.group(4);
            String password = matcher.group(6);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser2)).matches()){
            String username = matcher.group(2);
            String nickname = matcher.group(6);
            String password = matcher.group(4);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser3)).matches()){
            String username = matcher.group(4);
            String nickname = matcher.group(2);
            String password = matcher.group(6);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser4)).matches()){
            String username = matcher.group(6);
            String nickname = matcher.group(2);
            String password = matcher.group(4);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser5)).matches()){
            String username = matcher.group(6);
            String nickname = matcher.group(4);
            String password = matcher.group(2);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.createUser1)).matches()){
            String username = matcher.group(4);
            String nickname = matcher.group(6);
            String password = matcher.group(2);
            createUser(username,nickname,password);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.enterMenu)).matches()){
            //-----------------PROBLEM--------------------
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCurrentMenu)).matches()){
            System.out.println("Login Menu");
        }
        else if ((matcher = Regex.getMatcher(command,Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.LOGIN_MENU;
            System.exit(0);
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND.getStatus());
        }
    }

    private void loginUSer(String username, String password) {
        System.out.println(loginMenuController.loginUSer(username, password));
    }

    private void createUser(String username, String nickname, String password) {
        System.out.println(loginMenuController.createUser(username, nickname, password));
    }
}
