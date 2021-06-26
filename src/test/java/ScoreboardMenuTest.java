import models.User;
import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class ScoreboardMenuTest {

    //TODO make two users and show scoreboard

    @Test
    public void BuyTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;

        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -p a -n a
                user login -u a -p a
                menu enter Scoreboard
                menu show-current
                ssssssssss
                scoreboard show
                menu enter Shop
                menu exit
                user logout
                menu exit
                """).getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                Scoreboard\r
                invalid command\r
                1-a: 0\r
                menu navigation is not possible\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }


    }

