package view.menus;

import controller.menucontroller.LoginMenuController;
import controller.menucontroller.ShopMenuController;
import models.User;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;
import view.MenuEnum;
import view.ProgramController;
import view.Regex;
import view.StatusEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class ShopMenu {

    User currentUser = LoginMenuController.currentUser;
    ShopMenuController shopMenuController;

    public void run(String command) throws IOException {

        shopMenuController = new ShopMenuController(currentUser);

        Matcher matcher;
        if ((matcher = Regex.getMatcher(command, Regex.buyCard)).matches()){
            String cardName = matcher.group(1);
                buyCard(cardName);
        }
        else if ((matcher = Regex.getMatcher(command,Regex.showCardsInShop)).matches()){
            showCardsInShop();
        }
        else if ((matcher = Regex.getMatcher(command, Regex.exitMenu)).matches()){
            ProgramController.currentMenu = MenuEnum.MAIN_MENU;
        }
        else if ((matcher = Regex.getMatcher(command, Regex.enterMenu)).matches()){
            System.out.println(StatusEnum.MENU_NAVIGATION_NOT_POSSIBLE.getStatus());
        }
        else if ((matcher = Regex.getMatcher(command, Regex.showCurrentMenu)).matches()){
            System.out.println("Shop");
        }
        else{
            System.out.println(StatusEnum.INVALID_COMMAND.getStatus());
        }

    }

    private void showCardsInShop() throws IOException {
        ArrayList<MonsterCard> allMonsters = MonsterCard.getAllMonsterCardsToShow();
        ArrayList<SpellTrapCard> allSpellTraps = SpellTrapCard.getAllSpellTrapCardsToShow();
        allMonsters.sort(Comparator.comparing(MonsterCard::getName));
        allSpellTraps.sort(Comparator.comparing(SpellTrapCard::getName));
        for (MonsterCard monster : allMonsters) {
            System.out.println(monster.getName() + " : " + monster.getPrice());
        }
        for (SpellTrapCard spellTrap : allSpellTraps) {
            System.out.println(spellTrap.getName() + " : " + spellTrap.getPrice());
        }
    }

    private void buyCard(String cardName) throws IOException {
        System.out.println(shopMenuController.buyCard(cardName));
    }
}
