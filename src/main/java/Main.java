import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        stage.setTitle("Yu Gi Oh");
        stage.setScene(new Scene(root));
        primaryStage = stage;
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
