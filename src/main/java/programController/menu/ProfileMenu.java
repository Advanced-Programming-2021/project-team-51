package main.java.programController.menu;

import main.java.models.User;
import main.java.programController.MenuEnum;
import main.java.programController.ProgramController;
import main.java.programController.Regex;
import main.java.programController.StatusEnum;

import java.util.regex.Matcher;

/*------------------CURRENT USER NOT FIXED-------------*/


public class ProfileMenu {
    private User currentUser;


    public void run(String command){
        Matcher matcher;
        if ((matcher = Regex.getMatcher(command,Regex.changePass1)).matches()){
            String oldPass = matcher.group(3);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changePass2)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(3);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changePass3)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changePass4)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(4);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changePass5)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changePass6)).matches()){
            String oldPass = matcher.group(4);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.changeProfile)).matches()){
            String newNickname = matcher.group(2);
            changeNickname(newNickname);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command,Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCurrentMenu)).matches()){
            System.out.println("Profile");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }
    private void changeNickname(String newNickname){
        if (User.isUserNameTaken(newNickname)){
            System.out.println("user with nickname" + newNickname + "already exists");
        }
        else{
            currentUser.setNickName(newNickname);
            System.out.println(StatusEnum.CHANGE_NICKNAME_SUCCESSFULLY);
        }
    }
    private void changePass(String oldPass,String newPass){
        if (!currentUser.getPassword().equals(oldPass)){
            System.out.println(StatusEnum.CURRENT_PASSWORD_INVALIDITY);
        }
        else if (currentUser.getPassword().equals(newPass)){
            System.out.println(StatusEnum.ENTER_A_NEW_PASSWORD);
        }
        else{
            currentUser.changePassword(newPass);
        }
    }
}

