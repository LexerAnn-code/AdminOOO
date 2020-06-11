package sample.controller;

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Admission;
import sample.CurrentSelected;
import sample.Extension;

import java.io.IOException;

public class RecyclerAcceptController extends JFXListCell<Admission> {
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
        CurrentSelected currentSelected;
        super.updateItem(admission, empty);
        if (empty || admission == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/acceptItem.fxml"));
                //Declaring the fxml used for the cell in each ListView
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            userFirstName.setText(admission.getFirstname());
            userLastName.setText(admission.getLastname());
            String fn=admission.getFirstname();
            String ln=admission.getLastname();
            //Setings the data in each cell for the ListView
            int admissionId=admission.getAdmissionID();
            currentSelected=new CurrentSelected();
            currentSelected.setFirst_name(fn);
            currentSelected.setLast_name(ln);
            Extension extension=new Extension();
            extension.SelectAccept(currentSelected);


            setText(null);
            setGraphic(rootanchor);
        }
    }
}
