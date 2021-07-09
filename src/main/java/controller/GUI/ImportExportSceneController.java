package controller.GUI;


import javafx.event.ActionEvent;
import java.io.IOException;

public class ImportExportSceneController {
    public void importCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/Import_Scene.fxml", actionEvent);
    }

    public void exportCard(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/Export_Scene.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/mainMenu.fxml", actionEvent);
    }
}
