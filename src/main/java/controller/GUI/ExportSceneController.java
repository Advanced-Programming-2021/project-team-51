package controller.GUI;

import controller.menucontroller.ImportExportController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ExportSceneController {

    ImportExportController importExportController = new ImportExportController();

    public ToggleButton asJsonToggleButton;
    public Label statusLabel;
    public TextField cardName;
    public ImageView cardImageView;

    public void goBack(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/ImportExportScene.fxml", actionEvent);
    }

    public void export(ActionEvent actionEvent) {
        importExportController.exportCard(cardName, asJsonToggleButton, statusLabel);
        if (statusLabel.getText().equals("card exported successfully"))
            cardImageView.setImage(new Image(cardName.getText() + ".png"));
    }
}
