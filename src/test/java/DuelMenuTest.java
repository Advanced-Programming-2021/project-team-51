import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class DuelMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -p a -n a
                user creaate -u b -n b -p b
                user create -u b -n b -p b
                user login -u a -p a
                menu enter Deck
                deck create Mamad
                deck set-activate Mamad
                menu eixt
                eixt menu
                exit menu
                menu exit
                menu enter Duel
                duel -n -sp v -r 2
                duel -n -sp b -r 2
                b has no active deck
                menu eixt
                menu exit
                user logout -u a
                user logout -u a -p a
                menu show-current
                user logout
                user login -u b -p b
                menu enter Deck
                deck create Parsa
                deck set-activate Parsa
                menu exit
                menu exit
                menu exit
                """).getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                invalid command\r
                user created successfully!\r
                user logged in successfully!\r
                deck created successfully!\r
                deck activated successfully\r
                invalid command\r
                invalid command\r
                invalid command\r
                there is no player with this username\r
                b has no active deck\r
                invalid command!\r
                invalid command!\r
                invalid command\r
                invalid command\r
                Main Menu\r
                user logged out successfully!\r
                user logged in successfully!\r
                deck created successfully!\r
                deck activated successfully\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}
