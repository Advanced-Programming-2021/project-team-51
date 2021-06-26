import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class DuelViewTest {

    @Test
    public void generalMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -n b -p c
                user login -p c -u a
                menu enter Shop
                shop buy Hero of the east
                menu exit
                menu enter Deck
                menu enter Deck
                hey
                deck show -c
                deck show -a
                menu show-current
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                you bought the card successfully\r
                menu navigation is not possible\r
                invalid command\r
                Hero of the east:Feel da strength ah dis sword-swinging samurai from da Far East.\r
                Decks:\r
                Active Deck: \r
                Other decks:\r
                Deck\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}
