package controller.GUI;

import controller.menucontroller.LoginMenuController;
import controller.menucontroller.ProfileMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.GUI.AlertBox;
import view.StatusEnum;

import java.io.File;
import java.io.IOException;

public class ProfileMenuControllerGUI {


    public TextField change_nickname;
    public TextField new_pass;
    public TextField old_pass;
    public TextField change_user;




    public static SceneController sceneController = new SceneController();



    public void userInfoScene(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/userInfo.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
        Label username = new Label();
        username.setText(LoginMenuController.currentUser.getUserName());
        username.setLayoutY(80);
        username.setLayoutX(300);
        username.setFont(new Font(29));
        username.setVisible(true);
        Label nickname = new Label();
        nickname.setText(LoginMenuController.currentUser.getNickName());
        nickname.setLayoutY(150);
        nickname.setLayoutX(300);
        nickname.setFont(new Font(29));
        nickname.setVisible(true);
        Label score = new Label();
        score.setText(String.valueOf(LoginMenuController.currentUser.getScore()));
        score.setLayoutY(220);
        score.setLayoutX(300);
        score.setFont(new Font(29));
        score.setVisible(true);
        Label money = new Label();
        money.setText(String.valueOf(LoginMenuController.currentUser.getMoney()));
        money.setLayoutY(290);
        money.setLayoutX(300);
        money.setFont(new Font(29));
        money.setVisible(true);
        Image image = new Image(LoginMenuController.currentUser.getAvatar());
        ImageView avatar = new ImageView(image);
        avatar.setLayoutX(500);
        avatar.setLayoutY(50);
        avatar.setFitWidth(233);
        avatar.setFitHeight(310);
        pane.getChildren().add(username);
        pane.getChildren().add(nickname);
        pane.getChildren().add(score);
        pane.getChildren().add(money);
        pane.getChildren().add(avatar);


    }//Todo


    public void changeUserScene(ActionEvent actionEvent) throws IOException {
        sceneController.switchScene("/fxml/changeUser.fxml",actionEvent);

    }

    public void changeAvatarScene(ActionEvent actionEvent) throws IOException {
        sceneController.switchScene("/fxml/changeAvatar.fxml",actionEvent);
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
           case "one":LoginMenuController.currentUser.setAvatar("./image/Avatars/YamiYugi-DULI.png");break;
           case "two":LoginMenuController.currentUser.setAvatar("./image/Avatars/YamiMarik-DULI.png");break;
           case "three":LoginMenuController.currentUser.setAvatar("./image/Avatars/WeevilUnderwood-DULI.png");break;
           case "four":LoginMenuController.currentUser.setAvatar("./image/Avatars/sss.png");break;
           case "five":LoginMenuController.currentUser.setAvatar("./image/Avatars/SetoKaiba-DL.png");break;
           case "six":LoginMenuController.currentUser.setAvatar("./image/Avatars/RexRaptor-DULI.png");break;
           case "seven":LoginMenuController.currentUser.setAvatar("./image/Avatars/MaximillionPegasus-DULI.png");break;
           case "eight":LoginMenuController.currentUser.setAvatar("./image/Avatars/MakoTsunami-DULI.png");break;



           case "nine":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds.png");break;
           case "ten":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds7.png");break;
           case "eleven":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds13.png");break;
           case "twelve":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds21.png");break;
           case "thirteen":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds24.png");break;
           case "fourteen":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds33.png");break;
           case "fifteen":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds35.png");break;
           case "sisteen":LoginMenuController.currentUser.setAvatar("./image/Avatars/Chara002.dds20.png");break;
       }
       AlertBox.display("Profile changed successfully!");
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
        String musicFile = "./src/main/resources/sound/bt.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        ImageView imageView = ((ImageView)event.getSource());
        double w = imageView.getFitWidth();
        double h = imageView.getFitHeight();
        imageView.setFitHeight(h+5);
        imageView.setFitWidth(w+5);


    }

    public void returnToStart(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void returnNormalBt(MouseEvent event) {
        ImageView imageView = ((ImageView)event.getSource());
        double w = imageView.getFitWidth();
        double h = imageView.getFitHeight();
        imageView.setFitHeight(h-5);
        imageView.setFitWidth(w-5);
    }

    public void returnToProfileMneu(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile_menu.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }
}
