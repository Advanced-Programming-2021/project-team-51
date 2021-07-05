package controller.GUI;

import controller.menucontroller.LoginMenuController;
import controller.menucontroller.ProfileMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.GUI.AlertBox;
import view.StatusEnum;

import java.io.IOException;

public class ProfileMenuControllerGUI {

    public TextField username_field;
    public TextField nickname_field;
    public TextField score_field;
    public TextField money_field;
    public ImageView avatar;


    public TextField change_user;
    public TextField old_pass;
    public TextField new_pass;
    public TextField change_nickname;

    public void userInfoScene(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfo.fxml"));
        Pane pane = fxmlLoader.load();
        username_field.setText(LoginMenuController.currentUser.getUserName());
        nickname_field.setText(LoginMenuController.currentUser.getNickName());
        score_field.setText(String.valueOf(LoginMenuController.currentUser.getScore()));
        money_field.setText(String.valueOf(LoginMenuController.currentUser.getMoney()));
        Image image = new Image(LoginMenuController.currentUser.getAvatar());
        this.avatar = new ImageView(image);
        stage.setScene(new Scene(pane));
    }//Todo

    public void changeUserScene(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/changeUser.fxml",actionEvent);

    }

    public void changeAvatarScene(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/changeAvatar.fxml",actionEvent);
    }//Todo

    public void exit(MouseEvent event) {
        LoginMenuController.currentUser = null;
        LoginMenuController.isLoggedOn = false;
        System.exit(0);
    }

    public void returnToProfileMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void changeUser(ActionEvent actionEvent) {
        String name = change_user.getText();
        String res =  MainMenuControllerGUI.profileMenuController.changeUsername(name);
        if (name == null){
            AlertBox.display("Please fill the related field!");
        }
        else if (res.equals("user with username " + name + " already exists")){
            AlertBox.display("user with username " + name + " already exists");
        }
        else if (res.equals(StatusEnum.CHANGE_USERNAME_SUCCESSFULLY.getStatus())){
            AlertBox.display(res);
            change_user.clear();
        }

    }

    public void changePass(ActionEvent actionEvent) {
        String oldP = old_pass.getText();
        String newP = new_pass.getText();
        String res = MainMenuControllerGUI.profileMenuController.changePass(oldP,newP);
        if (oldP == null || newP == null){
            AlertBox.display("Please fill the related field!");
        }
        else if (res.equals(StatusEnum.CURRENT_PASSWORD_INVALIDITY.getStatus())){
            AlertBox.display(StatusEnum.CURRENT_PASSWORD_INVALIDITY.getStatus());
        }
        else if (res.equals(StatusEnum.ENTER_A_NEW_PASSWORD.getStatus())){
            AlertBox.display(StatusEnum.ENTER_A_NEW_PASSWORD.getStatus());
        }
        else if (res.equals(StatusEnum.CHANGE_PASSWORD_SUCCESSFULLY.getStatus())){
            AlertBox.display(StatusEnum.CHANGE_PASSWORD_SUCCESSFULLY.getStatus());
            old_pass.clear();
            new_pass.clear();
        }
    }

    public void changeNick(ActionEvent actionEvent) {
        String nick = change_nickname.getText();
        String res = MainMenuControllerGUI.profileMenuController.changeNickname(nick);
        if (nick == null){
            AlertBox.display("Please fill the related field!");
        }
        else if (res.equals("user with nickname " + nick + " already exists")){
            AlertBox.display("user with nickname " + nick + " already exists");
        }
        else if (res.equals(StatusEnum.CHANGE_NICKNAME_SUCCESSFULLY.getStatus())){
            AlertBox.display(StatusEnum.CHANGE_NICKNAME_SUCCESSFULLY.getStatus());
            change_nickname.clear();
        }
    }

    public void setAvatar(MouseEvent event) {
       String avatar = ((ImageView)event.getSource()).getId();
       switch (avatar){
           case "one":LoginMenuController.currentUser.setAvatar("/image/Avatars/YamiYugi-DULI.png");break;
           case "two":LoginMenuController.currentUser.setAvatar("/image/Avatars/YamiMarik-DULI.png");break;
           case "three":LoginMenuController.currentUser.setAvatar("/image/Avatars/WeevilUnderwood-DULI.png");break;
           case "four":LoginMenuController.currentUser.setAvatar("/image/Avatars/sss.png");break;
           case "five":LoginMenuController.currentUser.setAvatar("/image/Avatars/SetoKaiba-DL.png");break;
           case "six":LoginMenuController.currentUser.setAvatar("/image/Avatars/RexRaptor-DULI.png");break;
           case "seven":LoginMenuController.currentUser.setAvatar("/image/Avatars/MaximillionPegasus-DULI.png");break;
           case "eight":LoginMenuController.currentUser.setAvatar("/image/Avatars/MakoTsunami-DULI.png");break;

       }
    }

    public void nextAvatarMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/changeAvatar2.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void previousAvatarMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/changeAvatar.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void highlightBt(MouseEvent event) {
        ImageView imageView = ((ImageView)event.getSource());
        double w = imageView.getFitWidth();
        double h = imageView.getFitHeight();
        imageView.setFitHeight(h+2);
        imageView.setFitWidth(w+2);


    }
}
