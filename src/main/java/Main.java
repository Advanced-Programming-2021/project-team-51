/*import view.ProgramController;
import models.SaveData;
*/
import view.ProgramController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {

        ProgramController programController = new ProgramController();
        programController.run();

    }
}
//    FileOutputStream fos = new FileOutputStream("1.txt", true);
//        fos.close();
//        /*SaveData saveData = new SaveData();
//        saveData.load();
//        ProgramController programController = new ProgramController();
//        programController.run();
//        saveData.save();*/