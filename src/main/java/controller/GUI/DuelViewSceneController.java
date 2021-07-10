package controller.GUI;


import controller.duel.SettingController;
import controller.duel.singlePlayer.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    public ImageView rivalSpell13;
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

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void handleAttackOnMonster4(DragEvent dragEvent) {

    }

    public void handleAttackOnMonster2(DragEvent dragEvent) {
    }

    public void handleAttackOnMonster1(DragEvent dragEvent) {
    }

    public void handleAttackOnMonster3(DragEvent dragEvent) {
    }

    public void handleAttackOnMonster5(DragEvent dragEvent) {

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
        myLP.setProgress(1);
        rivalLP.setProgress(1);
    }

    public void changePhase(ActionEvent actionEvent) {

    }

    public void setPreparations() {
        myUserName.setText(GameController.player.getUserName());
        rivalName.setText(GameController.bot.getName());
        myName.setText(GameController.player.getNickName());
        rivalUserName.setText(GameController.bot.getName());
        myDeck.setImage(unknownCardImage);
        rivalDeck.setImage(unknownCardImage);
        //TODO myAvatar.setImage();
        //TODO rivalAvatar.setImage();
    }

    public void setDefense(MouseEvent mouseEvent) {
    }

    public void setAttack(MouseEvent mouseEvent) {
    }

    public void changePosition(MouseEvent mouseEvent) {
        new SettingController().changePosition();
    }

    public void activateEffect(MouseEvent mouseEvent) {
    }

    public void flipSummon(MouseEvent mouseEvent) {
    }

    public void normalSummon(MouseEvent mouseEvent) {
    }

    public void specialSummon(MouseEvent mouseEvent) {
    }

    public void directAttack(MouseEvent mouseEvent) {

    }
}
