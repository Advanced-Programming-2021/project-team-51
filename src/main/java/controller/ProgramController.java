package controller;

import controller.menu.*;

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
        while(currentMenu!= MenuEnum.EXIT){
            String command = scanner.nextLine();
            if (currentMenu == MenuEnum.LOGIN_MENU) {
                loginMenu.run(command);
            }
            else if(currentMenu == MenuEnum.MAIN_MENU){
                mainMenu.run(command);
            }
            else if (currentMenu == MenuEnum.SHOP_MENU){
                shopMenu.run(command);
            }
            else if (currentMenu == MenuEnum.SCOREBOARD){
                scoreboard.run(command);
            }
            else if (currentMenu == MenuEnum.DECK_MENU){
                deckMenu.run(command);
            }
            else if (currentMenu == MenuEnum.CHEAT_MENU){
                cheatMenu.run(command);
            }
            else if(currentMenu == MenuEnum.IMPORT_EXPORT){
                importexport.run(command);
            }
            else if (currentMenu == MenuEnum.PROFILE_MENU){
                profileMenu.run(command);
            }
            else if (currentMenu == MenuEnum.DUEL){
                duel.run(command);
            }

        }
    }

}
