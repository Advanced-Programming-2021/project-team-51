package controller.GUI;

import controller.menucontroller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;

import java.io.IOException;

public class MainMenuControllerGUI {


    public static ProfileMenuController profileMenuController = new ProfileMenuController(LoginMenuController.currentUser);
    public static ShopMenuController shopMenuController = new ShopMenuController(LoginMenuController.currentUser);
    public static ImportExportController importExportController = new ImportExportController();
    public static DeckMenuController deckMenuController = new DeckMenuController(LoginMenuController.currentUser);
    public static DuelMenuController duelMenuController = new DuelMenuController();

    public void enterProfileMenu(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/profile_menu.fxml",actionEvent);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        LoginMenuController.currentUser = null;
        LoginMenuController.isLoggedOn = false;
        new SceneController().switchScene("/fxml/start.fxml",actionEvent);
    }

    public void enterShopMenu(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/shop_menu.fxml", actionEvent);
    }

    public void enterImportMenu(ActionEvent actionEvent) {
        //Todo
    }

    public void enterDeckMenu(ActionEvent actionEvent) {
        //Todo
    }

    public void exit(MouseEvent event) {
        LoginMenuController.currentUser = null;
        LoginMenuController.isLoggedOn = false;
        System.exit(0);
    }


    public void enterDuelMenu(ActionEvent actionEvent) {
        //Todo
    }
}
