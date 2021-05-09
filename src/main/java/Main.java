import controller.ProgramController;
import models.SaveData;

public class Main {
    public static void main(String[] args) {
        SaveData saveData = new SaveData();
        saveData.load();
        ProgramController programController = new ProgramController();
        programController.run();
        saveData.save();
    }
}
