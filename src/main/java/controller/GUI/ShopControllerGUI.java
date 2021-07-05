package controller.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopControllerGUI {

    SceneController sceneController = new SceneController();

    public static ShopControllerGUI instance;

    public ShopControllerGUI() {
        if (instance == null)
            instance = this;
    }

    public static ShopControllerGUI getInstance() {
        return instance;
    }

    @FXML
    private AnchorPane anchor;

    public void initialize() {
        try {
            ArrayList<MonsterCard> monsters = MonsterCard.getAllMonsterCardsToShow();
            ArrayList<SpellTrapCard> spells = SpellTrapCard.getAllSpellTrapCardsToShow();
            int startX = 100;
            int startY = 100;
            for (MonsterCard monster: monsters) {
                monster.getImage().setX(startX);
                monster.getImage().setY(startY);
                monster.getImage().setFitHeight(120);
                monster.getImage().setFitWidth(80);
                anchor.getChildren().add(monster.getImage());
                startX += 100;
                if (startX == 500) {
                    startX = 100;
                    startY += 150;
                }
            }
            for (SpellTrapCard spellTrapCard: spells) {
                spellTrapCard.getImage().setX(startX);
                spellTrapCard.getImage().setY(startY);
                spellTrapCard.getImage().setFitHeight(120);
                spellTrapCard.getImage().setFitWidth(80);
                anchor.getChildren().add(spellTrapCard.getImage());
                startX += 100;
                if (startX == 500) {
                    startX = 100;
                    startY += 150;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }
}
