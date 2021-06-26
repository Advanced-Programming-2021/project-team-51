import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class ProfileMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u p -n e -p x
                user login -u p -p x
                menu enter Profile
                sad
                profile change -p -c x -n y
                profile change -p -c x -n y
                profile change -n mamad
                profile change -u ali
                menu enter Shop
                menu show-current
                menu exit
                user logout
                menu show-current
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                invalid command\r
                password changed successfully!\r
                current password is invalid\r
                nickname changed successfully!\r
                username changed successfully!\r
                menu navigation is not possible\r
                Profile\r
                user logged out successfully!\r
                Login Menu\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}

