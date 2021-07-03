package Controller;


import javafx.event.ActionEvent;

import java.awt.event.InputEvent;
import java.io.IOException;

public class LoginControllerGUI  {



    public void enterLoginMenu(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/login_menu.fxml",actionEvent);
    }
    public void
}
