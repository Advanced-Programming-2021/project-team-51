import controller.menucontroller.MakeCardController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MakeMonsterSceneController implements Initializable {
    MakeCardController makeCardController = new MakeCardController();

    public ImageView cardImage = null;
    public TextField cardName = null;
    public TextArea cardDescription = null;
    public Spinner<Integer> attackPointSpinner;
    public Spinner<Integer> defensePointSpinner;
    public ChoiceBox<String> attributeChoiceBox;
    public ChoiceBox<String> monsterTypeChoiceBox;
    public Label cardTypeLabel;
    public Label levelLabel;
    public Label priceLabel;
    public Label statusLabel;
    public CheckBox killMonsterCheckBox;
    public CheckBox killSpellCheckBox;
    public CheckBox increaseLPCheckBox;
    public CheckBox decreaseLPCheckBox;
    public CheckBox increaseAttackCheckBox;
    public CheckBox increaseDefenseCheckBox;

    public void handleDroppedImage(DragEvent dragEvent) {
        List<File> files = dragEvent.getDragboard().getFiles();
        try {
            Image image = new Image(new FileInputStream(files.get(files.size() - 1)));
            cardImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void goBack(ActionEvent actionEvent) {
        new SceneController().switchScene("MakeCardScene.fxml", actionEvent);
    }

    public void calculate() {
        CheckBox[] effects = {killMonsterCheckBox, killSpellCheckBox, increaseLPCheckBox, decreaseLPCheckBox,
                increaseAttackCheckBox, increaseDefenseCheckBox};
        levelLabel.setText(makeCardController.calculateMonsterLevel(attackPointSpinner, defensePointSpinner, effects));
        priceLabel.setText(makeCardController.calculateMonsterPrice(attackPointSpinner, defensePointSpinner, effects));
        cardTypeLabel.setText(makeCardController.calculateMonsterCardType(effects));
    }

    public void makeMonster() {
        CheckBox[] effects = {killMonsterCheckBox, killSpellCheckBox, increaseLPCheckBox, decreaseLPCheckBox,
                increaseAttackCheckBox, increaseDefenseCheckBox};
        if (makeCardController.doesHaveProblemMakingMonster(cardImage, cardName, cardDescription, attackPointSpinner,
                defensePointSpinner, effects) != null) {
            statusLabel.setText(makeCardController.doesHaveProblemMakingMonster(cardImage, cardName, cardDescription, attackPointSpinner,
                    defensePointSpinner, effects));
            return;
        }
        makeCardController.makeMonsterCard(cardName, cardDescription, cardImage, attackPointSpinner, defensePointSpinner
                , effects, attributeChoiceBox, monsterTypeChoiceBox);
        statusLabel.setText("Monster is made successfully");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //attribute
        String[] attributes = {"DARK", "EARTH", "FIRE", "LIGHT", "WATER", "WIND"};
        attributeChoiceBox.getItems().addAll(attributes);
        attributeChoiceBox.setValue("EARTH");
        //monster type
        String[] monsterTypes = {"Beast-Warrior", "Warrior", "Fiend", "Aqua", "Beast", "Pyro", "Spellcaster", "Thunder",
                "Dragon", "Machine", "Rock", "Insect", "Cyberse", "Fairy", "Sea Serpent"};
        monsterTypeChoiceBox.getItems().addAll(monsterTypes);
        monsterTypeChoiceBox.setValue("Warrior");
        //attack point & defense point
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 4000);
        valueFactory.increment(50);
        valueFactory.setValue(100);
        attackPointSpinner.setValueFactory(valueFactory);
        defensePointSpinner.setValueFactory(valueFactory);
    }
}
