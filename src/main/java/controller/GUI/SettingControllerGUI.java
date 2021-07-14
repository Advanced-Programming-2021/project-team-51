package controller.GUI;

import controller.duel.PhaseController;
import controller.menucontroller.LoginMenuController;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SettingControllerGUI {

    public void volumeUp() {
        double currentVolume = LoginControllerGUI.player.getVolume();
        if (currentVolume == 1)
            return;
        LoginControllerGUI.player.setVolume(currentVolume + 0.1);
    }

    public void volumeDown() {
        double currentVolume = LoginControllerGUI.player.getVolume();
        if (currentVolume <= 0)
            return;
        LoginControllerGUI.player.setVolume(currentVolume - 0.1);
    }

    public void mute() {
        LoginControllerGUI.player.setMute(!LoginControllerGUI.player.isMute());
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("/fxml/gameField.fxml", actionEvent);
    }

    public void surrender(MouseEvent event) throws IOException {
        if(DuelViewSceneController.isMultiPlayer){
            if (PhaseController.playerInTurn.getUser() == LoginMenuController.currentUser) {
                DuelViewSceneController.phaseController.endGame(PhaseController.playerAgainst, PhaseController.playerInTurn);
                SceneController.switchSceneMouse("/fxml/GameOver.fxml",event);
            }
            else{
                DuelViewSceneController.phaseController.endGame(PhaseController.playerInTurn,PhaseController.playerAgainst);
                SceneController.switchSceneMouse("/fxml/YouWon.fxml",event);
            }
        }
        else{
            DuelViewSceneController.gameController.endGame("bot");
            SceneController.switchSceneMouse("/fxml/GameOver.fxml",event);
        }
    }
}
