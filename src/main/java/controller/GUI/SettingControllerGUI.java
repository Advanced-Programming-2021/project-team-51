package controller.GUI;

import controller.menucontroller.LoginMenuController;
import javafx.event.ActionEvent;

public class SettingControllerGUI {
    public void volumeUp(ActionEvent actionEvent) {
        double currentVolume = LoginControllerGUI.player.getVolume();
        LoginControllerGUI.player.setVolume(currentVolume+1);
    }

    public void volumeDown(ActionEvent actionEvent) {
        double currentVolume = LoginControllerGUI.player.getVolume();
        LoginControllerGUI.player.setVolume(currentVolume-1);
    }

    public void brightnessUp(ActionEvent actionEvent) {
    }

    public void brightnessDown(ActionEvent actionEvent) {
    }

    public void mute(ActionEvent actionEvent) {
        LoginControllerGUI.player.setMute(!LoginControllerGUI.player.isMute());
    }
}
