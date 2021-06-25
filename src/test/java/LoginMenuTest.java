import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class LoginMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("hey\nmenu enter main\nmenu show-current\nmenu exit".getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                invalid command\r
                please login first\r
                Login Menu\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void SignupLoginTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -n b -p c
                user create -u a -p c -n b
                user create -n b -u b -p c
                user create -n b -p c -u a
                user create -p c -u a -n b
                user create -p c -n b -u a
                user create -u a -n b -p c
                user login -u a -p b
                user login -u lll -p b
                user login -p c -u a
                menu exit
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user with username a already exists\r
                user with nickname b already exists\r
                user with username a already exists\r
                user with username a already exists\r
                user with username a already exists\r
                user with username a already exists\r
                Username and password didn’t match!\r
                Username and password didn’t match!\r
                user logged in successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}
