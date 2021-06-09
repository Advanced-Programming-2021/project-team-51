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
        if ((matcher = Regex.getMatcher(command, Regex.importCard)).matches())
            System.out.println(importExportController.importCard(matcher.group(1)));
        else if ((matcher = Regex.getMatcher(command, Regex.exportCard)).matches())
            System.out.println(importExportController.exportCard(matcher.group(1)));
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches())
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        else if ((matcher = Regex.getMatcher(command, Regex.enterMenu)).matches())
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE);
        else if ((matcher = Regex.getMatcher(command, Regex.showCurrentMenu)).matches())
            System.out.println("Import/Export");
        else
            System.out.println(StatusEnum.INVALID_COMMAND);
    }
}
