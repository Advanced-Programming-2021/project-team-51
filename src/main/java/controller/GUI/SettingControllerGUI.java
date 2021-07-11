package controller.GUI;

import javafx.event.ActionEvent;

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

    public void brightnessUp() {
    }

    public void brightnessDown() {
    }

    public void mute() {
        LoginControllerGUI.player.setMute(!LoginControllerGUI.player.isMute());
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneController.switchScene("/fxml/gameField.fxml", actionEvent);
    }
}
