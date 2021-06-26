import models.User;
import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class ScoreboardMenuTest {

    @Test
    public void BuyTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        User user = new User("a", "a", "a");
        user.setScore(100);
        User user2 = new User("b", "b", "b");
        user2.setScore(10);
        User user3 = new User("c", "c", "c");
        user3.setScore(10);
        ByteArrayInputStream in = new ByteArrayInputStream(("""
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
                user logged in successfully!\r
                Scoreboard\r
                invalid command\r
                1-a: 100\r
                2-b: 10\r
                2-c: 10\r
                menu navigation is not possible\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }

}

