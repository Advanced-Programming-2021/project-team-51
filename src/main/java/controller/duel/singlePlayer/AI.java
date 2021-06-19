package controller.duel.singlePlayer;

import java.util.ArrayList;
import java.util.HashMap;

import controller.duel.GamePhase;
import models.Deck;
import models.Player;
import models.Board;
import models.cards.Card;
import models.cards.CardType;
import models.cards.monsters.Attribute;
import models.cards.monsters.Mode;
import models.cards.monsters.MonsterCard;
import models.cards.monsters.MonsterType;
import models.cards.monsters.Trait;
import models.cards.spelltrap.Icon;
import models.cards.spelltrap.SpellTrapCard;

abstract public class AI {
    protected Deck deck;
    protected Board board;
    protected String nickname;
    protected Player opponent;
    protected static AI aiBot;

    public static AI getInstance() {
        return aiBot;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getName() {
        return this.nickname;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void destroyGeneratedDeck() {
        this.deck.removeCopiedDeck();
    }

    public ArrayList<MonsterCard> getOpponentMonsters() {
        return this.opponent.getPlayerBoard().getMonsters();
    }

    public ArrayList<MonsterCard> getAIMonsters() {
        return this.board.getMonsters();
    }

    public ArrayList<SpellTrapCard> getOpponentSpellTraps() {
        return this.opponent.getPlayerBoard().getSpellTraps();
    }

    public ArrayList<SpellTrapCard> getAISpellTraps() {
        return this.board.getSpellTraps();
    }

    protected int getTrapCard(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.TRAP)
                return i;

        return -1;
    }

    protected int getSpellCard(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.SPELL)
                return i;

        return -1;
    }

    protected int getBestSpecialMonsterIndex(ArrayList<Card> cards, boolean canSpecialSummon2) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) cards.get(i);
                if (monster.getLevel() > 6 && monster.getLevel() > maximum && canSpecialSummon2) {
                    maximum = monster.getLevel();
                    index = i;
                } else if (monster.getLevel() > 4 && monster.getLevel() < 7 && monster.getLevel() > maximum) {
                    maximum = monster.getLevel();
                    index = i;
                }
            }
        return index;
    }

    protected boolean canSpecialSummonLevel2() {
        ArrayList<MonsterCard> cards = getAIMonsters();
        int counter = 0;
        for (MonsterCard card : cards) {
            if (card.getLevel() < 5)
                counter++;
        }
        return counter > 1;
    }

    protected boolean canSpecialSummonLevel1() {
        ArrayList<MonsterCard> cards = getAIMonsters();
        int counter = 0;
        for (MonsterCard card : cards) {
            if (card.getLevel() < 5)
                counter++;
        }
        return counter > 0;
    }

    protected void sacrificeWeakestMonster() {
        ArrayList<MonsterCard> cards = new ArrayList<>();
        cards = getAIMonsters();
        int minimum = 10;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getLevel() < minimum) {
                minimum = cards.get(i).getLevel();
                index = i;
            }
        if (index != -1)
            this.board.removeMonster(index);
    }

    protected int getBestMonsterCard(ArrayList<Card> cards) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < cards.size(); i++)
            if (cards.get(i).getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) cards.get(i);
                if (monster.getLevel() > maximum) {
                    if (monster.getLevel() > 6 && !canSpecialSummonLevel2())
                        continue;
                    if (monster.getLevel() < 7 && monster.getLevel() > 4 && !canSpecialSummonLevel1())
                        continue;
                    maximum = monster.getLevel();
                    index = i;
                }
            }

        return index;

    }

    protected int getBestMonsterToAttack(ArrayList<MonsterCard> monsters) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < monsters.size(); i++)
            if (!monsters.get(i).getHasAttacked() && monsters.get(i).getMode() == Mode.ATTACK
                    && monsters.get(i).getAttackPoint() > maximum) {
                maximum = monsters.get(i).getAttackPoint();
                index = i;
            }

        return index;
    }

    protected int getBestDarkMonster(ArrayList<MonsterCard> monsters) {
        int maximum = 0;
        int index = -1;
        for (int i = 0; i < monsters.size(); i++)
            if (monsters.get(i).getAttribute() == Attribute.DARK && monsters.get(i).getAttackPoint() > maximum) {
                maximum = monsters.get(i).getAttackPoint();
                index = i;
            }

        return index;
    }

    protected boolean isThereASetWithValueEqualToRitualLevel(ArrayList<MonsterCard> cards, int level) {
        if (level == 0)
            return true;
        if (cards.size() == 0)
            return false;

        for (int i = 0; i < cards.size(); i++) {
            cards.remove(i);
            if (cards.get(i).getLevel() <= level)
                return isThereASetWithValueEqualToRitualLevel(cards, level - cards.get(i).getLevel());
        }

        return false;
    }

    protected ReasonableLevel isReasonableToAttack(MonsterCard aiMonster, MonsterCard opponentMonster) {
        if (opponentMonster.getMode() == Mode.ATTACK && aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (opponentMonster.getMode() == Mode.DEFENSE) {
            /*------that is based on the chance that a card with a certain
            attack point could be able to destroy a set (DEFENSED) card------*/
            int attackChance = (int) (Math.random() * 100);
            if (aiMonster.getAttackPoint() > 3400)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 3000 && attackChance > 1)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 2500 && attackChance > 11)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 2000 && attackChance > 23)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 1500 && attackChance > 33)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 1000 && attackChance > 65)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 500 && attackChance > 90)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            if (aiMonster.getAttackPoint() > 0 && attackChance > 98)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            /*------Our HARD Bot takes Risk for Attacking a DEFENSED Card or not
                    but based on what its card attack point is------*/
            if (attackChance > 49)
                return ReasonableLevel.REASONABLE_FOR_EASY;
            /*------But EASY Bot Attacks without Caring about card attack point------*/
        }
        return ReasonableLevel.NOT_REASONABLE;
    }

    public int getOpponentMonsterIndexEasy(Player opponent) {
        int index = getBestMonsterToAttack(getAIMonsters());
        if (index != -1) {
            for (int i = 0; i < opponent.getPlayerBoard().getMonsterCards().size(); i++)
                if (isReasonableToAttack(getAIMonsters().get(index),
                        opponent.getPlayerBoard().getMonsterCards().get(i)) != ReasonableLevel.NOT_REASONABLE)
                    return i;
        }
        return -1;
    }

    public int getOpponentMonsterIndexHard(Player opponent) {
        int index = getBestMonsterToAttack(getAIMonsters());
        if (index != -1) {
            for (int i = 0; i < opponent.getPlayerBoard().getMonsterCards().size(); i++)
                if (isReasonableToAttack(getAIMonsters().get(index),
                        opponent.getPlayerBoard().getMonsterCards().get(i)) == ReasonableLevel.REASONABLE_FOR_HARD)
                    return i;
        }
        return -1;
    }

    protected ReasonableLevel isSpellReasonableToActive(String name, GamePhase phase) {
        switch (name) {
            case "Monster Reborn":
                ArrayList<Card> graveyardCards = new ArrayList<>();
                graveyardCards.addAll(this.board.getGraveyardCards());
                graveyardCards.addAll(opponent.getPlayerBoard().getGraveyardCards());
                return isReasonableToActiveMonsterReborn(graveyardCards);
            case "Terraforming":
                return isReasonableToActiveTerraforming();
            case "Pot of Greed":
                return isReasonableToActivePotOfGreed();
            case "Raigeki":
                return isReasonableToActiveRaigeki();
            case "Change of Heart":
                return isReasonableToActiveChangeOfHeart(phase);
            case "Swords of Revealing Light":
                return isReasonableToActiveSwordsOfRevealingLight();
            case "Harpie's Feather Duster":
                return isReasonableToActiveHarpiesFeatherDuster();
            case "Dark Hole":
                return isReasonableToActiveDarkHole();
            case "Supply Squad":
                return isReasonableToActiveSupplySquad();
            case "Spell Absorption":
                return ReasonableLevel.REASONABLE_FOR_HARD;
            case "Messenger of peace":
                return isReasonableToActiveMessengerOfPeace();
            case "Twin Twisters":
                return isReasonableToActiveTwinTwisters();
            case "Mystical space typhoon":
                return isReasonableToActiveMysticalSpaceTyphoon();
            case "Ring of defense":
                return isReasonableToActiveRingOfDefense();
            case "Yami":
                return isReasonableToActiveYami();
            case "Forest":
                return isReasonableToActiveForest();
            case "Closed Forest":
                return isReasonableToActiveClosedForest();
            case "Umiiruka":
                return isReasonableToActiveUmiiruka();
            case "Sword of dark destruction":
                return isReasonableToActiveSwordOfDarkDestruction();
            case "Black Pendant":
                return isReasonableToActiveBlackPendant();
            case "United We Stand":
                return isReasonableToActiveUnitedWeStand();
            case "Magnum Shield":
                return isReasonableToActiveMagnumShield();
            case "Advanced Ritual Art":
                return isReasonableToActiveAdvancedRitualArt();
            default:
                return ReasonableLevel.NOT_REASONABLE;
        }
    }

    protected ReasonableLevel isTrapReasonableToActive(String name, MonsterCard summoned, MonsterCard attacked) {
        return switch (name) {
            case "Trap Hole" -> isReasonableToActiveTrapHole(summoned);
            case "Mirror Force" -> isReasonableToActiveMirrorForce(attacked);
            case "Magic Cylinder" -> isReasonableToActiveMagicCylinder(attacked);
            case "Mind Crush" -> isReasonableToActiveMindCrush();
            case "Torrential Tribute" -> isReasonableToActiveTorrentialTribute(summoned);
            case "Time Seal" -> isReasonableToActiveTimeSeal();
            case "Negate Attack" -> isReasonableToActiveNegateAttack(attacked);
            case "Solemn Warning" -> isReasonableToActiveSolemnWarning(summoned);
            case "Magic Jamamer" -> isReasonableToActiveMagicJamamer();
            case "Call of The Haunted" -> isReasonableToActiveCallOfTheHaunted();
            case "Vanity's Emptiness" -> isReasonableToActiveVanitysEmptiness();
            case "Wall of Revealing Light" -> isReasonableToActiveWallOfRevealingLight();
            default -> ReasonableLevel.NOT_REASONABLE;
        };
    }

    public void activeSpellTrap(int index) {

    }

    public void summonSpellTrapIfCan() {
        int indexTrap = getTrapCard(this.board.getHandCards());
        int indexSpell = getSpellCard(this.board.getHandCards());
        if (indexTrap != -1 && this.board.hasSpellTrapZoneSpace()) {
            this.board.getHandCards().get(indexTrap).setIsHidden(true);
            this.board.summonOrSetSpellAndTrap(indexTrap);
            if (indexSpell != -1 && this.board.hasSpellTrapZoneSpace()) {
                this.board.getHandCards().get(indexSpell).setIsHidden(true);
                this.board.summonOrSetSpellAndTrap(indexSpell);
            }
        }
    }

    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/
    /*------spell & trap checking------*/

    public ReasonableLevel isReasonableToActiveMonsterReborn(ArrayList<Card> graveyardCards) {
        if (!canSpecialSummonLevel1() && !canSpecialSummonLevel2())
            return ReasonableLevel.NOT_REASONABLE;
        if (canSpecialSummonLevel2() && getBestSpecialMonsterIndex(graveyardCards, true) != -1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (getBestSpecialMonsterIndex(graveyardCards, false) != -1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveTerraforming() {
        ArrayList<Card> aiMainDeck = this.board.getDeck().getMainDeck();
        for (Card card : aiMainDeck)
            if (card.getCardType() == CardType.SPELL) {
                SpellTrapCard spell = (SpellTrapCard) card;
                if (spell.getIcon() == Icon.FIELD)
                    switch (aiMainDeck.size()) {
                        case 0:
                        case 1:
                            return ReasonableLevel.NOT_REASONABLE;
                        case 2:
                            int counter = 0;
                            ArrayList<MonsterCard> aiMonsters = getAIMonsters();
                            ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
                            switch (spell.getName()) {
                                case "Yami" -> {
                                    for (MonsterCard monster : aiMonsters) {
                                        if (monster.getMonsterType() == MonsterType.FIEND
                                                || monster.getMonsterType() == MonsterType.SPELL_CASTER)
                                            counter++;
                                        if (monster.getMonsterType() == MonsterType.FAIRY)
                                            counter--;
                                    }
                                    for (MonsterCard monster : opponentMonsters) {
                                        if (monster.getMonsterType() == MonsterType.FIEND
                                                || monster.getMonsterType() == MonsterType.SPELL_CASTER)
                                            counter--;
                                        if (monster.getMonsterType() == MonsterType.FAIRY)
                                            counter++;
                                    }
                                    if (counter * 200 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                }
                                case "Forest" -> {
                                    for (MonsterCard monster : aiMonsters)
                                        if (monster.getMonsterType() == MonsterType.INSECT
                                                || monster.getMonsterType() == MonsterType.BEAST
                                                || monster.getMonsterType() == MonsterType.BEAST_WARRIOR)
                                            counter++;
                                    for (MonsterCard monster : opponentMonsters)
                                        if (monster.getMonsterType() == MonsterType.INSECT
                                                || monster.getMonsterType() == MonsterType.BEAST
                                                || monster.getMonsterType() == MonsterType.BEAST_WARRIOR)
                                            counter--;
                                    if (counter * 200 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                }
                                case "Closed Forest" -> {
                                    int monsterCounter = 0;
                                    for (Card graveyardCard : this.board.getGraveyardCards())
                                        if (graveyardCard.getCardType() == CardType.MONSTER)
                                            monsterCounter++;
                                    for (MonsterCard monster : aiMonsters)
                                        if (monster.getMonsterType() == MonsterType.BEAST)
                                            counter++;
                                    if (counter * monsterCounter * 100 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                }
                                case "Umiiruka" -> {
                                    for (MonsterCard monster : aiMonsters)
                                        if (monster.getAttribute() == Attribute.WATER)
                                            counter++;
                                    for (MonsterCard monster : opponentMonsters)
                                        if (monster.getAttribute() == Attribute.WATER)
                                            counter--;
                                    if (counter * 500 > opponent.getPlayerBoard().getLifePoints())
                                        return ReasonableLevel.REASONABLE_FOR_HARD;
                                    return ReasonableLevel.REASONABLE_FOR_EASY;
                                }
                            }
                        default:
                            return ReasonableLevel.REASONABLE_FOR_HARD;
                    }
            }

        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActivePotOfGreed() {
        return switch (this.board.getDeck().getMainDeck().size()) {
            case 0, 1, 2 -> ReasonableLevel.NOT_REASONABLE;
            case 3, 4 -> ReasonableLevel.REASONABLE_FOR_EASY;
            default -> ReasonableLevel.REASONABLE_FOR_HARD;
        };
    }

    public ReasonableLevel isReasonableToActiveRaigeki() {
        if (getOpponentMonsters().size() > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveChangeOfHeart(GamePhase phase) {
        if (getOpponentMonsters().size() == 0)
            return ReasonableLevel.NOT_REASONABLE;
        if (phase == GamePhase.BATTLE)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveSwordsOfRevealingLight() {
        boolean hasHiddenCard = false;
        int betterCounter = 0;
        HashMap<MonsterCard, Boolean> checkedCards = new HashMap<>();
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard monster : aiMonsters)
            checkedCards.put(monster, false);
        for (MonsterCard monster : opponentMonsters)
            if (monster.getIsHidden()) {
                hasHiddenCard = true;
                break;
            }
        nextOpponentCard: for (MonsterCard opponentMonster : opponentMonsters)
            for (MonsterCard aiMonster : aiMonsters)
                if (!checkedCards.get(aiMonster) && opponentMonster.getAttackPoint() > aiMonster.getAttackPoint()
                        && !opponentMonster.getIsHidden()) {
                    betterCounter++;
                    checkedCards.put(aiMonster, true);
                    continue nextOpponentCard;
                }

        if (betterCounter > 1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (hasHiddenCard || betterCounter == 1)
            return ReasonableLevel.REASONABLE_FOR_EASY;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveHarpiesFeatherDuster() {
        if (getOpponentSpellTraps().size() == 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_HARD;
    }

    public ReasonableLevel isReasonableToActiveDarkHole() {
        int heaviestDamageToOpponent = 0;
        int heaviestDamageToSelf = 0;
        int totalAIDamage = 0;
        int totalOpponentDamage = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters)
            for (MonsterCard opponentMonster : opponentMonsters) {
                int difference = aiMonster.getAttackPoint() - opponentMonster.getAttackPoint();
                if (difference > heaviestDamageToOpponent)
                    heaviestDamageToOpponent = difference;
                else if (difference < heaviestDamageToSelf)
                    heaviestDamageToSelf = difference;
            }

        for (MonsterCard monster : aiMonsters)
            totalAIDamage += monster.getAttackPoint();
        for (MonsterCard monster : opponentMonsters)
            totalOpponentDamage += monster.getAttackPoint();

        if (heaviestDamageToSelf * (-1) > this.board.getLifePoints())
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (heaviestDamageToOpponent > opponent.getPlayerBoard().getLifePoints())
            return ReasonableLevel.NOT_REASONABLE;
        if (totalAIDamage > totalOpponentDamage)
            return ReasonableLevel.NOT_REASONABLE;
        if (totalAIDamage < totalOpponentDamage)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveSupplySquad() {
        return switch (this.board.getDeck().getMainDeck().size()) {
            case 0, 1 -> ReasonableLevel.NOT_REASONABLE;
            default -> ReasonableLevel.REASONABLE_FOR_HARD;
        };
    }

    public ReasonableLevel isReasonableToActiveMessengerOfPeace() {
        if (iskeepingMessengerOfPeaceReasonable() == ReasonableLevel.REASONABLE_FOR_HARD)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveTwinTwisters() {
        int opponentSpellTrapCards = getOpponentSpellTraps().size();
        if (opponentSpellTrapCards == 0)
            return ReasonableLevel.NOT_REASONABLE;
        if (opponentSpellTrapCards == 1)
            return ReasonableLevel.REASONABLE_FOR_EASY;
        return ReasonableLevel.REASONABLE_FOR_HARD;
    }

    public ReasonableLevel isReasonableToActiveMysticalSpaceTyphoon() {
        if (getOpponentSpellTraps().size() == 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_HARD;
    }

    public ReasonableLevel isReasonableToActiveRingOfDefense() {
        ArrayList<SpellTrapCard> aiSpellTrapCards = getAISpellTraps();
        ArrayList<SpellTrapCard> opponentSpellTrapCards = getOpponentSpellTraps();

        for (SpellTrapCard aiTrap : aiSpellTrapCards)
            if (aiTrap.getCardType() == CardType.TRAP)
                if (aiTrap.getName().equals("Solemn Warning") || aiTrap.getName().equals("Wall of Revealing Light"))
                    return ReasonableLevel.REASONABLE_FOR_HARD;

        for (SpellTrapCard opponentTrap : opponentSpellTrapCards)
            if (opponentTrap.getCardType() == CardType.TRAP && opponentTrap.getName().equals("Magic Cylinder"))
                return ReasonableLevel.REASONABLE_FOR_HARD;

        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveYami() {
        int counter = 0;
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        boolean isChanged = false;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters) {
            int aiMonsterPowerBeforeChange = aiMonster.getAttackPoint();
            int aiMonsterPowerAfterChange = 0;
            if (aiMonster.getMonsterType() == MonsterType.FIEND
                    || aiMonster.getMonsterType() == MonsterType.SPELL_CASTER) {
                aiMonsterPowerAfterChange = aiMonsterPowerBeforeChange + 200;
                counter++;
            } else if (aiMonster.getMonsterType() == MonsterType.FAIRY) {
                aiMonsterPowerAfterChange = aiMonsterPowerBeforeChange - 200;
                counter--;
            }
            for (MonsterCard opponentMonster : opponentMonsters) {
                int opponentMonsterPowerBeforeChange = opponentMonster.getAttackPoint();
                int opponentMonsterPowerAfterChange = 0;
                if (opponentMonster.getMonsterType() == MonsterType.FIEND
                        || opponentMonster.getMonsterType() == MonsterType.SPELL_CASTER) {
                    opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange + 200;
                    if (!isChanged)
                        counter--;
                } else if (opponentMonster.getMonsterType() == MonsterType.FAIRY) {
                    opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange - 200;
                    if (!isChanged)
                        counter++;
                }
                if (aiMonsterPowerBeforeChange > opponentMonsterPowerBeforeChange)
                    winningBeforeUsage++;
                if (aiMonsterPowerAfterChange > opponentMonsterPowerAfterChange)
                    winningAfterUsage++;
            }
            isChanged = true;

        }
        if (winningAfterUsage > winningBeforeUsage && counter > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (winningAfterUsage <= winningBeforeUsage && counter <= 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_EASY;

    }

    public ReasonableLevel isReasonableToActiveForest() {
        int counter = 0;
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        boolean isChanged = false;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters) {
            int aiMonsterPowerBeforeChange = aiMonster.getAttackPoint();
            int aiMonsterPowerAfterChange = 0;
            if (aiMonster.getMonsterType() == MonsterType.INSECT || aiMonster.getMonsterType() == MonsterType.BEAST
                    || aiMonster.getMonsterType() == MonsterType.BEAST_WARRIOR) {
                aiMonsterPowerAfterChange = aiMonsterPowerBeforeChange + 200;
                counter++;
            }
            for (MonsterCard opponentMonster : opponentMonsters) {
                int opponentMonsterPowerBeforeChange = opponentMonster.getAttackPoint();
                int opponentMonsterPowerAfterChange = 0;
                if (opponentMonster.getMonsterType() == MonsterType.INSECT
                        || opponentMonster.getMonsterType() == MonsterType.BEAST
                        || opponentMonster.getMonsterType() == MonsterType.BEAST_WARRIOR) {
                    opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange + 200;
                    if (!isChanged)
                        counter--;
                }
                if (aiMonsterPowerBeforeChange > opponentMonsterPowerBeforeChange)
                    winningBeforeUsage++;
                if (aiMonsterPowerAfterChange > opponentMonsterPowerAfterChange)
                    winningAfterUsage++;
            }
            isChanged = true;

        }
        if (winningAfterUsage > winningBeforeUsage && counter > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (winningAfterUsage <= winningBeforeUsage && counter <= 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveClosedForest() {
        int amount = 0;
        int counter = 0;
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        boolean isChanged = false;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        ArrayList<Card> graveyard = this.board.getGraveyardCards();
        for (Card card : graveyard)
            if (card.getCardType() == CardType.MONSTER)
                amount += 100;
        for (MonsterCard aiMonster : aiMonsters) {
            int aiMonsterPowerBeforeChange = aiMonster.getAttackPoint();
            int aiMonsterPowerAfterChange = 0;
            if (aiMonster.getMonsterType() == MonsterType.BEAST) {
                aiMonsterPowerAfterChange = aiMonsterPowerBeforeChange + amount;
                counter++;
            }
            for (MonsterCard opponentMonster : opponentMonsters) {
                int opponentMonsterPowerBeforeChange = opponentMonster.getAttackPoint();
                int opponentMonsterPowerAfterChange = 0;
                if (opponentMonster.getMonsterType() == MonsterType.BEAST) {
                    opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange + amount;
                    if (!isChanged)
                        counter--;
                }
                if (aiMonsterPowerBeforeChange > opponentMonsterPowerBeforeChange)
                    winningBeforeUsage++;
                if (aiMonsterPowerAfterChange > opponentMonsterPowerAfterChange)
                    winningAfterUsage++;
            }
            isChanged = true;

        }
        if (winningAfterUsage > winningBeforeUsage && counter > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (winningAfterUsage <= winningBeforeUsage && counter <= 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveUmiiruka() {
        int counter = 0;
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        boolean isChanged = false;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters) {
            int aiMonsterPowerBeforeChange = aiMonster.getAttackPoint();
            int aiMonsterPowerAfterChange = 0;
            if (aiMonster.getAttribute() == Attribute.WATER) {
                aiMonsterPowerAfterChange = aiMonsterPowerBeforeChange + 500;
                counter++;
            }
            for (MonsterCard opponentMonster : opponentMonsters) {
                int opponentMonsterPowerBeforeChange;
                if (opponentMonster.getIsHidden())
                    opponentMonsterPowerBeforeChange = opponentMonster.getDefensePoint();
                else
                    opponentMonsterPowerBeforeChange = opponentMonster.getAttackPoint();
                int opponentMonsterPowerAfterChange = 0;
                if (opponentMonster.getAttribute() == Attribute.WATER) {
                    if (opponentMonster.getIsHidden()) {
                        opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange - 400;
                        if (!isChanged)
                            counter++;
                    } else {
                        opponentMonsterPowerAfterChange = opponentMonsterPowerBeforeChange + 500;
                        if (!isChanged)
                            counter--;
                    }
                }
                if (aiMonsterPowerBeforeChange > opponentMonsterPowerBeforeChange)
                    winningBeforeUsage++;
                if (aiMonsterPowerAfterChange > opponentMonsterPowerAfterChange)
                    winningAfterUsage++;
            }
            isChanged = true;

        }
        if (winningAfterUsage > winningBeforeUsage && counter > 0)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (winningAfterUsage <= winningBeforeUsage && counter <= 0)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveSwordOfDarkDestruction() {
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters)
            if (aiMonster.getAttribute() == Attribute.DARK)
                for (MonsterCard opponentMonster : opponentMonsters) {
                    if (opponentMonster.getIsHidden()) {
                        if (aiMonster.getAttackPoint() > opponentMonster.getDefensePoint())
                            winningBeforeUsage++;
                        if (aiMonster.getAttackPoint() + 400 > opponentMonster.getDefensePoint())
                            winningAfterUsage++;
                    } else {
                        if (aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
                            winningBeforeUsage++;
                        if (aiMonster.getAttackPoint() + 400 > opponentMonster.getAttackPoint())
                            winningAfterUsage++;
                    }
                }
        if (winningAfterUsage == 0 && winningBeforeUsage == 0)
            return ReasonableLevel.NOT_REASONABLE;
        if (winningAfterUsage > winningBeforeUsage)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveBlackPendant() {
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters)
            for (MonsterCard opponentMonster : opponentMonsters) {
                if (opponentMonster.getIsHidden()) {
                    if (aiMonster.getAttackPoint() > opponentMonster.getDefensePoint())
                        winningBeforeUsage++;
                    if (aiMonster.getAttackPoint() + 500 > opponentMonster.getDefensePoint())
                        winningAfterUsage++;
                } else {
                    if (aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
                        winningBeforeUsage++;
                    if (aiMonster.getAttackPoint() + 500 > opponentMonster.getAttackPoint())
                        winningAfterUsage++;
                }
            }
        if (winningAfterUsage > winningBeforeUsage)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveUnitedWeStand() {
        int amount = 0;
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();

        for (MonsterCard monster : aiMonsters)
            if (!monster.getIsHidden())
                amount += 800;
        for (MonsterCard aiMonster : aiMonsters)
            for (MonsterCard opponentMonster : opponentMonsters) {
                if (opponentMonster.getIsHidden()) {
                    if (aiMonster.getAttackPoint() > opponentMonster.getDefensePoint())
                        winningBeforeUsage++;
                    if (aiMonster.getAttackPoint() + amount > opponentMonster.getDefensePoint())
                        winningAfterUsage++;
                } else {
                    if (aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
                        winningBeforeUsage++;
                    if (aiMonster.getAttackPoint() + amount > opponentMonster.getAttackPoint())
                        winningAfterUsage++;
                }
            }
        if (winningAfterUsage > winningBeforeUsage)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveMagnumShield() {
        int winningBeforeUsage = 0;
        int winningAfterUsage = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters) {
            int amount = aiMonster.getAttackPoint() + aiMonster.getDefensePoint();
            for (MonsterCard opponentMonster : opponentMonsters) {
                if (opponentMonster.getIsHidden()) {
                    if (aiMonster.getAttackPoint() > opponentMonster.getDefensePoint())
                        winningBeforeUsage++;
                    if (amount > opponentMonster.getDefensePoint())
                        winningAfterUsage++;
                } else {
                    if (aiMonster.getAttackPoint() > opponentMonster.getAttackPoint())
                        winningBeforeUsage++;
                    if (amount > opponentMonster.getAttackPoint())
                        winningAfterUsage++;
                }
            }
        }
        if (winningAfterUsage > winningBeforeUsage)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveAdvancedRitualArt() {
        MonsterCard ritualMonster = null;
        ArrayList<Card> aiHand = this.board.getHandCards();
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> aiMonstersToSacrifice = new ArrayList<>();
        for (Card card : aiHand)
            if (card.getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) card;
                if (monster.getTrait() != Trait.RITUAL)
                    aiMonstersToSacrifice.add(monster);
                else
                    ritualMonster = monster;
            }
        aiMonstersToSacrifice.addAll(aiMonsters);

        if (ritualMonster == null)
            return ReasonableLevel.NOT_REASONABLE;
        if (isThereASetWithValueEqualToRitualLevel(aiMonstersToSacrifice, ritualMonster.getLevel()))
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;

    }

    public ReasonableLevel isReasonableToActiveTrapHole(MonsterCard summonedMonster) {
        if (summonedMonster == null)
            return ReasonableLevel.NOT_REASONABLE;
        if (summonedMonster.getAttackPoint() > 1600)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (summonedMonster.getAttackPoint() > 1000)
            return ReasonableLevel.REASONABLE_FOR_EASY;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveMirrorForce(MonsterCard attacked) {
        if (attacked == null)
            return ReasonableLevel.NOT_REASONABLE;
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        int faceUpMonsters = 0;
        for (MonsterCard monster : opponentMonsters)
            if (!monster.getIsHidden())
                faceUpMonsters++;

        boolean loseInWar = false;
        for (MonsterCard monster : aiMonsters) {
            if (monster.getIsHidden() && attacked.getAttackPoint() > monster.getDefensePoint()) {
                loseInWar = true;
                break;
            }
            if (!monster.getIsHidden() && attacked.getAttackPoint() > monster.getAttackPoint()) {
                loseInWar = true;
                break;
            }
        }

        if (faceUpMonsters > 1)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (loseInWar)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveMagicCylinder(MonsterCard attacked) {
        if (attacked == null)
            return ReasonableLevel.NOT_REASONABLE;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        boolean canDestroyByAttack = false;
        for (MonsterCard monster : aiMonsters) {
            if (monster.getIsHidden()
                    && attacked.getAttackPoint() - monster.getDefensePoint() >= this.board.getLifePoints()) {
                canDestroyByAttack = true;
                break;
            }
            if (!monster.getIsHidden()
                    && attacked.getAttackPoint() - monster.getAttackPoint() >= this.board.getLifePoints()) {
                canDestroyByAttack = true;
                break;
            }
        }
        if (attacked.getAttackPoint() > 1000)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        if (canDestroyByAttack)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

    public ReasonableLevel isReasonableToActiveMindCrush() {
        /*------Should Think about It------*/
    }

    public ReasonableLevel isReasonableToActiveTorrentialTribute(MonsterCard summoned) {
        if (summoned == null)
            return ReasonableLevel.NOT_REASONABLE;
        int aiMonstersPower = 0;
        int opponentMonstersPower = 0;
        int aiWinners = 0;
        int opponentWinners = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();

        for (MonsterCard monster : aiMonsters) {
            if (monster.getIsHidden())
                aiMonstersPower += monster.getDefensePoint();
            else
                aiMonstersPower += monster.getAttackPoint();
        }
        for (MonsterCard monster : opponentMonsters) {
            if (monster.getIsHidden())
                opponentMonstersPower += monster.getDefensePoint();
            else
                opponentMonstersPower += monster.getAttackPoint();
        }

        for (MonsterCard aiMonster : aiMonsters) {
            int aiPower;
            if (aiMonster.getIsHidden())
                aiPower = aiMonster.getDefensePoint();
            else
                aiPower = aiMonster.getAttackPoint();
            for (MonsterCard opponentMonster : opponentMonsters) {
                int opponentPower;
                if (opponentMonster.getIsHidden())
                    opponentPower = opponentMonster.getDefensePoint();
                else
                    opponentPower = opponentMonster.getAttackPoint();

                if (opponentPower < aiPower)
                    aiWinners++;
                else
                    opponentWinners++;
            }
        }

        if (aiMonstersPower > opponentMonstersPower && aiWinners > opponentWinners)
            return ReasonableLevel.NOT_REASONABLE;
        if (aiMonstersPower <= opponentMonstersPower && aiWinners <= opponentWinners)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;

    }

    public ReasonableLevel isReasonableToActiveTimeSeal() {
        return switch (opponent.getPlayerBoard().getDeck().getMainDeck().size()) {
            case 0 -> ReasonableLevel.NOT_REASONABLE;
            case 1 -> ReasonableLevel.REASONABLE_FOR_EASY;
            default -> ReasonableLevel.REASONABLE_FOR_HARD;
        };
    }

    public ReasonableLevel isReasonableToActiveNegateAttack(MonsterCard attacker) {
        if (attacker == null)
            return ReasonableLevel.NOT_REASONABLE;
        return ReasonableLevel.REASONABLE_FOR_HARD;
    }

    public ReasonableLevel isReasonableToActiveSolemnWarning(MonsterCard summoned) {
        if (summoned == null)
            return ReasonableLevel.NOT_REASONABLE;
        if (summoned.getLevel() > 4) {
            if (summoned.getAttackPoint() > 2000)
                return ReasonableLevel.REASONABLE_FOR_HARD;
            return ReasonableLevel.REASONABLE_FOR_EASY;
        }
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveMagicJamamer() {
        /*------Shoud think About It------*/
    }

    public ReasonableLevel isReasonableToActiveCallOfTheHaunted() {
        boolean hasAnySpecialMonster = false;

        ArrayList<Card> aiGraveyard = this.board.getGraveyardCards();

        for (Card card : aiGraveyard)
            if (card.getCardType() == CardType.MONSTER) {
                MonsterCard monster = (MonsterCard) card;
                if (monster.getLevel() > 4) {
                    hasAnySpecialMonster = true;
                    break;
                }
            }

        if (hasAnySpecialMonster && getAIMonsters().size() < 5)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.NOT_REASONABLE;
    }

    public ReasonableLevel isReasonableToActiveVanitysEmptiness() {
    }

    public ReasonableLevel isReasonableToActiveWallOfRevealingLight() {
    }

    /*------Some Exceptions------*/
    /*------Some Exceptions------*/
    /*------Some Exceptions------*/
    /*------Some Exceptions------*/
    /*------Some Exceptions------*/

    public ReasonableLevel iskeepingMessengerOfPeaceReasonable() {
        int aiMonstersCounter = 0;
        int opponentMonsterCounter = 0;
        ArrayList<MonsterCard> aiMonsters = getAIMonsters();
        ArrayList<MonsterCard> opponentMonsters = getOpponentMonsters();
        for (MonsterCard aiMonster : aiMonsters)
            if (aiMonster.getAttackPoint() > 1500)
                aiMonstersCounter++;
        for (MonsterCard opponentMonster : opponentMonsters)
            if (opponentMonster.getAttackPoint() > 1500)
                opponentMonsterCounter++;

        if (this.board.getLifePoints() <= 100)
            return ReasonableLevel.NOT_REASONABLE;
        if (opponentMonsterCounter > aiMonstersCounter)
            return ReasonableLevel.REASONABLE_FOR_HARD;
        return ReasonableLevel.REASONABLE_FOR_EASY;
    }

}