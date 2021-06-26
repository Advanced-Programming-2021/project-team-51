import models.SaveData;
import view.ProgramController;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        SaveData.load();
        new ProgramController().run();
        SaveData.save();
    }
}