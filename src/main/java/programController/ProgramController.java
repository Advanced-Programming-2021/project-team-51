package main.java.programController;



import main.java.programController.menu.*;
import programController.menu.DeckMenu;

import java.util.Scanner;

public class ProgramController {
    public static MenuEnum currentMenu = MenuEnum.LOGIN_MENU;
    public static Scanner scanner = new Scanner(System.in);
    LoginMenu loginMenu ;
    MainMenu mainMenu ;
    ShopMenu shopMenu;
    Scoreboard scoreboard ;
    DeckMenu deckMenu;
    CheatMenu cheatMenu;
    ImportExport importexport;
    ProfileMenu profileMenu;
    Duel duel;


    public void run() {
        while(currentMenu!= main.java.programController.MenuEnum.EXIT){
            String command = scanner.nextLine();
            if (currentMenu == main.java.programController.MenuEnum.LOGIN_MENU) {
                loginMenu.run(command);
            }
            else if(currentMenu == main.java.programController.MenuEnum.MAIN_MENU){
                mainMenu.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.SHOP_MENU){
                shopMenu.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.SCOREBOARD){
                scoreboard.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.DECK_MENU){
                deckMenu.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.CHEAT_MENU){
                cheatMenu.run(command);
            }
            else if(currentMenu == main.java.programController.MenuEnum.IMPORT_EXPORT){
                importexport.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.PROFILE_MENU){
                profileMenu.run(command);
            }
            else if (currentMenu == main.java.programController.MenuEnum.DUEL){
                duel.run(command);
            }

        }
    }

}
