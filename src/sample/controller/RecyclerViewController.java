package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Admission;
import sample.Extension;

import javax.swing.text.html.ImageView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class RecyclerViewController extends JFXListCell<Admission> {

   private FXMLLoader fxmlLoader;
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
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/userItemView.fxml"));
                //Declaring the fxml used for the cell in each ListView
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Setings the data in each cell for the ListView
            userFirstName.setText(admission.getFirstname());
            userLastName.setText(admission.getLastname());

          declineButton.setOnAction(actionEvent -> {
              getListView().getItems().remove(getItem());

              Extension extension=new Extension();
              extension.DenySelect(admission);

              System.out.println("Totally Passed" + admission.getAdmissionID());
          });
          Doc.setOnAction(actionEvent -> {
              Extension extension=new Extension();
              //
              try {
                  extension.DocFile(admission);
              } catch (SQLException | FileNotFoundException e) {
                  e.printStackTrace();
              }
          });
           acceptButton.setOnAction(actionEvent -> {
                getListView().getItems().remove(getItem());
               Extension extension=new Extension();
               extension.AcceptSelect(admission);

            });

            setText(null);
            setGraphic(rootanchor);


        }
    }
}
