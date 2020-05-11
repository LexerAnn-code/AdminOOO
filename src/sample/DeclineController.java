package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeclineController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Label overViewNav;

    @FXML
    private Label approveNav;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        approveNav.setOnMouseClicked(mouseEvent -> {
            moveToApprove();
        });
        overViewNav.setOnMouseClicked(mouseEvent -> {
            moveToOverView();
        });
    }
    public void moveToApprove(){
        try {

            root = FXMLLoader.load(getClass().getResource("/sample/ApprovePage.fxml"));
            scene = new Scene(root, 850, 500);
            stage = (Stage) approveNav.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void moveToOverView(){
        try {

            root = FXMLLoader.load(getClass().getResource("/sample/AdminPage.fxml"));
            scene = new Scene(root, 850, 500);
            stage = (Stage) approveNav.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
