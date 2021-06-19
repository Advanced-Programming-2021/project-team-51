package view.menus;

import controller.menucontroller.ImportExportController;
import controller.menucontroller.LoginMenuController;
import models.User;
import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.io.IOException;
import java.util.regex.Matcher;

public class ImportExportMenu {
    User currentUser;

    public ImportExportMenu() {
        currentUser = LoginMenuController.currentUser;
    }

    public void run(String command) throws IOException {

        ImportExportController importExportController = new ImportExportController();

        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.IMPORT_CARD)).matches())
            System.out.println(importExportController.importCard(matcher.group(1)));
        else if ((matcher = Regex.getMatcher(command, Regex.EXPORT_CARD)).matches())
            System.out.println(importExportController.exportCard(matcher.group(1)));
        else if (Regex.getMatcher(command, Regex.EXIT_MENU).matches())
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        else if (Regex.getMatcher(command, Regex.ENTER_MENU).matches())
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        else if (Regex.getMatcher(command, Regex.SHOW_CURRENT_MENU).matches())
            System.out.println("Import/Export");
        else
            System.out.println(StatusEnum.INVALID_COMMAND);
    }
}
