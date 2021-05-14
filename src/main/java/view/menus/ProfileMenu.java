package view.menus;

import controller.menucontroller.ProfileMenuController;
import models.User;
import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.util.regex.Matcher;

public class ProfileMenu {

    User currentUser;
    ProfileMenuController profileMenuController;

    public void run(String command){

        profileMenuController = new ProfileMenuController(currentUser);

        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.changePass1)).matches()){
            String oldPass = matcher.group(3);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.changePass2)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(3);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.changePass3)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.changePass4)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(4);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.changePass5)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.changePass6)).matches()){
            String oldPass = matcher.group(4);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changeProfile)).matches()){
            String newNickname = matcher.group(2);
            changeNickname(newNickname);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command, Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showCurrentMenu)).matches()){
            System.out.println("Profile");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }

    private void changePass(String oldPass, String newPass) {
        System.out.println(profileMenuController.changePass(oldPass, newPass));
    }

    private void changeNickname(String newNickname) {
        System.out.println(profileMenuController.changeNickname(newNickname));
    }
}
