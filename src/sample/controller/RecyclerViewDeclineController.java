package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Admission;

import java.io.IOException;

public class RecyclerViewDeclineController extends JFXListCell<Admission> {
    FXMLLoader fxmlLoader;
    @FXML
    private AnchorPane rootanchor;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;
    @FXML
    private Label dateCell;


    @Override
    protected void updateItem(Admission admission, boolean empty) {
        super.updateItem(admission, empty);
        if (empty || admission == null) {
            setText(null);
            setGraphic(null);
        } else {

                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/declineListItem.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            userFirstName.setText(admission.getFirstname());
            userLastName.setText(admission.getLastname());

            setText(null);
            setGraphic(rootanchor);
        }
    }
}