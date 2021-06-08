package view;

import view.menus.*;

import java.io.IOException;
import java.util.Scanner;

public class ProgramController {
    public static MenuEnum currentMenu = MenuEnum.LOGIN_MENU;
    public static Scanner scanner = new Scanner(System.in);
    LoginMenu loginMenu = new LoginMenu();
    MainMenu mainMenu ;
    ShopMenu shopMenu;
    ScoreboardMenu scoreboardMenu ;
    DeckMenu deckMenu;
    ImportExportMenu importExportMenu;
    ProfileMenu profileMenu;
    DuelMenu duelMenu;


    public void run() throws CloneNotSupportedException, IOException {
        while(currentMenu!= MenuEnum.EXIT){
            String command = scanner.nextLine();
            if (currentMenu == MenuEnum.LOGIN_MENU) {
                loginMenu.run(command);
            }
            else if(currentMenu == MenuEnum.MAIN_MENU){
                mainMenu = new MainMenu();
                mainMenu.run(command);
            }
            else if (currentMenu == MenuEnum.SHOP_MENU){
                shopMenu = new ShopMenu();
                shopMenu.run(command);
            }
            else if (currentMenu == MenuEnum.SCOREBOARD){
                scoreboardMenu = new ScoreboardMenu();
                scoreboardMenu.run(command);
            }
            else if (currentMenu == MenuEnum.DECK_MENU){
                deckMenu = new DeckMenu();
                deckMenu.run(command);
            }
            else if(currentMenu == MenuEnum.IMPORT_EXPORT){
                importExportMenu = new ImportExportMenu();
                importExportMenu.run(command);
            }
            else if (currentMenu == MenuEnum.PROFILE_MENU){
                profileMenu = new ProfileMenu();
                profileMenu.run(command);
            }
            else if (currentMenu == MenuEnum.DUEL){
                duelMenu = new DuelMenu();
                duelMenu.run(command);
            }

        }
    }

}
