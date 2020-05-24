package sample;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DeclineController implements Initializable,DBConnection {
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
    private Label declineCount;


    @FXML
    private JFXListView<Admission> deniedListView;
    ObservableList<Admission> deniedlist;
    Admission admission;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deniedlist = FXCollections.observableArrayList();
        int counter = 0;
        try {
            ResultSet userRes = ReadToDBs();

            while (userRes.next()) {
                admission=new Admission();
                admission.setFirstname(userRes.getString("first_name"));
                admission.setLastname(userRes.getString("last_name"));
                admission.setAdmissionID(userRes.getInt("admissionid"));
                deniedlist.addAll(admission);
                counter++;
            }
            System.out.println("Count" + counter);
            declineCount.setText(String.valueOf(counter));
            deniedListView.setItems(deniedlist);

            deniedListView.setCellFactory(RecyclerViewDeclineController -> new RecyclerViewDeclineController());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        approveNav.setOnMouseClicked(mouseEvent -> {
            moveToApprove();
        });
        overViewNav.setOnMouseClicked(mouseEvent -> {
            moveToOverView();
        });
    }
    private ResultSet ReadToDBs() throws SQLException {
        ResultSet resultSet = null;
        String insert = "SELECT * FROM admissionlist WHERE status=?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1,"Denied");
        resultSet = preparedStatement.executeQuery();
        System.out.println("Data" + resultSet);
        return resultSet;
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

    @Override
    public void connect() throws SQLException {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
            System.out.println("Connection " + connection.getCatalog());
        }

    }

