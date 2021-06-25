import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class DeckMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
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

    @Test
    public void deckControllTest() throws IOException, CloneNotSupportedException {
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
                deck create a
                deck create a
                deck delete b
                deck create b
                deck delete b
                deck set-activate b
                deck set-activate a
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                you bought the card successfully\r
                deck created successfully!\r
                deck with name a already exists\r
                deck with name b does not exist\r
                deck created successfully!\r
                deck deleted successfully\r
                deck with name b does not exist\r
                deck activated successfully\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void addCardTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -n b -p c
                user login -p c -u a
                menu enter Shop
                shop buy Hero of the east
                shop buy Hero of the east
                shop buy Hero of the east
                shop buy Hero of the east
                menu exit
                menu enter Deck
                deck create a
                deck create b
                deck set-activate a
                deck add-card -c a -d a -s
                deck add-card -c Hero of the east -s -d c
                deck add-card -d a -c Hero of the east -s
                deck add-card -d a -s -c Hero of the east
                deck add-card -d c -s -c Hero of the east
                deck add-card -d a -s -c Hero of the east
                deck add-card -s -c a -d a
                deck add-card -s -d a -c a
                deck add-card -c Hero of the east -d a
                deck add-card -c Hero of the east -d a
                deck add-card -c Time Seal -d a
                deck show -a
                deck show -d c -s
                deck show -s -d a
                deck show -d a
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                deck created successfully!\r
                deck created successfully!\r
                deck activated successfully\r
                card with name a does not exist\r
                card with name Hero of the east -s does not exist\r
                card with name Hero of the east -s does not exist\r
                card added to deck successfully\r
                deck with name c does not exist\r
                card added to deck successfully\r
                card with name a does not exist\r
                card with name a does not exist\r
                card added to deck successfully\r
                there are already three cards with name Hero of the east in deck a\r
                card with name Time Seal does not exist\r
                Decks:\r
                Active Deck:\s
                a: main deck 1, side deck 2, invalid\r
                Other decks:\r
                b: main deck 0, side deck 0, invalid\r
                deck with name c does not exist\r
                Deck: a\r
                Sidedeck:\r
                Monsters:\r
                Hero of the east: Feel da strength ah dis sword-swinging samurai from da Far East.\r
                Hero of the east: Feel da strength ah dis sword-swinging samurai from da Far East.\r
                Spell and Traps:\r
                Deck: a\r
                Maindeck:\r
                Monsters:\r
                Hero of the east: Feel da strength ah dis sword-swinging samurai from da Far East.\r
                Spell and Traps:\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void removeCardTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user create -u a -n b -p c
                user login -p c -u a
                menu enter Shop
                shop buy Forest
                shop buy Black Pendant
                menu exit
                menu enter Deck
                deck create a
                deck create b
                deck set-activate a
                deck add-card -d a -c Forest
                deck add-card -d b -s -c Black Pendant
                deck show -a
                deck show -d c -s
                deck show -s -d a
                deck show -d a
                deck rm-card -c a -d a -s
                deck rm-card -c a -s -d a
                deck rm-card -d a -c a -s
                deck rm-card -d b -s -c Forest
                deck rm-card -s -c a -d a
                deck rm-card -s -d a -c Forest
                deck rm-card -d a -c Forest
                deck rm-card -d b -c Black Pendant
                deck rm-card -s -d b -c Black Pendant
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user created successfully!\r
                user logged in successfully!\r
                you bought the card successfully\r
                you bought the card successfully\r
                deck created successfully!\r
                deck created successfully!\r
                deck activated successfully\r
                card added to deck successfully\r
                card added to deck successfully\r
                Decks:\r
                Active Deck:\s
                a: main deck 1, side deck 0, invalid\r
                Other decks:\r
                b: main deck 0, side deck 1, invalid\r
                deck with name c does not exist\r
                Deck: a\r
                Sidedeck:\r
                Monsters:\r
                Spell and Traps:\r
                Deck: a\r
                Maindeck:\r
                Monsters:\r
                Spell and Traps:\r
                Forest: All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\r
                card with name a does not exist in side deck\r
                card with name a -s does not exist in main deck\r
                card with name a -s does not exist in main deck\r
                card with name Forest does not exist in side deck\r
                deck with name -d does not exist\r
                card with name Forest does not exist in side deck\r
                card removed form deck successfully\r
                card with name Black Pendant does not exist in main deck\r
                card removed form deck successfully\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}
