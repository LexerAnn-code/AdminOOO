package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    @FXML
    private StackPane rootpane;
    @FXML
    private ImageView image;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new SplashScreen().start();

    }

    class  SplashScreen extends Thread{
        @Override
        public void run(){

            try {
                Thread.sleep(2500);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/sample/view/Login.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene=new Scene(root,1000,650);
                        Stage stage=new Stage();
                        stage.setScene(scene);
                        stage.show();

                        rootpane.getScene().getWindow().hide();
                        image.getScene().getWindow().hide();

                    }
                });

                
                
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}

