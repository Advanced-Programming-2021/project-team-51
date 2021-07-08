package controller.GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ImportExportSceneController {
    public void importCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("ImportScene.fxml", actionEvent);
    }

    public void exportCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("ExportScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("MainMenuScene.fxml", actionEvent);
    }
}
