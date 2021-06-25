import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class MainMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -n b -p c
                user login -p c -u a
                menu show-current
                hey
                menu enter Duel
                menu exit
                menu enter Deck
                menu exit
                menu enter Scoreboard
                menu exit
                menu enter Profile
                menu exit
                menu enter Shop
                menu exit
                menu enter Import/Export
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                Main Menu\r
                invalid command\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}

