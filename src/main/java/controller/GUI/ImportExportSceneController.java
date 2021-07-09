package controller.GUI;


import javafx.event.ActionEvent;
import java.io.IOException;

public class ImportExportSceneController {
    public void importCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/ImportScene.fxml", actionEvent);
    }

    public void exportCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/ExportScene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/main_menu.fxml", actionEvent);
    }
}
