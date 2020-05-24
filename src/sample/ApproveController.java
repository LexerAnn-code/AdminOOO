package sample;

import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ApproveController  implements Initializable,DBConnection  {
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    Stage stage;
    Scene scene;
    Parent root;


    @FXML
    private Label overViewNav;

    @FXML
    private Label declineNav;

    @FXML
    private Label approveNav;
    @FXML

    private JFXListView<Admission> acceptListView;
    ObservableList<Admission> acceptlist;
    Admission admission;
    String firstnames;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        acceptlist = FXCollections.observableArrayList();
        int counter = 0;
        try {
            ResultSet userRes = ReadToDBs();

            while (userRes.next()) {
                admission=new Admission();
                admission.setFirstname(userRes.getString("first_name"));
                admission.setLastname(userRes.getString("last_name"));
                admission.setAdmissionID(userRes.getInt("admissionid"));
                acceptlist.addAll(admission);
                counter++;
            }
            System.out.println("Count" + counter);
            acceptListView.setItems(acceptlist);
acceptListView.setOnMouseClicked(mouseEvent -> {

    Admission name= acceptListView.getSelectionModel().getSelectedItem();
    System.out.println(name.getFirstname()+ "New");
    AcceptDetailsController acceptDetailsController=new AcceptDetailsController();
    acceptDetailsController.setFirstname(name.getFirstname(),name.getLastname());
    acceptDetailsController.setAdmissionID(name.getAdmissionID());
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/sample/acceptDetails.fxml"));

    try {
        fxmlLoader.setRoot(fxmlLoader.getRoot());
        fxmlLoader.load();
    } catch (IOException e) {

    }

    Parent root = fxmlLoader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
});

            acceptListView.setCellFactory(RecyclerAcceptController -> new RecyclerAcceptController());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    overViewNav.setOnMouseClicked(mouseEvent -> {
        moveToOverView();
    });
        declineNav.setOnMouseClicked(mouseEvent -> {
            moveToDecline();
        });
    }
    public void selectWell(CurrentSelected currentSelected){
        System.out.println("NAME"+ currentSelected.getFirst_name());
          firstnames=currentSelected.getFirst_name();

    }
    private ResultSet ReadToDBs() throws SQLException {
        ResultSet resultSet = null;
        String insert = "SELECT * FROM admissionlist WHERE status=?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1,"Accepted");
        resultSet = preparedStatement.executeQuery();
        System.out.println("Data" + resultSet);
        return resultSet;

    }


    public void moveToDecline(){
            try {

                root = FXMLLoader.load(getClass().getResource("/sample/DeclinePage.fxml"));
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


    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
}
