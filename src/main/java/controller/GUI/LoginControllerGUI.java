package controller.GUI;




import controller.menucontroller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;
import view.GUI.AlertBox;
import view.StatusEnum;

import java.io.IOException;

public class LoginControllerGUI  {

    public TextField login_Username;
    public TextField loign_Password;
    public TextField register_username;
    public TextField register_nickname;
    public TextField register_pass;

    public void enterLoginMenu(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/login_menu.fxml",actionEvent);
    }

    public void login(ActionEvent actionEvent) throws IOException {
        String name = login_Username.getText();
        String pass = loign_Password.getText();
        String res = new LoginMenuController().loginUSer(name,pass);
        if(name ==null || pass == null){
            AlertBox.display("Please fill the empty fields!");
        }
        else if (res.equals("There is no user with username " + name)){
            String msg = "There is no user with username " + name;
            AlertBox.display(msg);
        }
        else if (res.equals(StatusEnum.USERNAME_AND_PASSWORD_MISMATCH.getStatus())){
            AlertBox.display(StatusEnum.USERNAME_AND_PASSWORD_MISMATCH.getStatus());
        }
        else if (res.equals(StatusEnum.USER_LOGIN_SUCCESSFULLY.getStatus())){
            new SceneController().switchScene("/fxml/main_menu.fxml",actionEvent);
        }
    }

    public void enterRegisterMenu(ActionEvent actionEvent) throws IOException {
        new SceneController().switchScene("/fxml/register_menu.fxml",actionEvent);
    }

    public void register(ActionEvent actionEvent) {
        String name = register_username.getText();
        String nick = register_nickname.getText();
        String pass = register_pass.getText();
        String res = new LoginMenuController().createUser(name,nick,pass);
        if(name == null || nick == null || pass == null){
            AlertBox.display("Please fill the empty fields!");
        }
        else if (res.equals("user with username " + name + " already exists")){
            AlertBox.display("user with username " + name + " already exists");
        }
        else if (res.equals("user with nickname " + nick + " already exists")){
            AlertBox.display("user with nickname " + nick + " already exists");
        }
        else if(res.equals(StatusEnum.USER_CREATE_SUCCESSFULLY.getStatus())){
            AlertBox.display(StatusEnum.USER_CREATE_SUCCESSFULLY.getStatus());
            register_username.clear();
            register_nickname.clear();
            register_pass.clear();
        }
    }

    public void returnToStart(MouseEvent inputEvent) throws IOException {
        Stage stage = (Stage) ((Node)inputEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        Pane pane = fxmlLoader.load();
        stage.setScene(new Scene(pane));
    }

    public void exit(MouseEvent event) {
        System.exit(0);
    }
}
