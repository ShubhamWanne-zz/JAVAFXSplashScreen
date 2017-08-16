/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notification.and.splash.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author shubham
 */
public class LoginScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Pane root_pane;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton enter;

    @FXML
    private JFXTextField user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!NotificationAndSplashScreen.splash_screen_is_done){
            loadSplashScreen();
        }
    }
    @FXML
    void show_notification(ActionEvent event) {
        System.out.println("This executed !");
        Notifications notifications= Notifications.create()
                .title("Welcome")
                .text("Hi "+user.getText())
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT)
                ;
        notifications.show();
    }
    public void loadSplashScreen(){
        try {
            NotificationAndSplashScreen.splash_screen_is_done=true;
            
            System.out.println(root);
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            root.getChildren().setAll(pane);
            
            
            FadeTransition fadin= new FadeTransition(Duration.seconds(2), pane);
            fadin.setFromValue(0);
            fadin.setToValue(1);
            fadin.setCycleCount(1);
            
            FadeTransition fadout= new FadeTransition(Duration.seconds(2), pane);
            fadout.setFromValue(1);
            fadout.setToValue(0);
            fadout.setCycleCount(1);
             
            fadin.play();
            
            fadin.setOnFinished((e)->{
                fadout.play();
            });
            
            fadout.setOnFinished((e)->{
                try {
                    AnchorPane pane_in = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
                root.getChildren().setAll(pane_in);
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
