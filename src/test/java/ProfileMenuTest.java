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
                profile change -p -n z -c y
                profile change -p -c x -n y
                profile change -p -c z -n z
                profile change -n mamad
                profile change -n mamad
                profile change -n mohsen
                profile change -u ali
                profile change -u ali
                menu enter Shop
                menu show-current
                profile change -c a -p -n b
                profile change -c b -n a -p
                profile change -n q -p -c a
                profilechange -n s -c q -p
                profile change -n s -c q -p
                menu exit
                menu show-current
                user logout
                menu exit
                """).getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                invalid command\r
                password changed successfully!\r
                password changed successfully!\r
                current password is invalid\r
                please enter a new password\r
                nickname changed successfully!\r
                user with nickname mamad already exists\r
                nickname changed successfully!\r
                username changed successfully!\r
                user with username ali already exists\r
                menu navigation is not possible\r
                Profile\r
                current password is invalid\r
                current password is invalid\r
                current password is invalid\r
                invalid command\r
                current password is invalid\r
                Main Menu\r
                user logged out successfully!\r                               
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}

