package programController.menu;


/*------------------CURRENT USER NOT FIXED-------------*/


import models.User;
import programController.ProgramController;

import java.util.regex.Matcher;

public class ProfileMenu {
    private User currentUser;


    public void run(String command){
        Matcher matcher;
        if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass1)).matches()){
            String oldPass = matcher.group(3);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass2)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(3);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass3)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(5);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass4)).matches()){
            String oldPass = matcher.group(2);
            String newPass = matcher.group(4);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass5)).matches()){
            String oldPass = matcher.group(5);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changePass6)).matches()){
            String oldPass = matcher.group(4);
            String newPass = matcher.group(2);
            changePass(oldPass,newPass);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.changeProfile)).matches()){
            String newNickname = matcher.group(2);
            changeNickname(newNickname);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.exitMenu)).matches()){
            ProgramController.currentMenu = main.java.programController.MenuEnum.MAIN_MENU;
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.enterMenu)).matches()){
            System.out.println(main.java.programController.StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        }
        else if ((matcher = main.java.programController.Regex.getMatcher(command, main.java.programController.Regex.showCurrentMenu)).matches()){
            System.out.println("Profile");
        }
        else{
            System.out.println(main.java.programController.StatusEnum.INVALID_COMMAND);
        }
    }
    private void changeNickname(String newNickname){
        if (User.isNickNameTaken(newNickname)){
            System.out.println("user with nickname" + newNickname + "already exists");
        }
        else{
            currentUser.setNickName(newNickname);
            System.out.println(main.java.programController.StatusEnum.CHANGE_NICKNAME_SUCCESSFULLY);
        }
    }
    private void changePass(String oldPass,String newPass){
        if (!currentUser.getPassword().equals(oldPass)){
            System.out.println(main.java.programController.StatusEnum.CURRENT_PASSWORD_INVALIDITY);
        }
        else if (currentUser.getPassword().equals(newPass)){
            System.out.println(main.java.programController.StatusEnum.ENTER_A_NEW_PASSWORD);
        }
        else{
            currentUser.changePassword(newPass);
        }
    }
}

