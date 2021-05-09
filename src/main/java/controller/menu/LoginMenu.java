package controller.menu;


import controller.MenuEnum;
import controller.ProgramController;
import controller.StatusEnum;
import models.User;
import view.Regex;


import java.util.regex.Matcher;


//-----------------------------------PLEASE LOGIN FIRST NOT FIXED-------------------

public class LoginMenu {
    public static User currentUser;
    public static boolean isLoggedOn = false;


    public void run(String command){
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
            System.out.println(StatusEnum.INVALID_COMMAND);
        }
    }

    private boolean deosUserExist(String username){
        for (User user: User.allUsers) {
            if (user.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }
    private boolean isPasswoerCorrect(String username , String password){
        if (User.getUserByUserName(username).getPassword().equals(password)){
            return true;
        }
        return false;
    }
    private void loginUSer(String username , String password){
        if (!deosUserExist(username)){
            System.out.println(StatusEnum.USERNAME_AND_PASSWORD_MISMATCH);
        }
        else if (!isPasswoerCorrect(username,password)){
            System.out.println(StatusEnum.USERNAME_AND_PASSWORD_MISMATCH);
        }
        else{
            currentUser = User.getUserByUserName(username);
            isLoggedOn = true;
            System.out.println(StatusEnum.USER_LOGIN_SUCCESSFULLY);
        }
    }
    private void createUser(String username, String nickname , String password){
        if (User.isUserNameTaken(username)){
            System.out.println("user with username" + username + "already exists");
        }
        else if (User.isNickNameTaken(nickname)){
            System.out.println("user with nickname" + nickname + "already exists");
        }
        else{
            currentUser = new User(username,nickname,password);
            System.out.println(StatusEnum.USER_LOGIN_SUCCESSFULLY);
        }
    }



}
