package view;

import view.MenuEnum;
import view.menus.*;

import java.util.Scanner;

public class ProgramController {
    public static MenuEnum currentMenu = MenuEnum.LOGIN_MENU;
    public static Scanner scanner = new Scanner(System.in);
    LoginMenu loginMenu ;
    MainMenu mainMenu ;
    ShopMenu shopMenu;
    ScoreboardMenu scoreboardMenu ;
    DeckMenu deckMenu;
    CheatMenu cheatMenu;
    ImportExportMenu importExportMenu;
    ProfileMenu profileMenu;
    DuelMenu duelMenu;


    public void run() throws CloneNotSupportedException {
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
                scoreboardMenu.run(command);
            }
            else if (currentMenu == MenuEnum.DECK_MENU){
                deckMenu.run(command);
            }
            else if (currentMenu == MenuEnum.CHEAT_MENU){
                cheatMenu.run(command);
            }
            else if(currentMenu == MenuEnum.IMPORT_EXPORT){
                importExportMenu.run(command);
            }
            else if (currentMenu == MenuEnum.PROFILE_MENU){
                profileMenu.run(command);
            }
            else if (currentMenu == MenuEnum.DUEL){
                duelMenu.run(command);
            }

        }
    }

}
