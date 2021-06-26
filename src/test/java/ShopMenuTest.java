import models.User;
import org.junit.Assert;
import org.junit.Test;
import view.ProgramController;

import java.io.*;

public class ShopMenuTest {
    @Test
    public void GeneralMenuTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        User user = new User("a", "b", "c");
        user.setMoney(0);
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user login -p c -u a
                menu enter Shop
                shop buy Hero of the east
                menu show-current
                hey
                menu enter shop
                shop show money
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user logged in successfully!\r
                not enough money\r
                Shop\r
                invalid command\r
                menu navigation is not possible\r
                0\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }

    @Test
    public void BuyTest() throws IOException, CloneNotSupportedException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputStream sysInBackup = System.in;
        User user = new User("a", "b", "c");
        user.setMoney(99999999);
        ByteArrayInputStream in = new ByteArrayInputStream(("""
                user login -p c -u a
                menu enter Shop
                shop buy a
                shop buy Flame manipulator
                shop buy Crawling dragon
                shop buy Hero of the east
                shop buy Wattkid
                shop buy Suijin
                shop buy Yomi Ship
                shop buy Texchanger
                shop buy Terratiger, the Empowered Warrior
                shop buy Dark magician
                shop buy Feral Imp
                shop buy Curtain of the dark ones
                shop buy Fireyarou
                shop buy Horn Imp
                shop buy Silver Fang
                shop buy Alexandrite Dragon
                shop buy Axe Raider
                shop buy Baby dragon
                shop buy Battle OX
                shop buy Battle warrior
                shop buy Beast King Barbaros
                shop buy Bitron
                shop buy Blue-Eyes white dragon
                shop buy Command Knight
                shop buy Crab Turtle
                shop buy Skull Guardian
                shop buy Slot Machine
                shop buy Haniwa
                shop buy Man-Eater Bug
                shop buy Gate Guardian
                shop buy Scanner
                shop buy Bitron
                shop buy Marshmallon
                shop buy Beast King Barbaros
                shop buy Leotron
                shop buy The Calculator
                shop buy Alexandrite Dragon
                shop buy Mirage Dragon
                shop buy Herald of Creation
                shop buy Exploder Dragon
                shop buy Warrior Dai Grepher
                shop buy Dark Blade
                shop buy Wattaildragon
                shop buy Terratiger, the Empowered Warrior
                shop buy The Tricky
                shop buy Spiral Serpent
                shop buy Command Knight
                shop buy Trap Hole
                shop buy Mirror Force
                shop buy Magic Cylinder
                shop buy Mind Crush
                shop buy Torrential Tribute
                shop buy Time Seal
                shop buy Negate Attack
                shop buy Solemn Warning
                shop buy Magic Jammer
                shop buy Call of The Haunted
                shop buy Vanity's Emptiness
                shop buy Wall of Revealing Light
                shop buy Monster Reborn
                shop buy Terraforming
                shop buy Pot of Greed
                shop buy Raigeki
                shop buy Change of Heart
                shop buy Swords of Revealing Light
                shop buy Harpie's Feather Duster
                shop buy Dark Hole
                shop buy Supply Squad
                shop buy Spell Absorption
                shop buy Messenger of peace
                shop buy Twin Twisters
                shop buy Mystical space typhoon
                shop buy Ring of defense
                shop buy Yami
                shop buy Forest
                shop buy Closed Forest
                shop buy Umiiruka
                shop buy Sword of dark destruction
                shop buy Black Pendant
                shop buy United We Stand
                shop buy Magnum Shield
                shop buy Advanced Ritual Art
                shop show -a
                menu exit
                user logout
                menu exit""").getBytes());
        System.setIn(in);
        new ProgramController().run();
        Assert.assertEquals("""
                user logged in successfully!\r
                there is no card with this name\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                there is no card with this name\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                there is no card with this name\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                there is no card with this name\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                you bought the card successfully\r
                "Terratiger : 3200\r
                Alexandrite Dragon : 2600\r
                Axe Raider : 3100\r
                Baby dragon : 1600\r
                Battle OX : 2900\r
                Battle warrior : 1300\r
                Beast King Barbaros : 9200\r
                Bitron : 1000\r
                Blue-Eyes white dragon : 11300\r
                Command Knight : 2100\r
                Crab Turtle : 10200\r
                Crawling dragon : 3900\r
                Curtain of the dark ones : 700\r
                Dark Blade : 3500\r
                Dark magician : 8300\r
                Exploder Dragon : 1000\r
                Feral Imp : 2800\r
                Fireyarou : 2500\r
                Flame manipulator : 1500\r
                Gate Guardian : 20000\r
                Haniwa : 600\r
                Herald of Creation : 2700\r
                Hero of the east : 1700\r
                Horn Imp : 2500\r
                IMPORTANT: Capturing the ""Wattaildragon"" is forbidden by the Ancient Rules and is a Level 6 offense : 5800\r
                Leotron  : 2500\r
                Man-Eater Bug : 600\r
                Marshmallon : 700\r
                Mirage Dragon : 2500\r
                Scanner : 8000\r
                Silver Fang : 1700\r
                Skull Guardian : 7900\r
                Slot Machine : 7500\r
                Spiral Serpent : 11700\r
                Suijin : 8700\r
                Texchanger : 200\r
                The Calculator : 8000\r
                The Tricky : 4300\r
                Warrior Dai Grepher : 3400\r
                Wattaildragon : 5800\r
                Wattkid : 1300\r
                Yomi Ship : 1700\r
                Advanced Ritual Art : 3000\r
                Black Pendant : 4300\r
                Call of The Haunted : 3500\r
                Change of Heart : 2500\r
                Closed Forest : 4300\r
                Dark Hole : 2500\r
                Forest : 4300\r
                Harpie's Feather Duster : 2500\r
                Magic Cylinder : 2000\r
                Magic Jammer : 3000\r
                Magnum Shield : 4300\r
                Messenger of peace : 4000\r
                Mind Crush : 2000\r
                Mirror Force : 2000\r
                Monster Reborn : 2500\r
                Mystical space typhoon : 3500\r
                Negate Attack : 3000\r
                Pot of Greed : 2500\r
                Raigeki : 2500\r
                Ring of defense : 3500\r
                Solemn Warning : 3000\r
                Spell Absorption : 4000\r
                Supply Squad : 4000\r
                Sword of dark destruction : 4300\r
                Swords of Revealing Light : 2500\r
                Terraforming : 2500\r
                Time Seal : 2000\r
                Torrential Tribute : 2000\r
                Trap Hole : 2000\r
                Twin Twisters : 3500\r
                Umiiruka : 4300\r
                United We Stand : 4300\r
                Vanity's Emptiness : 3500\r
                Wall of Revealing Light : 3500\r
                Yami : 4300\r
                user logged out successfully!\r
                """, outContent.toString());
        System.setIn(sysInBackup);
    }
}
