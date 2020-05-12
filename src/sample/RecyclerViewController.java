package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class RecyclerViewController extends JFXListCell<Student> {

    FXMLLoader fxmlLoader;
    @FXML
    private ImageView pdfDrive;


    @FXML
    private AnchorPane rootanchor;

    @FXML
    private Label dateCell;

    @FXML
    private Label userFirstName;

    @FXML
    private Label userLastName;

    @FXML
    private JFXButton acceptButton;

    @FXML
    private JFXButton declineButton;

    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);
        if (empty || student == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/useritemView.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            userFirstName.setText(student.getUserName());
            userLastName.setText(student.getLastName());
            dateCell.setText(student.getDate());
          declineButton.setOnAction(actionEvent -> {
              getListView().getItems().remove(getItem());
          });
           acceptButton.setOnAction(actionEvent -> {
                getListView().getItems().remove(getItem());

            });

            setText(null);
            setGraphic(rootanchor);


        }
    }
}
