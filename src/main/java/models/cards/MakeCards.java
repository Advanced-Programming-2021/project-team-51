package models.cards;

public class MakeCards {
    public static Card makeCard(String name) {
        Card card = makeNormalMonsterCard(name);
        if (card != null) return card;
        card = makeEffectMonsterCard(name);
        if (card != null) return card;
        card = makeRitualCard(name);
        if (card != null) return card;
        card = makeTrapCard(name);
        if (card != null) return card;
        else return makeSpellCard(name);
    }

    private static Card makeSpellCard(String name) {
        if (name.equals("Monster Reborn"))
            return new SpellTrapCard(name, "Target 1 monster in either GY; Special Summon it.",
                    2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Terraforming"))
            return new SpellTrapCard(name, "Add 1 Field Spell from your Deck to your hand.",
                    2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Pot of Greed"))
            return new SpellTrapCard(name, "Draw 2 cards.", 2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Raigeki"))
            return new SpellTrapCard(name, "Destroy all monsters your opponent controls.",
                    2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Change of Heart"))
            return new SpellTrapCard(name, "Target 1 monster your opponent controls; take control of it until" +
                    " the End Phase.", 2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Swords of Revealing Light"))
            return new SpellTrapCard(name, "After this card's activation, it remains on the field, but destroy " +
                    "it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls" +
                    " a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, " +
                    "your opponent's monsters cannot declare an attack.", 2500, CardType.SPELL, Icon.NORMAL, false);
        else if (name.equals("Harpie's Feather Duster"))
            return new SpellTrapCard(name, "Destroy all Spells and Traps your opponent controls.",
                    2500, CardType.SPELL, Icon.NORMAL, true);
        else if (name.equals("Dark Hole"))
            return new SpellTrapCard(name, "Destroy all monsters on the field.",
                    2500, CardType.SPELL, Icon.NORMAL, false);
        else if (name.equals("Supply Squad"))
            return new SpellTrapCard(name, "Once per turn, if a monster(s) you control is destroyed by battle" +
                    " or card effect: Draw 1 card.", 4000, CardType.SPELL, Icon.CONTINUOUS, false);
        else if (name.equals("Spell Absorption"))
            return new SpellTrapCard(name, "Each time a Spell Card is activated, gain 500 Life Points " +
                    "immediately after it resolves.", 4000, CardType.SPELL, Icon.CONTINUOUS, false);
        else if (name.equals("Messenger of peace"))
            return new SpellTrapCard(name, "Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during" +
                    " your Standby Phase, pay 100 LP or destroy this card.", 4000, CardType.SPELL, Icon.CONTINUOUS, false);
        else if (name.equals("Twin Twisters"))
            return new SpellTrapCard(name, "Discard 1 card, then target up to 2 Spells/Traps on the field;" +
                    " destroy them.", 3500, CardType.SPELL, Icon.QUICK_PLAY, false);
        else if (name.equals("Mystical space typhoon"))
            return new SpellTrapCard(name, "Target 1 Spell/Trap on the field; destroy that target.",
                    3500, CardType.SPELL, Icon.QUICK_PLAY, false);
        else if (name.equals("Ring of defense"))
            return new SpellTrapCard(name, "When a Trap effect that inflicts damage is activated: Make that " +
                    "effect damage 0.", 3500, CardType.SPELL, Icon.QUICK_PLAY, false);
        else if (name.equals("Yami"))
            return new SpellTrapCard(name, "All Fiend and Spellcaster monsters on the field gain 200 ATK/DEF, also" +
                    " all Fairy monsters on the field lose 200 ATK/DEF.", 4300, CardType.SPELL, Icon.FIELD, false);
        else if (name.equals("Forest"))
            return new SpellTrapCard(name, "All Insect, Beast, Plant, and Beast-Warrior monsters on the field" +
                    " gain 200 ATK/DEF.", 4300, CardType.SPELL, Icon.FIELD, false);
        else if (name.equals("Closed Forest"))
            return new SpellTrapCard(name, "All Beast-Type monsters you control gain 100 ATK for each monster" +
                    " in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated " +
                    "during the turn this card is destroyed.", 4300, CardType.SPELL, Icon.FIELD, false);
        else if (name.equals("Umiiruka"))
            return new SpellTrapCard(name, "Increase the ATK of all WATER monsters by 500 points and decrease" +
                    " their DEF by 400 points.", 4300, CardType.SPELL, Icon.FIELD, false);
        else if (name.equals("Sword of dark destruction"))
            return new SpellTrapCard(name, "A DARK monster equipped with this card increases its ATK by 400 " +
                    "points and decreases its DEF by 200 points.", 4300, CardType.SPELL, Icon.EQUIP, false);
        else if (name.equals("Black Pendant"))
            return new SpellTrapCard(name, "The equipped monster gains 500 ATK. When this card is sent from the field" +
                    " to the Graveyard: Inflict 500 damage to your opponent.", 4300, CardType.SPELL, Icon.EQUIP, false);
        else if (name.equals("United We Stand"))
            return new SpellTrapCard(name, "The equipped monster gains 800 ATK/DEF for each face-up monster" +
                    " you control.", 4300, CardType.SPELL, Icon.EQUIP, false);
        else if (name.equals("Magnum Shield"))
            return new SpellTrapCard(name, "\"Equip only to a Warrior-Type monster. Apply this effect, depending on " +
                    "its battle position.\nâ—\u008F Attack Position: It gains ATK equal to its original DEF.\nâ—\u008F Defense" +
                    " Position: It gains DEF equal to its original ATK.\"\n", 4300, CardType.SPELL, Icon.EQUIP, false);
        else if (name.equals("Advanced Ritual Art"))
            return new SpellTrapCard(name, "This card can be used to Ritual Summon any 1 Ritual Monster. You " +
                    "must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level " +
                    "of that Ritual Monster.", 3000, CardType.SPELL, Icon.RITUAL, false);
        return null;
    }

    private static Card makeTrapCard(String name) {
        if (name.equals("Trap Hole"))
            return new SpellTrapCard(name, "When your opponent Normal or Flip Summons 1 monster with 1000 or more " +
                    "ATK: Target that monster; destroy that target.", 2000, CardType.TRAP, Icon.NORMAL, false);
        else if (name.equals("Mirror Force"))
            return new SpellTrapCard(name, "When an opponent's monster declares an attack: Destroy all your" +
                    " opponent's Attack Position monsters.", 2000, CardType.TRAP, Icon.NORMAL, false);
        else if (name.equals("Magic Cylinder"))
            return new SpellTrapCard(name, "When an opponent's monster declares an attack: Target the " +
                    "attacking monster; negate the attack, and if you do, inflict damage to your opponent " +
                    "equal to its ATK.", 2000, CardType.TRAP, Icon.NORMAL, false);
        else if (name.equals("Mind Crush"))
            return new SpellTrapCard(name, "Declare 1 card name; if that card is in your opponent's hand, they must discard " +
                    "all copies of it, otherwise you discard 1 random card.", 2000, CardType.TRAP, Icon.NORMAL, false);
        else if (name.equals("Torrential Tribute"))
            return new SpellTrapCard(name, "When a monster(s) is Summoned: Destroy all monsters on the field.",
                    2000, CardType.TRAP, Icon.NORMAL, false);
        else if (name.equals("Time Seal"))
            return new SpellTrapCard(name, "Skip the Draw Phase of your opponent's next turn.",
                    2000, CardType.TRAP, Icon.NORMAL, true);
        else if (name.equals("Negate Attack"))
            return new SpellTrapCard(name, "When an opponent's monster declares an attack: Target the attacking" +
                    " monster; negate the attack, then end the Battle Phase.", 3000, CardType.TRAP, Icon.COUNTER, false);
        else if (name.equals("Solemn Warning"))
            return new SpellTrapCard(name, "When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster" +
                    " effect, is activated that includes an effect that Special Summons a monster(s): Pay 2000 LP; negate" +
                    " the Summon or activation, and if you do, destroy it.", 3000, CardType.TRAP, Icon.COUNTER, false);
        else if (name.equals("Magic Jamamer"))
            return new SpellTrapCard(name, "When a Spell Card is activated: Discard 1 card; negate the " +
                    "activation, and if you do, destroy it.", 3000, CardType.TRAP, Icon.COUNTER, false);
        else if (name.equals("Call of The Haunted"))
            return new SpellTrapCard(name, "Activate this card by targeting 1 monster in your GY; Special Summon " +
                    "that target in Attack Position. When this card leaves the field, destroy that monster. When that monster " +
                    "is destroyed, destroy this card.", 3500, CardType.TRAP, Icon.CONTINUOUS, false);
        else if (name.equals("Vanity's Emptiness"))
            return new SpellTrapCard(name, "Neither player can Special Summon monsters. If a card is sent from the " +
                    "Deck or the field to your Graveyard: Destroy this card.", 3500, CardType.TRAP, Icon.CONTINUOUS, true);
        else if (name.equals("Wall of Revealing Light"))
            return new SpellTrapCard(name, "Activate by paying any multiple of 1000 Life Points." +
                    " Monsters your opponent controls cannot attack if their ATK is less than or equal to the" +
                    " amount you paid.", 3500, CardType.TRAP, Icon.CONTINUOUS, true);
        return null;
    }

    private static Card makeRitualCard(String name) {
        if (name.equals("Crab Turtle"))
            return new MonsterCard(name, "This monster can only be Ritual Summoned with the Ritual Spell Card, \"Turtle Oath\"." +
                    " You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.",
                    10200, 8, Attribute.WATER, MonsterType.AQUA, 2500, 2500, Trait.RITUAL);
        else if (name.equals("Skull Guardian"))
            return new MonsterCard(name, "This monster can only be Ritual Summoned with the Ritual Spell Card, \"Novox's Prayer\"." +
                    " You must also offer monsters whose total Level Stars equal 7 or more as a Tribute from the field or your hand.",
                    7900, 7, Attribute.LIGHT, MonsterType.WARRIOR, 2050, 2500, Trait.RITUAL);
        return null;
    }

    private static Card makeEffectMonsterCard(String name) {
        if (name.equals("Yomi Ship"))
            return new MonsterCard(name, "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.",
                    1700, 3, Attribute.WATER, MonsterType.AQUA, 800, 1400, Trait.EFFECT);
        else if (name.equals("Suijin"))
            return new MonsterCard(name, "During damage calculation in your opponent's turn," +
                    " if this card is being attacked: You can target the attacking monster; " +
                    "make that target's ATK 0 during damage calculation only (this is a Quick Effect). " +
                    "This effect can only be used once while this card is face-up on the field.",
                    8700, 7, Attribute.WATER, MonsterType.AQUA, 2500, 2400, Trait.EFFECT);
        else if (name.equals("Man-Eater Bug"))
            return new MonsterCard(name, "FLIP: Target 1 monster on the field; destroy that target.",
                    600, 2, Attribute.EARTH, MonsterType.INSECT, 450, 600, Trait.EFFECT);
        else if (name.equals("Gate Guardian"))
            return new MonsterCard(name, "Cannot be Normal Summoned/Set. Must first be Special Summoned" +
                    " (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".",
                    20000, 11, Attribute.DARK, MonsterType.WARRIOR, 3750, 3400, Trait.EFFECT);
        else if (name.equals("Scanner"))
            return new MonsterCard(name, "Once per turn, you can select 1 of your opponent's monsters that is" +
                    " removed from play. Until the End Phase, this card's name is treated as the selected monster's name," +
                    " and this card has the same Attribute, Level, ATK, and DEF as the selected monster." +
                    " If this card is removed from the field while this effect is applied, remove it from play.",
                    8000, 1, Attribute.LIGHT, MonsterType.MACHINE, 0, 0, Trait.EFFECT);
        else if (name.equals("Marshmallon"))
            return new MonsterCard(name, "Cannot be destroyed by battle. After damage calculation, if this card" +
                    " was attacked, and was face-down at the start of the Damage Step: The attacking player takes 1000 damage.",
                    700, 3, Attribute.LIGHT, MonsterType.FAIRY, 300, 500, Trait.EFFECT);
        else if (name.equals("Beast King Barbaros"))
            return new MonsterCard(name, "You can Normal Summon/Set this card without Tributing, but its " +
                    "original ATK becomes 1900. You can Tribute 3 monsters to Tribute Summon (but not Set) this card. " +
                    "If Summoned this way: Destroy all cards your opponent controls.",
                    9200, 8, Attribute.EARTH, MonsterType.BEAST_WARRIOR, 3000, 1200, Trait.EFFECT);
        else if (name.equals("Texchanger"))
            return new MonsterCard(name, "Once per turn, when your monster is targeted for an attack: You can" +
                    " negate that attack, then Special Summon 1 Cyberse Normal Monster from your hand, Deck, or GY.",
                    200, 1, Attribute.DARK, MonsterType.CYBERSE, 100, 100, Trait.EFFECT);
        else if (name.equals("The Calculator"))
            return new MonsterCard(name, "The ATK of this card is the combined Levels of all face-up monsters you control x 300.",
                    8000, 2, Attribute.LIGHT, MonsterType.THUNDER, 0, 0, Trait.EFFECT);
        else if (name.equals("Mirage Dragon"))
            return new MonsterCard(name, "Your opponent cannot activate Trap Cards during the Battle Phase.",
                    2500, 4, Attribute.LIGHT, MonsterType.DRAGON, 1600, 600, Trait.EFFECT);
        else if (name.equals("Herald of Creation"))
            return new MonsterCard(name, "Once per turn: You can discard 1 card," +
                    " then target 1 Level 7 or higher monster in your Graveyard; add that target to your hand.",
                    2700, 4, Attribute.LIGHT, MonsterType.SPELL_CASTER, 1800, 600, Trait.EFFECT);
        else if (name.equals("Exploder Dragon"))
            return new MonsterCard(name, "If this card is destroyed by battle and sent to the Graveyard: Destroy " +
                    "the monster that destroyed it. Neither player takes any battle damage from attacks involving this attacking card.",
                    1000, 3, Attribute.EARTH, MonsterType.DRAGON, 1000, 0, Trait.EFFECT);
        else if (name.equals("Terratiger, the Empowered Warrior"))
            return new MonsterCard(name, "When this card is Normal Summoned: You can Special Summon 1 Level 4" +
                    " or lower Normal Monster from your hand in Defense Position.",
                    3200, 4, Attribute.EARTH, MonsterType.WARRIOR, 1800, 1200, Trait.EFFECT);
        else if (name.equals("The Tricky"))
            return new MonsterCard(name, "You can Special Summon this card (from your hand) by discarding 1 card.",
                    4300, 5, Attribute.WIND, MonsterType.SPELL_CASTER, 2000, 1200, Trait.EFFECT);
        else if (name.equals("Command Knight"))
            return new MonsterCard(name, "All Warrior-Type monsters you control gain 400 ATK. If you control " +
                    "another monster, monsters your opponent controls cannot target this card for an attack.",
                    2100, 4, Attribute.FIRE, MonsterType.WARRIOR, 1000, 1000, Trait.EFFECT);
        return null;
    }

    private static Card makeNormalMonsterCard(String name) {
        if (name.equals("Battle OX"))
            return new MonsterCard(name, "A monster with tremendous power, it destroys enemies with a swing of its axe."
                    , 2900, 4, Attribute.EARTH, MonsterType.BEAST_WARRIOR, 1700, 1000, Trait.NORMAL);
        else if (name.equals("Axe Raider"))
            return new MonsterCard(name, "An axe-wielding monster of tremendous strength and agility."
                    , 3100, 4, Attribute.EARTH, MonsterType.WARRIOR, 1700, 1150, Trait.NORMAL);
        else if (name.equals("Horn Imp"))
            return new MonsterCard(name, "A small fiend that dwells in the dark, its single horn makes it a formidable opponent.",
                    2500, 4, Attribute.DARK, MonsterType.FIEND, 1300, 1000, Trait.NORMAL);
        else if (name.equals("Silver Fang"))
            return new MonsterCard(name, "A snow wolf that's beautiful to the eye, but absolutely vicious in battle.",
                    1700, 3, Attribute.EARTH, MonsterType.BEAST, 1200, 800, Trait.NORMAL);
        else if (name.equals("Fireyarou"))
            return new MonsterCard(name, "A malevolent creature wrapped in flames that attacks enemies with intense fire.",
                    2500, 4, Attribute.FIRE, MonsterType.PYRO, 1300, 100, Trait.NORMAL);
        else if (name.equals("Curtain of the dark ones"))
            return new MonsterCard(name, "A curtain that a spellcaster made, it is said to raise a dark power.",
                    700, 2, Attribute.DARK, MonsterType.SPELL_CASTER, 600, 500, Trait.NORMAL);
        else if (name.equals("Feral Imp"))
            return new MonsterCard(name, "A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.",
                    2800, 4, Attribute.DARK, MonsterType.FIEND, 1300, 1400, Trait.NORMAL);
        else if (name.equals("Dark magician"))
            return new MonsterCard(name, "The ultimate wizard in terms of attack and defense.",
                    8300, 7, Attribute.DARK, MonsterType.SPELL_CASTER, 2500, 2100, Trait.NORMAL);
        else if (name.equals("Wattkid"))
            return new MonsterCard(name, "A creature that electrocutes opponents with bolts of lightning.",
                    1300, 3, Attribute.LIGHT, MonsterType.THUNDER, 1000, 500, Trait.NORMAL);
        else if (name.equals("Baby dragon"))
            return new MonsterCard(name, "Much more than just a child, this dragon is gifted with untapped power.",
                    1600, 3, Attribute.WIND, MonsterType.DRAGON, 1200, 700, Trait.NORMAL);
        else if (name.equals("Hero of the east"))
            return new MonsterCard(name, "Feel da strength ah dis sword-swinging samurai from da Far East.",
                    1700, 3, Attribute.EARTH, MonsterType.WARRIOR, 1100, 1000, Trait.NORMAL);
        else if (name.equals("Battle warrior"))
            return new MonsterCard(name, "A warrior that fights with his bare hands!!!",
                    1300, 3, Attribute.EARTH, MonsterType.WARRIOR, 700, 1000, Trait.NORMAL);
        else if (name.equals("Crawling dragon"))
            return new MonsterCard(name, "This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.",
                    3900, 5, Attribute.EARTH, MonsterType.DRAGON, 1600, 1400, Trait.NORMAL);
        else if (name.equals("Flame manipulator"))
            return new MonsterCard(name, "This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".",
                    1500, 3, Attribute.FIRE, MonsterType.SPELL_CASTER, 900, 1000, Trait.NORMAL);
        else if (name.equals("Blue-Eyes white dragon"))
            return new MonsterCard(name, "This legendary dragon is a powerful engine of destruction" +
                    ". Virtually invincible, very few have faced this awesome creature and lived to tell the tale.",
                    11300, 8, Attribute.LIGHT, MonsterType.DRAGON, 3000, 2500, Trait.NORMAL);
        else if (name.equals("Slot Machine"))
            return new MonsterCard(name, "The machine's ability is said to vary according to its slot results.",
                    7500, 7, Attribute.DARK, MonsterType.MACHINE, 2000, 2300, Trait.NORMAL);
        else if (name.equals("Haniwa"))
            return new MonsterCard(name, "An earthen figure that protects the tomb of an ancient ruler.",
                    600, 2, Attribute.EARTH, MonsterType.ROCK, 500, 500, Trait.NORMAL);
        else if (name.equals("Bitron"))
            return new MonsterCard(name, "A new species found in electronic space. There's not much information on it.",
                    1000, 2, Attribute.EARTH, MonsterType.CYBERSE, 200, 2000, Trait.NORMAL);
        else if (name.equals("Leotron "))
            return new MonsterCard(name, "A territorial electronic monster that guards its own domain.",
                    2500, 4, Attribute.EARTH, MonsterType.CYBERSE, 2000, 0, Trait.NORMAL);
        else if (name.equals("Alexandrite Dragon"))
            return new MonsterCard(name, "Many of the czars' lost jewels can be found in the scales of this" +
                    " priceless dragon. Its creator remains a mystery, along with how they acquired the imperial " +
                    "treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.",
                    2600, 4, Attribute.LIGHT, MonsterType.DRAGON, 2000, 100, Trait.NORMAL);
        else if (name.equals("Warrior Dai Grepher"))
            return new MonsterCard(name, "The warrior who can manipulate dragons. Nobody knows his mysterious past.",
                    3400, 4, Attribute.EARTH, MonsterType.WARRIOR, 1700, 1600, Trait.NORMAL);
        else if (name.equals("Dark Blade"))
            return new MonsterCard(name, "They say he is a dragon-manipulating warrior from the dark world. " +
                    "His attack is tremendous, using his great swords with vicious power.",
                    3500, 4, Attribute.DARK, MonsterType.WARRIOR, 1800, 1500, Trait.NORMAL);
        else if (name.equals("Wattaildragon"))
            return new MonsterCard(name, "\"Capable of indefinite flight. Attacks by wrapping its body" +
                    " with electricity and ramming into opponents.\nIMPORTANT: Capturing the \"\"Wattaildragon\"\"" +
                    " is forbidden by the Ancient Rules and is a Level 6 offense, the minimum sentence for which is" +
                    " imprisonment for no less than 2500 heliocycles.\"",
                    5800, 6, Attribute.LIGHT, MonsterType.DRAGON, 2500, 1000, Trait.NORMAL);
        else if (name.equals("Spiral Serpent"))
            return new MonsterCard(name, "When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at" +
                    " work. No one has ever escaped its dreaded Spiral Wave to accurately describe the terror they experienced.",
                    11700, 8, Attribute.WATER, MonsterType.SEA_SERPENT, 2900, 2900, Trait.NORMAL);
        return null;
    }
}
