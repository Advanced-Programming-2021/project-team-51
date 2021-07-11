package controller.GUI;


import controller.duel.*;
import controller.duel.singlePlayer.GameController;
import controller.menucontroller.CheatMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import models.cards.Card;
import models.cards.monsters.MonsterCard;
import models.cards.spelltrap.SpellTrapCard;
import view.menus.DuelMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DuelViewSceneController implements Initializable {

    private static final Image unknownCardImage = new Image("./image/Unknown.jpg");

    public ImageView myMonster5;
    public ImageView myMonster3;
    public ImageView myMonster1;
    public ImageView myMonster2;
    public ImageView myMonster4;
    public ImageView mySpell5;
    public ImageView mySpell3;
    public ImageView mySpell1;
    public ImageView mySpell2;
    public ImageView mySpell4;
    public ImageView rivalMonster4;
    public ImageView rivalMonster1;
    public ImageView rivalMonster2;
    public ImageView rivalMonster3;
    public ImageView rivalMonster5;
    public ImageView rivalSpell5;
    public ImageView rivalSpell3;
    public ImageView rivalSpell1;
    public ImageView rivalSpell2;
    public ImageView rivalSpell4;
    public ImageView myGraveyard;
    public ImageView rivalGraveyard;
    public ImageView rivalDeck;
    public ImageView selectedCard;
    public ProgressBar myLP;
    public ProgressBar rivalLP;
    public Label rivalName;
    public Label myName;
    public ImageView backGround;
    public ImageView myHand1;
    public ImageView myHand2;
    public ImageView myHand3;
    public ImageView myHand4;
    public ImageView myHand5;
    public ImageView rivalHand4;
    public ImageView rivalHand3;
    public ImageView rivalHand2;
    public ImageView rivalHand1;
    public ImageView myDeck;
    public Label rivalUserName;
    public Label myUserName;
    public Label rivalLPLabel;
    public Label myLPLabel1;
    public ImageView myAvatar;
    public ImageView rivalAvatar;
    public AnchorPane pane;
    public ImageView myHand6;
    public ImageView myHand7;
    public ImageView rivalHand9;
    public ImageView rivalHand5;
    public ImageView rivalHand8;
    public ImageView myHand8;
    public ImageView myHand9;
    public ImageView rivalHand7;
    public ImageView myHand10;
    public ImageView rivalHand6;
    public ImageView rivalHand10;
    public ImageView rivalFieldSpell;
    public ImageView myFieldSpell;

    public static SelectionController selectionController = new SelectionController();
    public static SummonController summonController = new SummonController();
    public static SettingController settingController = new SettingController();
    public static PhaseController phaseController = new PhaseController();
    public static AttackController attackController = new AttackController();
    public static ActivationController activationController = new ActivationController();

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void handleAttackOnMonster4(DragEvent dragEvent) {
        attackController.attackMonsterToMonster("4");
        setCards();
        setLifePoint();
    }

    public void handleAttackOnMonster2(DragEvent dragEvent) {
        attackController.attackMonsterToMonster("2");
        setCards();
        setLifePoint();
    }

    public void handleAttackOnMonster1(DragEvent dragEvent) {
        attackController.attackMonsterToMonster("1");
        setCards();
        setLifePoint();
    }

    public void handleAttackOnMonster3(DragEvent dragEvent) {
        attackController.attackMonsterToMonster("3");
        setCards();
        setLifePoint();
    }

    public void handleAttackOnMonster5(DragEvent dragEvent) {
        attackController.attackMonsterToMonster("5");
        setCards();
        setLifePoint();
    }

    public void handleDragMonster5(MouseEvent mouseEvent) {
        Dragboard dragboard = myMonster5.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboard = new ClipboardContent();
        clipboard.putImage(myMonster5.getImage());
        dragboard.setContent(clipboard);
        mouseEvent.consume();
    }

    public void handleDragMonster1(MouseEvent mouseEvent) {
      Dragboard dragboard = myMonster1.startDragAndDrop(TransferMode.ANY);
      ClipboardContent clipboard = new ClipboardContent();
      clipboard.putImage(myMonster1.getImage());
      dragboard.setContent(clipboard);
      mouseEvent.consume();
    }

    public void handleDragMonster3(MouseEvent mouseEvent) {
        Dragboard dragboard = myMonster3.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboard = new ClipboardContent();
        clipboard.putImage(myMonster3.getImage());
        dragboard.setContent(clipboard);
        mouseEvent.consume();
    }

    public void handleDragMonster2(MouseEvent mouseEvent) {
        Dragboard dragboard = myMonster2.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboard = new ClipboardContent();
        clipboard.putImage(myMonster2.getImage());
        dragboard.setContent(clipboard);
        mouseEvent.consume();
    }

    public void handleDragMonster4(MouseEvent mouseEvent) {
        Dragboard dragboard = myMonster4.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboard = new ClipboardContent();
        clipboard.putImage(myMonster4.getImage());
        dragboard.setContent(clipboard);
        mouseEvent.consume();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myLP.setProgress((double) GameController.player.getPlayerBoard().getLifePoints() / 8000.0);
        rivalLP.setProgress((double) GameController.bot.getBoard().getLifePoints() / 8000.0);
        setSelections();
    }

    public void setSelections() {
        setSelectMyMonster(myMonster1, "1");
        setSelectMyMonster(myMonster2, "2");
        setSelectMyMonster(myMonster3, "3");
        setSelectMyMonster(myMonster4, "4");
        setSelectMyMonster(myMonster5, "5");
        setSelectRivalMonster(rivalMonster1, "1");
        setSelectRivalMonster(rivalMonster2, "2");
        setSelectRivalMonster(rivalMonster3, "3");
        setSelectRivalMonster(rivalMonster4, "4");
        setSelectRivalMonster(rivalMonster5, "5");
        setSelectMySpell(mySpell1, "1");
        setSelectMySpell(mySpell2, "2");
        setSelectMySpell(mySpell3, "3");
        setSelectMySpell(mySpell4, "4");
        setSelectMySpell(mySpell5, "5");
        setSelectRivalSpell(rivalSpell1, "1");
        setSelectRivalSpell(rivalSpell2, "2");
        setSelectRivalSpell(rivalSpell3, "3");
        setSelectRivalSpell(rivalSpell4, "4");
        setSelectRivalSpell(rivalSpell5, "5");
        setSelectMyHand(myHand1, "1");
        setSelectMyHand(myHand2, "2");
        setSelectMyHand(myHand3, "3");
        setSelectMyHand(myHand4, "4");
        setSelectMyHand(myHand5, "5");
    }

    public void setSelectMyMonster(ImageView imageView, String index) {
        imageView.setOnMouseClicked(event -> {
            selectionController.selectMyMonster(index);
            selectedCard.setImage(imageView.getImage());
        });
    }

    public void setSelectRivalMonster(ImageView imageView, String index) {
        imageView.setOnMouseClicked(event -> {
            selectionController.selectRivalMonster(index);
            selectedCard.setImage(imageView.getImage());
        });
    }

    public void setSelectMySpell(ImageView imageView, String index) {
        imageView.setOnMouseClicked(event -> {
            selectionController.selectMySpell(index);
            selectedCard.setImage(imageView.getImage());
        });
    }

    public void setSelectRivalSpell(ImageView imageView, String index) {
        imageView.setOnMouseClicked(event -> {
            selectionController.selectRivalSpell(index);
            selectedCard.setImage(imageView.getImage());
        });
    }

    public void setSelectMyHand(ImageView imageView, String index) {
        imageView.setOnMouseClicked(event -> {
            selectionController.selectHandCard(index);
            selectedCard.setImage(imageView.getImage());
        });
    }

    public void changePhase(ActionEvent actionEvent) {
        phaseController.changePhase();
        setCards();
        setLifePoint();
    }

    public void setPreparations() {
        myUserName.setText(GameController.player.getUserName());
        rivalName.setText(GameController.bot.getName());
        myName.setText(GameController.player.getNickName());
        rivalUserName.setText(GameController.bot.getName());
        myDeck.setImage(unknownCardImage);
        rivalDeck.setImage(unknownCardImage);
        myAvatar.setImage(new Image(GameController.player.getUser().getMiniAvatar()));
        rivalAvatar.setImage(new Image("./image/Avatars/bot.png"));
    }

    public void setDefense(MouseEvent mouseEvent) {
        settingController.set();
        setCards();
    }

    public void setAttack(MouseEvent mouseEvent) {
        settingController.set();
        setCards();
    }

    public void changePosition(MouseEvent mouseEvent) {
        new SettingController().changePosition();
        setCards();
    }

    public void activateEffect(MouseEvent mouseEvent) {
        activationController.activate();
        setCards();
        setLifePoint();
    }

    public void flipSummon(MouseEvent mouseEvent) {
        summonController.flipSummon();
        setCards();
        setLifePoint();
    }

    public void normalSummon(MouseEvent mouseEvent) {
        summonController.summon();
        setCards();
        setLifePoint();
    }

    public void specialSummon(MouseEvent mouseEvent) {
        summonController.specialSummon();
        setCards();
        setLifePoint();
    }

    public void directAttack(MouseEvent mouseEvent) {
        attackController.directAttack();
        setCards();
        setLifePoint();
    }

    public void setCards() {
        ImageView[] myHands = {myHand1, myHand2, myHand3, myHand4, myHand5, myHand6, myHand7, myHand8, myHand9, myHand10};
        setCardImages(myHands, GameController.player.getPlayerBoard().getHandCards());
        ImageView[] rivalHands = {rivalHand1, rivalHand2, rivalHand3, rivalHand4, rivalHand5, rivalHand6, rivalHand7,
                rivalHand8, rivalHand9, rivalHand10};
        setCardImages(rivalHands, GameController.bot.getBoard().getHandCards());
        ImageView[] myMonsters = {myMonster1, myMonster2, myMonster3, myMonster4, myMonster5};
        setMonsterCardImages(myMonsters, GameController.player.getPlayerBoard().getMonsters());
        ImageView[] rivalMonsters = {rivalMonster1, rivalMonster2, rivalMonster3, rivalMonster4, rivalMonster5};
        setMonsterCardImages(rivalMonsters, GameController.bot.getAIMonsters());
        ImageView[] mySpellTraps = {mySpell1, mySpell2, mySpell3, mySpell4, mySpell5};
        setSpellTrapCardImages(mySpellTraps, GameController.player.getPlayerBoard().getSpellTraps());
        ImageView[] rivalSpellTraps = {rivalSpell1, rivalSpell2, rivalSpell3, rivalSpell4, rivalSpell5};
        setSpellTrapCardImages(rivalSpellTraps, GameController.bot.getAISpellTraps());
        if (GameController.player.getPlayerBoard().getFieldZone() != null)
            myFieldSpell.setImage(new Image("./image/Cards/" + GameController.player.getPlayerBoard().getFieldZone().getName() + ".png"));
        if (GameController.bot.getBoard().getFieldZone() != null)
            myFieldSpell.setImage(new Image("./image/Cards/" + GameController.bot.getBoard().getFieldZone().getName() + ".png"));
    }

    private void setCardImages(ImageView[] imageViews, ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getIsHidden()) {
                String name = cards.get(i).getName();
                imageViews[i].setImage(new Image("./image/Cards/" + name + ".png"));
            }
            else
                imageViews[i].setImage(unknownCardImage);
        }
    }

    private void setMonsterCardImages(ImageView[] imageViews, ArrayList<MonsterCard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getIsHidden()) {
                String name = cards.get(i).getName();
                imageViews[i].setImage(new Image("./image/Cards/" + name + ".png"));
            }
            else
                imageViews[i].setImage(unknownCardImage);
        }
    }

    private void setSpellTrapCardImages(ImageView[] imageViews, ArrayList<SpellTrapCard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getIsHidden()) {
                String name = cards.get(i).getName();
                imageViews[i].setImage(new Image("./image/Cards/" + name + ".png"));
            }
            else
                imageViews[i].setImage(unknownCardImage);
        }
    }

    private void setLifePoint() {
        myLPLabel1.setText(String.valueOf(GameController.player.getPlayerBoard().getLifePoints()));
        myLP.setProgress((double) GameController.player.getPlayerBoard().getLifePoints() / 8000.0);
        rivalLPLabel.setText(String.valueOf(GameController.bot.getBoard().getLifePoints()));
        rivalLP.setProgress((double) GameController.bot.getBoard().getLifePoints() / 8000.0);
    }

    public void openSettingScene(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/setting.fxml",actionEvent);
    }
}
