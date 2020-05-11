package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           button.setOnAction(event->{

               System.out.println("passed");
               try {

                   root = FXMLLoader.load(getClass().getResource("/sample/AdminPage.fxml"));
                   scene = new Scene(root, 850, 500);
                   stage = (Stage) button.getScene().getWindow();
                   stage.setScene(scene);
                   stage.show();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           });
    }
}
