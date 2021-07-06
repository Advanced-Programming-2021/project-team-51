import javafx.application.Application;
import javafx.stage.Stage;
import models.SaveData;
import view.ProgramController;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        SaveData.load();
        new ProgramController().run();
        SaveData.save();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}