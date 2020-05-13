package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class RecyclerViewController extends JFXListCell<Admission> {

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

    @FXML
    private JFXButton Doc;

    @Override
    protected void updateItem(Admission admission, boolean empty) {
        super.updateItem(admission, empty);
        if (empty || admission == null) {
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
            userFirstName.setText(admission.getFirstname());
            userLastName.setText(admission.getLastname());

          declineButton.setOnAction(actionEvent -> {
              getListView().getItems().remove(getItem());

              Extension extension=new Extension();
              extension.deleteTask(admission);
              extension.deleteSelect(admission);
              System.out.println("Totally Passed" + admission.getAdmissionID());
          });
           acceptButton.setOnAction(actionEvent -> {
                getListView().getItems().remove(getItem());

            });

            setText(null);
            setGraphic(rootanchor);


        }
    }
}
