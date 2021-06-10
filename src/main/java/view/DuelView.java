package view;

import controller.duel.SettingController;
import controller.duel.ShowController;
import controller.duel.SummonController;

import java.util.regex.Matcher;

public class DuelView {

    boolean isCommandValid;
    SummonController summonController = new SummonController();
    SettingController settingController = new SettingController();
    ShowController showController = new ShowController();

    public void run(String command) {
        //System.out.println(); TODO print board from controller
        isCommandValid = false;
        //Selection
        selectMyMonster(command);
        selectRivalMonster(command);
        selectMySpell(command);
        selectRivalSpell(command);
        selectMyFieldCard(command);
        selectRivalFieldCard(command);
        selectMyHand(command);
        deSelect(command);
        //Summon
        summon(command);
        tributeSummon(command);
        flipSummon(command);
        specialSummon(command);
        //setting
        set(command);
        setPosition(command);
        //attack
        attack(command);
        directAttack(command);
        //effects
        activate(command);
        //phase
        switchPhase(command);
        //show
        showGraveyard(command);
        showCard(command);
        if (!isCommandValid)
            System.out.println("invalid command!");
    }

    private void showCard(String command) {
        if (!Regex.getMatcher(command, Regex.SHOW_SELECTED_CARD).find())
            return;
        isCommandValid = true;
        System.out.println(showController.showSelectedCard());
    }

    private void showGraveyard(String command) {
        if (!Regex.getMatcher(command, Regex.SHOW_GRAVEYARD).find())
            return;
        isCommandValid = true;
        System.out.println(showController.showGraveyard());
    }

    private void selectMyMonster(String command) {
        if (!Regex.getMatcher(command, Regex.SELECT_OWN_MONSTER).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call function from controller
    }

    private void selectRivalMonster(String command) {
        if (Regex.getMatcher(command, Regex.SELECT_OPPONENT_MONSTER_1).find()
                || Regex.getMatcher(command, Regex.SELECT_OPPONENT_MONSTER_2).find())
            isCommandValid = true;
        else return;
        System.out.println(); //TODO call function from controller
    }

    private void selectMySpell(String command) {
        if (!Regex.getMatcher(command, Regex.SELECT_OWN_SPELL_CARD).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call function form controller
    }

    private void selectRivalSpell(String command) {
        if (Regex.getMatcher(command, Regex.SELECT_OPPONENT_SPELL_CARD_1).find()
                || Regex.getMatcher(command, Regex.SELECT_OPPONENT_SPELL_CARD_2).find())
            isCommandValid = true;
        else return;
        System.out.println(); //TODO call function from controller
    }

    private void selectMyFieldCard(String command) {
        if (!Regex.getMatcher(command, Regex.SELECT_OWN_FIELD).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call function from controller
    }

    private void selectRivalFieldCard(String command) {
        if (Regex.getMatcher(command, Regex.SELECT_OPPONENT_FIELD_1).find()
        || Regex.getMatcher(command, Regex.SELECT_OPPONENT_FIELD_2).find())
            isCommandValid = true;
        else return;
        System.out.println(); //TODO call function from controller
    }

    private void selectMyHand(String command) {
        if (!Regex.getMatcher(command, Regex.SELECT_HAND_CARD).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call function from controller
    }

    private void deSelect(String command) {
        if (!Regex.getMatcher(command, Regex.DESELECT_CARD).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call function from controller
    }

    private void summon(String command) {
        if (!Regex.getMatcher(command, Regex.SUMMON).find())
            return;
        isCommandValid = true;
        System.out.println(summonController.summon());
    }

    private void tributeSummon(String command) {
        Matcher matcher = Regex.getMatcher(command, Regex.TRIBUTE_SUMMON);
        if (!matcher.find())
            return;
        isCommandValid = true;
        System.out.println(summonController.tributeSummon(matcher.group(1)));
    }

    private void flipSummon(String command) {
        if (!Regex.getMatcher(command, Regex.SET).find())
            return;
        isCommandValid = true;
        System.out.println(summonController.flipSummon());
    }

    private void specialSummon(String command) {
    }

    private void set(String command) {
        if (!Regex.getMatcher(command, Regex.SET).find())
            return;
        isCommandValid = true;
        System.out.println(settingController.set());
    }

    private void setPosition(String command) {
        Matcher matcher = Regex.getMatcher(command, Regex.SET_CARD_POSITION);
        if (!matcher.find())
            return;
        isCommandValid = true;
        System.out.println(settingController.setPosition(matcher.group(2)));
    }

    private void attack(String command) {
        Matcher matcher = Regex.getMatcher(command, Regex.ATTACK_MONSTER);
        if (!matcher.find())
            return;
        isCommandValid = true;
        System.out.println(); // TODO call
    }

    private void directAttack(String command) {
        if (!Regex.getMatcher(command, Regex.ATTACK_DIRECT).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call
    }

    private void activate(String command) {
        if (!Regex.getMatcher(command, Regex.ACTIVATE_EFFECT).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call
    }

    private void switchPhase(String command) {
        if (!Regex.getMatcher(command, Regex.SWITCH_PHASE).find())
            return;
        isCommandValid = true;
        System.out.println(); //TODO call func from controller
    }
}
